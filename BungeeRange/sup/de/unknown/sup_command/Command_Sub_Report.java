package de.unknown.sup_command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.support.Support_MySQL;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_Sub_Report {

	@SuppressWarnings("deprecation")
	public Command_Sub_Report(ProxiedPlayer p, String[] args, BungeeRange main) {
		String UUID = uuidfetcher.getUUID(p.getName()).toString();
		String UUID_TARGET = uuidfetcher.getUUID(args[1]).toString();
		String Reason = null;
		for(int i = 2; i<args.length; i++) {
			Reason = Reason + " " + args[i];
		}
		Reason = Reason.replace("null", "");
		
		p.sendMessage(Utils.prefix + "§cDu hast den Spieler §e" + args[1] + "§cReportet!");
		p.sendMessage("§cDer Report lautet §d" + Reason);
		
		for(ProxiedPlayer pp : main.getProxy().getPlayers()) {
			if(pp.hasPermission("Bungee.support.sendmessage")) {
				pp.sendMessage(Utils.prefix + "§cDer Spieler §e" + p.getName() + "§c hat soebend den Spieler §e" + args[1] + "§creportet!");
			}
		}
		Support_MySQL.createReport(UUID, Reason, UUID_TARGET);
	}

}
