package de.unknown.perms.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_RemoveGroup extends Thread{
	ProxiedPlayer p;
	String pUUID;
	public MySQL_RemoveGroup(ProxiedPlayer t, String UUID) {
		p = t;
		pUUID = UUID;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Uperms Thread wird gestartet!");
		try {
			BungeeRange.my.update("UPDATE Account SET groups='"+getDefaultID()+"' WHERE UUID='"+pUUID+"'");
			p.sendMessage(Utils.prefix + "§cDer Spielr wurde aus seinen Rang entfernt!");
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
