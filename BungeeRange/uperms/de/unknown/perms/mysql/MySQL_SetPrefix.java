package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_SetPrefix extends Thread{
	ProxiedPlayer p;
	String group;
	String prefix;
	public MySQL_SetPrefix(ProxiedPlayer t, String g, String c) {
		p = t;
		group = g;
		prefix = c;
	}
	
	@Override
	public void run() {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group WHERE Name='"+group+"'");
			if(rs.next()) {
				BungeeRange.my.update("UPDATE UPerms_Group SET Prefix='"+prefix+"' WHERE Name='"+group+"'");
				p.sendMessage(Utils.prefix + "§aDie Gruppe §e" + group + "§a hat nun die Prefix " + ChatColor.translateAlternateColorCodes('&', prefix));
			}else {p.sendMessage(Utils.prefix + "§cAchtung, die Gruppe wurde nicht gefunden!");}
			Thread.sleep(100);
		}catch(Exception e) {}
		super.run();
	}
}
