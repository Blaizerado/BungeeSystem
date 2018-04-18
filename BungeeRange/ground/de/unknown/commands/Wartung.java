package de.unknown.commands;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Server_Console;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class Wartung extends Command {

	private BungeeRange main;

	public Wartung(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.wartung")) {p.sendMessage("§cAchtung das darfst du nicht!"); return;}
				if(Utils.Wartung) {
					Server_Console.updateWartung(0);
					p.sendMessage(Utils.prefix + "§3Der Wartungmodus ist nun Offline");
				}else {
					Server_Console.updateWartung(1);
					p.sendMessage(Utils.prefix + "§3Der Wartungmodus ist nun Online");
					for(ProxiedPlayer pp : this.main.getProxy().getPlayers()) {
						if(!pp.hasPermission("Bungee.join")) {
							pp.disconnect("§cAchtung\n§eDer Server wird grade gewartet!");
						}
					}
				}
			}else {p.sendMessage("§cDer Command ist nicht bekannt!");}
		}else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender c = (ConsoleCommandSender) sender;
			if(args.length == 0) {
				if(Utils.Wartung) {
					Server_Console.updateWartung(0);
					c.sendMessage(Utils.prefix + "§3Der Wartungmodus ist nun Offline");
				}else {
					Server_Console.updateWartung(1);
					c.sendMessage(Utils.prefix + "§3Der Wartungmodus ist nun Online");
					for(ProxiedPlayer pp : this.main.getProxy().getPlayers()) {
						if(!pp.hasPermission("Bungee.join")) {
							pp.disconnect("§cAchtung\n§eDer Server wird grade gewartet!");
						}
					}
				}
			}else {c.sendMessage("§cDer Command ist nicht bekannt!");}
		}
	}

}
