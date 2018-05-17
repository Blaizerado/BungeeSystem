package de.unknown.warn.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GetWarnsByPlayer extends Thread{
	ProxiedPlayer p;
	String cUUID;
	public MySQL_GetWarnsByPlayer(ProxiedPlayer t, String UU) {
		p = t;
		cUUID = UU;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			ArrayList<Integer>ids = new ArrayList<>();
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Warn WHERE UUID='"+cUUID+"'");
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
			if(!ids.isEmpty()) {
				p.sendMessage(Utils.prefix + "Der Spieler §e" + uuidfetcher.getName(UUID.fromString(cUUID)) + "§3 hat folgende Wanrs");
				p.sendMessage("§c" + ids.toString().replace("[", "").replace("]", ""));
			}else {p.sendMessage(Utils.prefix + "§3Der Spieler §e" + uuidfetcher.getName(UUID.fromString(cUUID)) + "§3 hat noch keine Warns");}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
