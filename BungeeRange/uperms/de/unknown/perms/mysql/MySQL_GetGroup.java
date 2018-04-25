package de.unknown.perms.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GetGroup extends Thread{
	ProxiedPlayer p;
	public MySQL_GetGroup(ProxiedPlayer t) {
		p = t;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Uperms Thread wird gestartet!");
		try {
			ArrayList<String>IDs = new ArrayList<>();
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group ORDER BY Server_Group='0'");
			while (rs.next()) {
				IDs.add(rs.getString("Name")+"("+rs.getInt("id")+")");
			}
			p.sendMessage("§cGruppen:");
			p.sendMessage("§3" + IDs.toString().replace("[", "").replace("]", ""));
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
