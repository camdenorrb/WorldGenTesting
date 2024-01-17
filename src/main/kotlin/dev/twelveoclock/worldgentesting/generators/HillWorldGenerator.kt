package dev.twelveoclock.worldgentesting.generators

import org.bukkit.Material
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import org.bukkit.util.noise.SimplexNoiseGenerator
import kotlin.random.Random

class HillWorldGenerator : ChunkGenerator() {

	val simplexNoise = SimplexNoiseGenerator(Random.nextLong())

	override fun generateSurface(worldInfo: WorldInfo, random: java.util.Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {


		// Noise parameters
		val octaves = 4
		val frequency = 0.1
		val amplitude = 5.0


		// Generate hills, stone interior, bedrock bottom, and grass top
		for (x in 0..15) {
			for (z in 0..15) {

				val chunkSize = 16
				val baseHeight = 90

				val worldX = chunkX * chunkSize + x
				val worldZ = chunkZ * chunkSize + z

				// Set blocks based on calculated height
				for (y in chunkData.minHeight..chunkData.maxHeight) {

					val noiseValue = simplexNoise.noise(worldX.toDouble(), y.toDouble(), worldZ.toDouble(), octaves, frequency, amplitude)
					// Combine noise and wave, apply base height and hill height
					val height = baseHeight + noiseValue



					when {
						y < height - 4 -> chunkData.setBlock(x, y, z, Material.STONE)
						y < height -> chunkData.setBlock(x, y, z, Material.DIRT)
						y.toDouble() == height -> chunkData.setBlock(x, y, z, Material.GRASS_BLOCK)
						//y < 100 -> chunkData.setBlock(x, y, z, Material.WATER)
					}
				}
			}
		}
	}

	fun linearInterpolate(a: Double, b: Double, blend: Double): Double {
		return (1.0 - blend) * a + blend * b
	}

}