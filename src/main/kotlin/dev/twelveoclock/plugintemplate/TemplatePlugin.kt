package dev.twelveoclock.plugintemplate

import com.moandjiezana.toml.Toml
import dev.twelveoclock.plugintemplate.config.PluginConfig
import dev.twelveoclock.plugintemplate.modules.CatModule
import dev.twelveoclock.plugintemplate.modules.PlayerModule
import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.notExists

class TemplatePlugin : JavaPlugin {

    val catModule = CatModule(this)

    val playerModule = PlayerModule(this)

    lateinit var pluginConfig: PluginConfig
        private set


    // Constructor for MockBukkit
    @Suppress("unused")
    constructor()
            : super()

    // Constructor for MockBukkit
    @Suppress("unused")
    constructor(loader: JavaPluginLoader, description: PluginDescriptionFile, dataFolder: File, file: File)
            : super(loader, description, dataFolder, file)


    override fun onLoad() {
        loadConfig()
    }

    override fun onEnable() {
        catModule.enable()
        playerModule.enable()
    }

    override fun onDisable() {
        catModule.disable()
        playerModule.disable()
    }


    /**
     * Loads the current config or copies the default
     */
    private fun loadConfig() {

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
    }
}