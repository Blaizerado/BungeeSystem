package de.unknown.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Stats extends Thread{
	ProxiedPlayer p;
	public MySQL_Stats(ProxiedPlayer t) {
		p = t;
	}
	
	@Override
	public void run() {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Stats WHERE UUID='"+uuidfetcher.getUUID(p.getName()).toString()+"'");
			if(!rs.next()) {
				BungeeRange.my.update("INSERT INTO Stats(UUID,stars,chats) VALUES('"+uuidfetcher.getUUID(p.getName()).toString()+"','0','0');");
			}
			Thread.sleep(100);
		}catch (Exception e) {}
	}
}
