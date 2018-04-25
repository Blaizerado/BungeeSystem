package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GroupAddPermission extends Thread{
	String perms;
	String groups;
	ProxiedPlayer p;
	public MySQL_GroupAddPermission(String perm, String group, ProxiedPlayer t) {
		perms = perm;
		groups = group;
		p = t;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Uperms Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group WHERE Name='"+groups+"'");
			if(rs.next()) {
				BungeeRange.my.update("INSERT INTO UPerms_Permission(group_id,perms) VALUES('"+rs.getInt("id")+"','"+perms+"');");
				p.sendMessage(Utils.prefix + "§3Du hast den Permission §3" + perms + "§3 zu der Gruppe §e" + groups + "§3 geaddet!");
			}else {p.sendMessage(Utils.prefix + "§cAchtung, die Grupper gibt es nicht!"); Thread.sleep(100); return;}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
