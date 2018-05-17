package de.unknown.perms.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_GetExGroup extends Thread{
	ProxiedPlayer p;
	String groups;
	ArrayList<String>Perms = new ArrayList<>();
	public MySQL_GetExGroup(ProxiedPlayer t) {
		p = t;
	}
	
	@Override
	public void run() {
		ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Group WHERE id='"+Player_Account.getRang(uuidfetcher.getUUID(p.getName()).toString())+"'");
		System.out.println("UPerms Update Thread gestartet!");
		try {
			if(rs.next()) {
				groups = rs.getString("Group_Copy");
			}else {System.out.println("Achtung, die default gruppe kann nicht erben!!"); return;}
			Thread.sleep(100);
			Sub_String(groups);
		}catch(Exception e) {}
		super.run();
	}
	
	
	public void Sub_String(String s) {
		String[] value = s.split(",");
		ArrayList<Integer>ids = new ArrayList<>();
		for(String v : value) {
			if(s!=null) {
				ids.add(Integer.valueOf(s));
			}
		}
		if(ids.size() != 0) {
			getPerms(ids);
		}
	}
	
	public void getPerms(ArrayList<Integer> id) {
		for(Integer i : id) {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Permission WHERE group_id='"+i+"'");
			try {
				while(rs.next()) {
					Perms.add(rs.getString("perms"));
				}
			}catch(Exception e) {}
			System.out.println(Perms + "permission gefunden!");
			setPermission();
		}
	}
	
	public void setPermission() {
		for(String Perms : Perms) {
			p.setPermission(Perms, true);
		}
	}
	
}
