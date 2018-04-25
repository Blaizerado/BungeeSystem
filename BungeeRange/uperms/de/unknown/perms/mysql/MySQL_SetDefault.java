package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_SetDefault extends Thread{
	ProxiedPlayer p;
	String g;
	public MySQL_SetDefault(ProxiedPlayer t, String group) {
		p = t;
		g = group;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Uperms Thread wird gestartet!");
		try {
			BungeeRange.my.update("UPDATE UPerms_Group SET defaults='0' WHERE id='"+getDefaultID()+"'");
			BungeeRange.my.update("UPDATE UPerms_Group SET defaults='1' WHERE Name='"+g+"'");
			p.sendMessage(Utils.prefix + "§3Die Grupper §3" + g + "§3 ist nun die §eDefault§3 Gruppe!");
			Thread.sleep(100);
		}catch(Exception e) {}
	}
	
	public int getDefaultID() {
		int i = 0;
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group WHERE defaults='1'");
			if(rs.next()) {
				i = rs.getInt("id");
			}
		} catch (Exception e) {}
		return i;
	}
	
}
