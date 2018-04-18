package de.unknown.commands;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Lobby extends Command {

	private BungeeRange main;

	public Lobby(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {	
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.connect(this.main.getProxy().getServerInfo("lobby"));
			}else {p.sendMessage("§cAchtung der Command ist nicht bekannt");}
		}
	}

}
