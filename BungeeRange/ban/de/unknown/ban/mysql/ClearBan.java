package de.unknown.ban.mysql;

import de.unknown.bungeecord.BungeeRange;

public class ClearBan extends Thread{
	
	public ClearBan() {
		
	}
	
	@Override
	public void run() {
		System.out.println("Ban Thread wird gestartet!");
		BungeeRange.my.update("DELETE FROM TimeBan WHERE ban_activ='0'");
		BungeeRange.my.update("DELETE FROM PermaBan WHERE ban_active='0'");
		try{Thread.sleep(100);}catch(Exception e) {}
	}
}
