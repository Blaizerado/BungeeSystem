package de.unknown.commands;

import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyFollow extends Command {

	public ProxyFollow(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.follow")) {p.sendMessage("§cDas Darfst du nicht!"); return;}
				p.sendMessage("§3/follow <Player>");
			}else if(args.length == 1) {
				if(!p.hasPermission("Bungee.follow")) {p.sendMessage("§cDas Darfst du nicht!"); return;}
				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
				if(target != null) {
					p.connect(target.getServer().getInfo());
					p.sendMessage(Utils.prefix + "§3Du bist dem Spieler §e" + args[0] + "§3 nach gesprungen!");
				}else {p.sendMessage(Utils.prefix + "§cDer Spieler §e" + args[0] + "§c ist nicht Online!"); return;}
			}
		}
	}

}
