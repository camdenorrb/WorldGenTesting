package dev.twelveoclock.worldgentesting.commands;

import dev.twelveoclock.worldgentesting.WorldGenTestingPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class DeleteWorldCommand(val plugin: WorldGenTestingPlugin) : CommandExecutor, TabCompleter {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

		if (args.isEmpty()) {
			sender.sendMessage("Usage: /deleteworld <world name>")
			return true
		}

		val world = plugin.server.getWorld(args[0])
		if (world == null) {
			sender.sendMessage("No world with that name exists")
			return true
		}

		// Teleport all players to the main world
		world.players.forEach { it.teleport(plugin.server.worlds[0].spawnLocation) }

		// Unload and delete the world
		plugin.server.unloadWorld(world, false)
		world.worldFolder.deleteRecursively()

		sender.sendMessage("Deleted world ${args[0]}")

		return true
	}

	override fun onTabComplete(
		sender: CommandSender,
		command: Command,
		label: String,
		args: Array<out String>
	): MutableList<String>? {

		when(args.size) {
			1 -> return plugin.server.worlds.map { it.name }.filter { it.startsWith(args[0], true) }.toMutableList()
		}

		return null
	}

}
