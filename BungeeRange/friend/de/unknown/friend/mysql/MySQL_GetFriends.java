package de.unknown.friend.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GetFriends extends Thread{
	String pUUID;
	ProxiedPlayer p;
	public MySQL_GetFriends(String UUID, ProxiedPlayer t) {
		pUUID = UUID;
		p = t;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Friend Thread wird gestartet!");
		try {
			ArrayList<Integer>friend = new ArrayList<>();
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Friend_Friend WHERE UUID1='"+pUUID+"' UNION SELECT * FROM Friend_Friend WHERE UUID2='"+pUUID+"'");
			while (rs.next()) {
				friend.add(rs.getInt("id"));
			}
			if(!friend.isEmpty()) {
				p.sendMessage(Utils.prefix + "Du hast zur Zeit §e" + friend.size() + "§3 Freund/e");
			}else {
				p.sendMessage(Utils.prefix + "Du hast zur Zeit noch keine Freunde!");
			}
			Thread.sleep(100);
		} catch (Exception e) {}
	}
}
