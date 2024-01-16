package dev.twelveoclock.worldgentesting.modules

import dev.twelveoclock.worldgentesting.WorldGenTestingPlugin
import dev.twelveoclock.worldgentesting.commands.CreateWorldCommand
import dev.twelveoclock.worldgentesting.commands.DeleteWorldCommand
import dev.twelveoclock.worldgentesting.commands.SwitchWorldCommand
import dev.twelveoclock.worldgentesting.modules.base.PluginModule

class CommandModule(override val plugin: WorldGenTestingPlugin) : PluginModule() {

	val createWorldCommand = CreateWorldCommand(plugin)
	val switchWorldCommand = SwitchWorldCommand(plugin)
	val deleteWorldCommand = DeleteWorldCommand(plugin)

	override fun onEnable() {
		plugin.getCommand("createWorld")?.setExecutor(createWorldCommand)
		plugin.getCommand("switchWorld")?.setExecutor(switchWorldCommand)
		plugin.getCommand("deleteWorld")?.setExecutor(deleteWorldCommand)
	}

	override fun onDisable() {
		plugin.getCommand("createWorld")?.setExecutor(null)
		plugin.getCommand("switchWorld")?.setExecutor(null)
		plugin.getCommand("deleteWorld")?.setExecutor(null)
	}

}