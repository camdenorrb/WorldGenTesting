package dev.twelveoclock.worldgentesting

import dev.twelveoclock.worldgentesting.generators.FlatWorldGenerator
import dev.twelveoclock.worldgentesting.modules.CommandModule
import dev.twelveoclock.worldgentesting.modules.WorldGenModule
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.java.JavaPlugin

class WorldGenTestingPlugin : JavaPlugin() {

    val worldGeneratorByID = mutableMapOf<String, ChunkGenerator>(
        "flat" to FlatWorldGenerator()
    )

    val commandModule = CommandModule(this)

    val worldGenModules = mutableListOf<WorldGenModule>()

    /*
    lateinit var pluginConfig: PluginConfig
        private set
    */

    override fun onLoad() {
    }

    override fun onEnable() {
        commandModule.enable()
        worldGenModules.forEach { it.enable() }
    }

    override fun onDisable() {
        commandModule.disable()
        worldGenModules.forEach { it.disable() }
    }


    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator? {
        return worldGeneratorByID[id]
    }

    /**
     * Loads the current config or copies the default
     */
    /*private fun loadConfig() {

        val configPath = Path(dataFolder.absolutePath, "config.toml")

        // Create the default config if no file exists
        if (configPath.notExists()) {
            javaClass.getResource("/config.toml")!!.openStream().use { configStream ->
                configPath.parent.createDirectories()
                Files.copy(configStream, configPath)
            }
        }

        // Read config
        Files.newInputStream(configPath).use {
            pluginConfig = PluginConfig.from(Toml().read(it))
        }
    }*/

}