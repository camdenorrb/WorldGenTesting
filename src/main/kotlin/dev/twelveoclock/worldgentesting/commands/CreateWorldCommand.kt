package dev.twelveoclock.worldgentesting.commands

import dev.twelveoclock.worldgentesting.WorldGenTestingPlugin
import org.bukkit.WorldCreator
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CreateWorldCommand(val plugin: WorldGenTestingPlugin) : CommandExecutor {

	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

		if (args.size < 2) {
			sender.sendMessage("Usage: /createworld <world name> <generator>")
			return true
		}

		val world = plugin.server.getWorld(args[0])
		if (world != null) {
			sender.sendMessage("A world with that name already exists")
			return true
		}

		val generator = plugin.worldGeneratorByID[args[1]]
		if (generator == null) {
			sender.sendMessage("No generator with that name exists")
			return true
		}

		val worldCreator = WorldCreator(args[0]).generator(generator)
		plugin.server.createWorld(worldCreator)

		sender.sendMessage("Created world ${args[0]} with generator ${args[1]}")

		return true
	}
}