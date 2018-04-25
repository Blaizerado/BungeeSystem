package de.unknown.friend.mysql;


import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Deny extends Thread{
	String UUID;
	String tUUID;
	ProxiedPlayer p;
	String name;
	private BungeeRange main;
	public MySQL_Deny(ProxiedPlayer t, String tu, String pu, BungeeRange bungeeRange, String tname) {
		UUID = pu;
		tUUID = tu;
		p = t;
		name = tname;
		this.main = bungeeRange;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Friend Thread wrid gestartet!");
		try {
			if(!checkRequest(tUUID)) {p.sendMessage(Utils.prefix + "§cDer Spieler hat dir keine Anfrage gesendet!"); Thread.sleep(100); return;}
			BungeeRange.my.update("DELETE FROM Friend_Request WHERE player_target='"+tUUID+"' ORDER BY player_requestet='"+UUID+"'");
			p.sendMessage(Utils.prefix + "§3Du hast den Spieler §e" + name + "§3 abgelehnt!");
			if(this.main.getProxy().getPlayer(name) != null) {
				this.main.getProxy().getPlayer(name).sendMessage(Utils.prefix + "§cLeider hat der Spieler §3" + p.getName() + "§c deine Anfrage abgelehnt!");
			}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
	
	public Boolean checkRequest(String UU) {
		ResultSet rs = BungeeRange.my.query("SELECT * FROM Friend_Request WHERE player_target='"+UU+"'");
		try {
			while (rs.next()) {
				if(rs.getString("player_requestet").equalsIgnoreCase(UUID)) {
					return true;
				}
			}
		}catch(Exception e) {}
		return false;
	}
	
}
