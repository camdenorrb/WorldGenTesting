package dev.twelveoclock.worldgentesting.commands

import dev.twelveoclock.worldgentesting.WorldGenTestingPlugin
import org.bukkit.WorldCreator
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class CreateWorldCommand(val plugin: WorldGenTestingPlugin) : CommandExecutor, TabCompleter {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

		if (args.size < 2) {
			sender.sendMessage("Usage: /createworld <world name> <generator>")
			return true
		}

		val oldWorld = plugin.server.getWorld(args[0])
		if (oldWorld != null) {
			// Delete world if it already exists
			oldWorld.players.forEach { it.teleport(plugin.server.worlds[0].spawnLocation) }
			plugin.server.unloadWorld(oldWorld, false)
			oldWorld.worldFolder.deleteRecursively()
		}

		val generator = plugin.worldGeneratorByID[args[1]]
		if (generator == null) {
			sender.sendMessage("No generator with that name exists")
			return true
		}

		val worldCreator = WorldCreator(args[0]).generator(generator)
		val world = plugin.server.createWorld(worldCreator)

		sender.sendMessage("Created world ${args[0]} with generator ${args[1]}")
		(sender as? Player)?.teleport(world!!.spawnLocation)

		return true
	}

	override fun onTabComplete(
		sender: CommandSender,
		command: Command,
		label: String,
		args: Array<out String>
	): MutableList<String>? {

		when(args.size) {
			2 -> return plugin.worldGeneratorByID.keys.filter { it.startsWith(args[1], true) }.toMutableList()
		}

		return null
	}
}