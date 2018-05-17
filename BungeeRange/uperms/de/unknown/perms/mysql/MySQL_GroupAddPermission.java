package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.message.SendUpDateMessage;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GroupAddPermission extends Thread{
	String perms;
	String groups;
	ProxiedPlayer p;
	Integer id;
	public MySQL_GroupAddPermission(String perm, String group, ProxiedPlayer t) {
		perms = perm;
		groups = group;
		p = t;
	}
	
	@Override
	public void run() {
		System.out.println("Uperms Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group WHERE Name='"+groups+"'");
			if(rs.next()) {
				id = rs.getInt("id");
				BungeeRange.my.update("INSERT INTO UPerms_Permission(group_id,perms) VALUES('"+rs.getInt("id")+"','"+perms+"');");
				SendUpDateMessage.sendData("UPDATE", perms, String.valueOf(id), "ADD");
				p.sendMessage(Utils.prefix + "§3Du hast den Permission §3" + perms + "§3 zu der Gruppe §e" + groups + "§3 geaddet!");
			}else {p.sendMessage(Utils.prefix + "§cAchtung, die Grupper gibt es nicht!"); Thread.sleep(100); return;}
			for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
				if(Player_Account.getRang(uuidfetcher.getUUID(p.getName()).toString()) == id ) {
					p.setPermission(perms, true);
				}
			}
			Thread.sleep(100);
		}catch(Exception e) {e.printStackTrace();}
	}
}
