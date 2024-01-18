package dev.twelveoclock.worldgentesting.generators

import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.data.type.WallSign
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*

// TODO: Learn how block populators work to set sign text
class PlotWorldGenerator(val plotSize: Int, val roadSize: Int) : ChunkGenerator() {


	override fun generateSurface(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunkData: ChunkData) {

		val plotPlusRoad = plotSize + roadSize

		for (x in 0 until 16) {
			for (z in 0 until 16) {

				val worldX = (chunkX shl 4) + x
				val worldZ = (chunkZ shl 4) + z

				val modX = Math.floorMod(worldX, plotPlusRoad)
				val modZ = Math.floorMod(worldZ, plotPlusRoad)

				// Determine if the current block is in a road area
				val inRoadX = modX < roadSize
				val inRoadZ = modZ < roadSize

				val onBorderX = modX == roadSize || modX == plotPlusRoad - 1
				val onBorderZ = modZ == roadSize || modZ == plotPlusRoad - 1

				if (modX == roadSize && modZ == 0) {
					chunkData.setBlock(x, 0, z, Material.STONE)
					chunkData.setBlock(x, 1, z, Material.DIRT)
					chunkData.setBlock(x, 2, z, Material.OAK_WALL_SIGN)

					chunkData.getTypeAndData(x, 2, z)
					val wallSign = chunkData.getBlockData(x, 2, z) as WallSign
					wallSign.facing = BlockFace.SOUTH
					chunkData.setBlock(x, 2, z, wallSign)
				}
				else if (inRoadX || inRoadZ) {
					// Road area
					chunkData.setBlock(x, 0, z, Material.STONE)
					chunkData.setBlock(x, 1, z, Material.QUARTZ_BLOCK)
				}
				else if (onBorderX || onBorderZ){
					// Plot border
					chunkData.setBlock(x, 0, z, Material.STONE)
					chunkData.setBlock(x, 1, z, Material.DIRT)
					chunkData.setBlock(x, 2, z, Material.SANDSTONE_SLAB)
				}
				else {
					// Plot area
					chunkData.setBlock(x, 0, z, Material.STONE)
					chunkData.setBlock(x, 1, z, Material.GRASS_BLOCK)
				}
			}
		}
	}

	/*
	object Populator : BlockPopulator() {
		override fun populate(
			worldInfo: WorldInfo,
			random: Random,
			chunkX: Int,
			chunkZ: Int,
			limitedRegion: LimitedRegion
		) {
			limitedRegion.
		}
	}*/

}