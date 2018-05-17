package de.unknown.support;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Support_MySQL_SetStats extends Thread{
	ProxiedPlayer p;
	String UUID;
	public Support_MySQL_SetStats(ProxiedPlayer t, String UU) {
		p = t;
		UUID = UU;
	}
	
	@Override
	public void run() {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Stats WHERE UUID='"+UUID+"'");
			if(rs.next()) {
				Integer i = rs.getInt("chats") +1;
				BungeeRange.my.update("UPDATE Stats SET chats='"+i+"' WHERE UUID='"+UUID+"'");
			}else {p.sendMessage(Utils.prefix + "§cAchtung, der Supporter hat noch keinen Livechat geführt"); return;}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
