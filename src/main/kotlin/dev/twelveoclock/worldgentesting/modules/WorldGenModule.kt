package dev.twelveoclock.worldgentesting.modules

import dev.twelveoclock.worldgentesting.WorldGenTestingPlugin
import dev.twelveoclock.worldgentesting.modules.base.PluginModule
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.java.JavaPlugin

// TODO: Take in a world generator
class WorldGenModule(override val plugin: WorldGenTestingPlugin, val name: String, val chunkGenerator: ChunkGenerator) : PluginModule() {

	override fun onEnable() {
		plugin.worldGeneratorByID[name] = chunkGenerator
	}

	override fun onDisable() {

		plugin.server.worlds
			.filter { it.generator == chunkGenerator }
			.forEach { plugin.server.unloadWorld(it, true) }


		plugin.worldGeneratorByID.remove(name)
	}

}