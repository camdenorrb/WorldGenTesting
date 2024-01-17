package dev.twelveoclock.worldgentesting.generators

import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*

class RandomWorldGeneration : ChunkGenerator() {

	override fun generateSurface(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {
		random.nextInt()
	}

}
