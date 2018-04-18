package de.unknown.commands.sub;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Sub_Info_Player {

	@SuppressWarnings("deprecation")
	public Sub_Info_Player(ProxiedPlayer p, String[] args, BungeeRange main) {
		if(Player_Account.playExists(uuidfetcher.getUUID(args[0]).toString())) {
			String UUID = uuidfetcher.getUUID(args[0]).toString();
			p.sendMessage("§3<============>§2Info§3<============>");
			p.sendMessage("§cName: §3" + Player_Account.getName(UUID));
			p.sendMessage("§cUUID: §3" + UUID);
			p.sendMessage("§cIP: §3" + Player_Account.getIP(UUID));
			p.sendMessage("§cWarns: §3" + Player_Account.getWarns(UUID));
			p.sendMessage("§cKicks: §3" + Player_Account.getKicks(UUID));
			p.sendMessage("§cBans: §3" + Player_Account.getBans(UUID));
			p.sendMessage("§cGebant: §2 Nein");
			p.sendMessage("§3<==============================>");
		}else {p.sendMessage(Utils.prefix + "§cDer Spieler ist nicht in der Datenbank aufgeführt!");}
	}
}
