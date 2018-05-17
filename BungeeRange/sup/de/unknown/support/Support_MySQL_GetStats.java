package de.unknown.support;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Support_MySQL_GetStats extends Thread{
	String UU;
	ProxiedPlayer p;
	public Support_MySQL_GetStats(ProxiedPlayer t, String UUID) {
		UU = UUID;
		p = t;
	}
	
	@Override
	public void run() {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Stats WHERE UUID='"+UU+"'");
			if(rs.next()) {
				Double starts = rs.getDouble("stars") / rs.getDouble("chats");
				long l = Math.round(starts*10)/10;
				p.sendMessage(Utils.prefix + "§3Der Supporter hat eine Bewertung von: §c" + l + "§3 Stern/en");
			}else {p.sendMessage(Utils.prefix + "§cDer Supporter hat noch keine Livechat geführt");}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
