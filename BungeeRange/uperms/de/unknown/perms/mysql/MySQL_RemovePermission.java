package de.unknown.perms.mysql;

import java.sql.ResultSet;
import java.util.Collection;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.message.SendUpDateMessage;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_RemovePermission extends Thread{
	ProxiedPlayer t;
	String g;
	String perms;
	Integer id;
	public MySQL_RemovePermission(ProxiedPlayer p, String group, String perm) {
		g = group;
		t = p;
		perms = perm;
	}
	
	@Override
	public void run() {
		System.out.println("UPerms Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM UPerms_Permission WHERE perms='"+perms+"'");
			if(rs.next()) {
				BungeeRange.my.update("DELETE FROM UPerms_Permission WHERE perms='"+perms+"'");
				id = rs.getInt("group_id");
				SendUpDateMessage.sendData("UPDATE", perms, String.valueOf(id), "REMOVE");
				t.sendMessage(Utils.prefix + "§3Du hast die Permission §e"+perms+"§3 für die Gruppe §e" + g + "§3 gelöscht!");
			}else {t.sendMessage(Utils.prefix + "§cAchtung, die Permission wurde nicht eingetragen!");}
			Thread.sleep(100);
			removePermission();
		}catch(Exception e) {e.printStackTrace();}
		
		System.out.println("start");
	}
	
	
	public void removePermission() {
		try {
			for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
				if(Player_Account.getRang(uuidfetcher.getUUID(p.getName()).toString()) == id) {
					reset(p, p.getPermissions());
				}else {continue;}
			}
		} catch (Exception e) {}
	}
	
	public void reset(ProxiedPlayer p, Collection<String> collection) {
		String Permission = collection.toString();
		Permission = Permission.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace(" ", "");
		String[] rdy = Permission.split(",");
		for(String execute : rdy) {
			p.setPermission(execute, false);
			System.out.println(execute + " removed");
			MySQL_SetPermission b = new MySQL_SetPermission(p);
			b.start();
		}
	}
	
}
