package dev.twelveoclock.worldgentesting.generators

import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*

class HillsWithBlocksWorldGen : ChunkGenerator() {

	override fun generateNoise(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {
		//super.generateNoise(worldInfo, random, chunkX, chunkZ, chunkData)
	}

	override fun generateSurface(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {

		//this.getDefaultPopulators(worldInfo)
	}

}