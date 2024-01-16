package dev.twelveoclock.worldgentesting.generators

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*

class VoidWorldGenerator : ChunkGenerator() {

	override fun generateSurface(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {
		// NOOP besides setting block for spawn
		chunkData.setBlock(0, 64, 0, Material.GLASS)
	}


	override fun getFixedSpawnLocation(world: org.bukkit.World, random: java.util.Random): org.bukkit.Location? {
		return Location(world, 0.0, 64.0, 0.0)
	}

}