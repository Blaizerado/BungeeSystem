package de.unknown.support;

import java.sql.ResultSet;


import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Support_MySQL_SetStars extends Thread{
	String UUID;
	ProxiedPlayer p;
	Integer starts;
	public Support_MySQL_SetStars(ProxiedPlayer t, String UU,Integer c) {
		UUID = UU;
		p = t;
		starts = c;
	}
	
	@Override
	public void run() {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Stats WHERE UUID='"+UUID+"'");
			if(rs.next()) {
				Integer i = rs.getInt("stars")+starts;
				BungeeRange.my.update("UPDATE Stats SET stars='"+i+"'");
				for(int t = 0; t<150; t++) {
					p.sendMessage(" ");
				}
				p.sendMessage(Utils.prefix + "§3Danke das du uns helfen möchtest, unsere Supporter zu bewerten!");
			}else {p.sendMessage(Utils.prefix + "§cAchtung, der Spieler hat noch keine Livechat geführt!");}
		}catch (Exception e) {}
	}
}
