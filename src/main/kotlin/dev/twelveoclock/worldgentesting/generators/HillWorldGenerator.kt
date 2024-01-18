package dev.twelveoclock.worldgentesting.generators

import org.bukkit.Material
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import org.bukkit.util.noise.SimplexNoiseGenerator
import kotlin.random.Random

class HillWorldGenerator : ChunkGenerator() {

	// This needs to be defined outside of the generateSurface function
	val simplexNoise = SimplexNoiseGenerator(Random.nextLong())

	// TODO: Look at voxel sniper to see how the smooth brush works
	override fun generateNoise(worldInfo: WorldInfo, random: java.util.Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {

		val chunkSize = 16
		val baseHeight = 50

		// Noise parameters
		val octaves = 4
		val frequency = 0.07
		val amplitude = 5.0


		// Generate hills, stone interior, bedrock bottom, and grass top
		for (x in 0..15) {
			for (z in 0..15) {

				val worldX = chunkX * chunkSize + x
				val worldZ = chunkZ * chunkSize + z
				val noiseValue = simplexNoise.noise(worldX.toDouble(), worldZ.toDouble(), octaves, frequency, amplitude)
				val height =
					(baseHeight + noiseValue).coerceIn(chunkData.minHeight.toDouble(), chunkData.maxHeight.toDouble())

				// Set blocks based on calculated height
				for (y in chunkData.minHeight..chunkData.maxHeight) {
					when {
						y < height - 4 -> chunkData.setBlock(x, y, z, Material.STONE)
						y == height.toInt() -> chunkData.setBlock(x, y, z, Material.GRASS_BLOCK)
						y < height -> chunkData.setBlock(x, y, z, Material.DIRT)
						//y < 100 -> chunkData.setBlock(x, y, z, Material.WATER)
					}
				}
			}
		}
	}

}