package de.unknown.commands;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class Find extends Command {

	private BungeeRange main;

	public Find(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.find")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/find [Player]");
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(!p.hasPermission("Bungee.find")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				ProxiedPlayer t = this.main.getProxy().getPlayer(args[0]);
				if(t != null) {
					p.sendMessage(Utils.prefix + "Der Spieler " + args[0] + " befindet sich zurzeit auf dem Server " + t.getServer().getInfo().getName());
				}else {p.sendMessage(Utils.prefix + "§cDer Spieler ist nicht Online");}
			}
		}else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender c = (ConsoleCommandSender)sender;
			if(args.length == 0) {
				c.sendMessage("§3<============>§2Info§3<============>");
				c.sendMessage("§3/find [Player]");
				c.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				ProxiedPlayer t = this.main.getProxy().getPlayer(args[0]);
				if(t != null) {
					c.sendMessage(Utils.prefix + "Der Spieler " + args[0] + " befindet sich zurzeit auf dem Server " + t.getServer().getInfo().getName());
				}else {c.sendMessage(Utils.prefix + "§cDer Spieler ist nicht Online");}
			}
		}
	}

}
