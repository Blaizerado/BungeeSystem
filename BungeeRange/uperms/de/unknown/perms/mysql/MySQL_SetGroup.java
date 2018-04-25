package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_SetGroup extends Thread{
	String pUUID;
	ProxiedPlayer t;
	String name;
	String plname;
	public MySQL_SetGroup(ProxiedPlayer p, String UUID, String group, String pname) {
		t = p;
		pUUID = UUID;
		name = group;
		plname = pname;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Uperms Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group WHERE Name='"+name+"'");
			if(rs.next()) {
				BungeeRange.my.update("UPDATE Account SET groups='"+rs.getInt("id")+"' WHERE UUID='"+pUUID+"'");
				t.sendMessage(Utils.prefix + "§3 Du hast den Spieler §e" + plname + "§3 in die Gruppe §e" + name + "§3 gesetzt!");
			}else {t.sendMessage(Utils.prefix + "§cAchtung, die Gruppe wurde nicht gefunden!");}
			Thread.sleep(100);
		}catch(Exception e) {e.printStackTrace();}
	}
}
