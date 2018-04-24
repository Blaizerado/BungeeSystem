package de.unknown.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UnBanUpDatePermaID extends Thread{

	String ID;
	ProxiedPlayer p;
	public UnBanUpDatePermaID(String id, ProxiedPlayer t) {
		ID = id;
		p = t;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Unban Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM PermaBan WHERE ban_id='"+ID+"' ORDER BY ban_active='1'");
			if(rs.next()) {
				if(rs.getString("UUID") != null) {
					BungeeRange.my.update("UPDATE PermaBan SET ban_active='0' WHERE ban_id='"+ID+"' ORDER BY ban_active='1'");
				}else {p.sendMessage(Utils.prefix + "§cAchtung der Spieler wurde nicht gefunden!"); Thread.sleep(100); return;}
			}
			Thread.sleep(100);
		}catch(Exception e) {e.printStackTrace();}
	}
	
}
