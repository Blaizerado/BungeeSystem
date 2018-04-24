package de.unknown.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UnBanUpDatePermaPlayer extends Thread{
	String pUUID;
	ProxiedPlayer t;
	public UnBanUpDatePermaPlayer(String UUID,ProxiedPlayer p) {pUUID = UUID; t = p;}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Unban Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM PermaBan WHERE UUID='"+pUUID+"' ORDER BY ban_active='1'");
			if(rs.next()) {
				if(rs.getString("UUID") != null) {
					BungeeRange.my.update("UPDATE PermaBan SET ban_active='0' WHERE UUID='"+pUUID+"' ORDER BY ban_active='1'");
				}else {t.sendMessage(Utils.prefix + "§cAchtung der Spieler wurde nicht gefunden!"); Thread.sleep(100); return;}
			}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
