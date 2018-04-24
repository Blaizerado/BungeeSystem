package de.unknown.ban.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GetMySQLBanPerma extends Thread{
	String UUID;
	ProxiedPlayer target;
	public GetMySQLBanPerma(String pUUID, ProxiedPlayer p) {
		UUID = pUUID;
		target = p;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Ban Thread gestartet!");
		ArrayList<Integer>Id = new ArrayList<>();
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM PermaBan WHERE UUID='"+UUID+"'");
			while (rs.next()) {
				Id.add(rs.getInt("id"));
			}
			if(!Id.isEmpty()) {
				target.sendMessage(Utils.prefix + "§c zu diesen Spieler gibt es folgene Einträge!");
				target.sendMessage("§c" + Id.toString().replace("[", "").replace("]", ""));
			}else {target.sendMessage(Utils.prefix + "§cDieser Spieler hat noch keine Einträge!");}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
	
}
