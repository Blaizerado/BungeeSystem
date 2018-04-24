package de.unknown.ban.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GetMySQLBanTime extends Thread{
	String PUUID;
	ProxiedPlayer target;
	public GetMySQLBanTime(ProxiedPlayer p, String UUID) {
		PUUID = UUID;
		target = p;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Ban Thread wird gestartet!");
		ArrayList<Integer>Id = new ArrayList<>();
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM TimeBan WHERE UUID='"+PUUID+"'");
			while (rs.next()) {
				Id.add(rs.getInt("id"));
			}
			if(!Id.isEmpty()) {
				target.sendMessage(Utils.prefix + "§cZu diesem Spieler gibt es folgene Einträge!");
				target.sendMessage("§c"+Id.toString().replace("[", "").replace("]", ""));
			}else {
				target.sendMessage(Utils.prefix + "§cDieser Spieler ist noch nicht aufgefallen!");
			}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
	
}
