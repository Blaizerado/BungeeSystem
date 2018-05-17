package de.unknown.warn.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_ReadTicket extends Thread{
	ProxiedPlayer p;
	Integer id;
	public MySQL_ReadTicket(ProxiedPlayer t, Integer i) {
		p = t;
		id = i;
	}
	
	@Override
	public void run() {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Warn WHERE id='"+id+"'");
			if(rs.next()) {
				p.sendMessage("§3Spieler: §c" + rs.getString("warn_targetname"));
				p.sendMessage("§3UUID: §c" + rs.getString("UUID"));
				p.sendMessage("§3Ersteller: §c" + rs.getString("warn_warnername"));
				p.sendMessage("§3Grund: §c" + rs.getString("warn_reason"));
			}
			Thread.sleep(100);
		} catch (Exception e) {}
	}
}
