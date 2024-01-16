package dev.twelveoclock.worldgentesting.generators

import org.bukkit.Material
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*

class FlatWorldGenerator : ChunkGenerator() {

	override fun generateSurface(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {
		chunkData.setRegion(0, 0, 0, 16, 1, 16, Material.STONE)
		chunkData.setRegion(0, 1, 0, 16, 2, 16, Material.DIRT)
		chunkData.setRegion(0, 2, 0, 16, 3, 16, Material.GRASS_BLOCK)
	}

}