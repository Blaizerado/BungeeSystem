package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_RemovePermission extends Thread{
	ProxiedPlayer t;
	String g;
	String perms;
	public MySQL_RemovePermission(ProxiedPlayer p, String group, String perm) {
		g = group;
		t = p;
		perms = perm;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("UPerms Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Permission WHERE perms='"+perms+"'");
			if(rs.next()) {
				BungeeRange.my.update("DELETE FROM UPerms_Permission WHERE perms='"+perms+"'");
				t.sendMessage(Utils.prefix + "§3Du hast die Permission §e"+perms+"§3 für die Gruppe §e" + g + "§3 gelöscht!");
			}else {t.sendMessage(Utils.prefix + "§cAchtung, die Permission wurde nicht eingetragen!");}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
