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
		// TODO: Simplify this
		plugin.getCommand("createWorld")?.let {
			it.setExecutor(createWorldCommand)
			it.tabCompleter = createWorldCommand
		}
		plugin.getCommand("switchWorld")?.let {
			it.setExecutor(switchWorldCommand)
			it.tabCompleter = switchWorldCommand
		}
		plugin.getCommand("deleteWorld")?.let {
			it.setExecutor(deleteWorldCommand)
			it.tabCompleter = deleteWorldCommand
		}
	}

	override fun onDisable() {
		plugin.getCommand("createWorld")?.let {
			it.setExecutor(null)
			it.tabCompleter = null
		}
		plugin.getCommand("switchWorld")?.let {
			it.setExecutor(null)
			it.tabCompleter = null
		}
		plugin.getCommand("deleteWorld")?.let {
			it.setExecutor(null)
			it.tabCompleter = null
		}
	}

}