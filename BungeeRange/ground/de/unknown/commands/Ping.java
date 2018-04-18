package de.unknown.commands;

import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class Ping extends Command {

	public Ping(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage(Utils.prefix + "§3Du hast einen Ping von " + p.getPing() + "ms!");
			}
		}else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender c = (ConsoleCommandSender) sender;
			if(args.length == 0) {
				c.sendMessage("§cAchtung der Server hat keinen Ping ;D ");
			}
		}
	}

}
