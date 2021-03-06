package com.soulcraftserver.aacore.CMD;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.soulcraftserver.aacore.API.Utilities;
import com.soulcraftserver.aacore.CMD.Commands.CommandDisable;
import com.soulcraftserver.aacore.CMD.Commands.CommandEnable;
import com.soulcraftserver.aacore.CMD.Commands.CommandHelp;
import com.soulcraftserver.aacore.CMD.Commands.CommandList;
import com.soulcraftserver.aacore.CMD.Commands.CommandVersion;
import com.soulcraftserver.aacore.Usage.IcyHotManager;

public class CommandHandler implements CommandExecutor {
	
	private IcyHotManager manager;
	
	public CommandHandler(IcyHotManager manager) {
		this.manager = manager;
		manager.getPlugin().getCommand("AACore").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("AACore")) {
			if(args.length != 0 && manager.getConfig().getBoolean("console only") && !(sender instanceof Player)) {
				sender.sendMessage("�c�lAACore �8�l>> �7Sorry, but in-game commands are disabled for this plugin.");
				return true;
			}
			
			switch (args.length) {
			case 0:
				new CommandVersion(sender, command, args, manager);
				return true;
			
			case 1:
				if(Utilities.isAny(args[0], "help", "h"))
					new CommandHelp(sender, command, args, manager);
				else if(Utilities.isAny(args[0], "list", "l"))
					new CommandList(sender, command, args, manager);
				else if(Utilities.isAny(args[0], "reload", "rl", "r")) {
					manager.onReload();
					sender.sendMessage("�c�lAACore �8�7>> �7Plugin reloaded.");
				} else
					sender.sendMessage("�c�lAACore �8�l>> �7Error, invalid arguments given.");
				return true;
			
			case 2:
				if(Utilities.isAny(args[0], "help", "h"))
					new CommandHelp(sender, command, args, manager);
				else if(Utilities.isAny(args[0], "enable", "e"))
					new CommandEnable(sender, command, args, manager);
				else if(Utilities.isAny(args[0], "disable", "d"))
					new CommandDisable(sender, command, args, manager);
				else
					sender.sendMessage("�c�lAACore �8�l>> �7Error, invalid arguments given.");
				return true;

			default:
				sender.sendMessage("�c�lAACore �8�l>> �7Error, unknown command.");
				return false;
			}
		}
		
		return false;
	}

}
