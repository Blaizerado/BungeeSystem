package de.unknown.sup_command;

import de.unknown.mysql.Player_Account;
import de.unknown.support.Support_MySQL_GetStats;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyRead extends Command {

	public ProxyRead(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if(args.length == 0) {
			if(!p.hasPermission("Bungee.stats.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
			p.sendMessage("§3/stats [Player]");
		}else if(args.length == 1) {
			if(!p.hasPermission("Bungee.stats.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
			String Player = args[0];
			if(!Player_Account.playExists(uuidfetcher.getUUID(Player).toString())) {p.sendMessage(Utils.prefix + "§cAchtung, der Spieler ist nicht in der Datenbank!");}
			Support_MySQL_GetStats b = new Support_MySQL_GetStats(p, uuidfetcher.getUUID(args[0]).toString());
			b.start();
		}
	}

}
