package dev.twelveoclock.worldgentesting.generators

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*

class VoidWorldGenerator : ChunkGenerator() {

	override fun generateSurface(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {
		// NOOP besides setting block for spawn
		if (chunkX != 0 || chunkZ != 0) return
		chunkData.setBlock(0, 64, 0, Material.GLASS)
	}

	override fun getFixedSpawnLocation(world: World, random: Random): Location {
		return Location(world, 0.5, 65.0, 0.5)
	}

}