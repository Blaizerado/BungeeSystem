package de.unknown.sup_command;

import de.unknown.support.Support_MySQL_SetStars;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyStast extends Command {

	public ProxyStast(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if(args.length == 2) {
			if(args[0].equalsIgnoreCase("0")) {
				Support_MySQL_SetStars b = new Support_MySQL_SetStars(p, uuidfetcher.getUUID(args[1]).toString(), 0);
				b.start();
			}else if(args[0].equalsIgnoreCase("1")) {
				Support_MySQL_SetStars b = new Support_MySQL_SetStars(p, uuidfetcher.getUUID(args[1]).toString(), 1);
				b.start();
			}else if(args[0].equalsIgnoreCase("2")) {
				Support_MySQL_SetStars b = new Support_MySQL_SetStars(p, uuidfetcher.getUUID(args[1]).toString(), 2);
				b.start();
			}else if(args[0].equalsIgnoreCase("3")) {
				Support_MySQL_SetStars b = new Support_MySQL_SetStars(p, uuidfetcher.getUUID(args[1]).toString(), 3);
				b.start();
			}else if(args[0].equalsIgnoreCase("4")) {
				Support_MySQL_SetStars b = new Support_MySQL_SetStars(p, uuidfetcher.getUUID(args[1]).toString(), 4);
				b.start();
			}else if(args[0].equalsIgnoreCase("5")) {
				Support_MySQL_SetStars b = new Support_MySQL_SetStars(p, uuidfetcher.getUUID(args[1]).toString(), 5);
				b.start();
			}
		}
	}

}
