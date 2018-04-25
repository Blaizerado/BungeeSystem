package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GroupCreate extends Thread{
	String gname;
	String er;
	ProxiedPlayer p;
	public MySQL_GroupCreate(ProxiedPlayer t,String name, String erbung) {
		gname = name;
		er = erbung;
		p = t;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("UPerms Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group WHERE Name='"+gname+"'");
			if(!rs.next()) {
				BungeeRange.my.update("INSERT INTO UPerms_Group(Name,Server_Group,Group_Copy,defaults) VALUES('"+gname+"','0','"+er+"','0');");
				p.sendMessage(Utils.prefix + "§3Du hast die Gruppe §3" + gname + "§3 erstellt!");
			}else {p.sendMessage(Utils.prefix + "§cDie Gruppe wurde bereits erstellt!");}
		}catch(Exception e) {}
	}
}
