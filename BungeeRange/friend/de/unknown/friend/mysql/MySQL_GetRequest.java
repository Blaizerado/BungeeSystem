package de.unknown.friend.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GetRequest extends Thread{
	String UU;
	ProxiedPlayer t;
	public MySQL_GetRequest(ProxiedPlayer p, String UUID) {
		UU = UUID;
		t = p;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Friend Thread wird gestartet!");
		try {
			int i = 0;
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Friend_Request WHERE player_target='"+UU+"'");
			while (rs.next()) {
				i++;
			}
			if(i == 0) {
				t.sendMessage(Utils.prefix + "§3Du hast zurzeit keine Anfragen!");
			}else {t.sendMessage(Utils.prefix + "Du hast zurzeit §e" + i + "§3 Anfrage/en");}
			Thread.sleep(100);
		} catch (Exception e) {	}
	}
}
