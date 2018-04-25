package de.unknown.perms.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_SetPermission extends Thread{ 
	ProxiedPlayer t;
	public MySQL_SetPermission(ProxiedPlayer p) {
		t = p;
	}
	
	@Override
	public void run() {
		System.out.println("Uperms Thread gestartet!");
		String UUID = uuidfetcher.getUUID(t.getName()).toString();
		try {
			if(Player_Account.getRang(UUID) >= 1) {
				ArrayList<String>perms = new ArrayList<>();
				ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Permission WHERE group_id='"+Player_Account.getRang(UUID)+"'");
				while (rs.next()) {
					perms.add(rs.getString("perms"));
				}
				for(String s : perms) {
					t.setPermission(s, true);
				}
			}else {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Permission WHERE group_id='"+getDefaultID()+"'");
				ArrayList<String> perms = new ArrayList<>();
				while (rs.next()) {
					perms.add(rs.getString("perms"));
				}
				System.out.println(getDefaultID());
				if(perms.isEmpty()) {System.out.println("Achtung es wurde keine default permission gefunden!"); Thread.sleep(100); return;}
				for(String p : perms) {
					t.setPermission(p, true);
					System.out.println("gesetzt");
				}
			}
			Thread.sleep(0);
		}catch(Exception e) {e.printStackTrace();}
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
