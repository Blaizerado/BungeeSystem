package de.unknown.login.mysql;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Create extends Thread{
	String UUID;
	String password;
	String pname;
	ProxiedPlayer t;
	public MySQL_Create(ProxiedPlayer p, String pUUID, String pw, String name) {
		t = p;
		UUID = pUUID;
		password = pw;
		pname = name;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Login Thread wird gestartet!");
		try{
			BungeeRange.my.update("INSERT INTO Login(UUID,login_name,login_password,login_activ) VALUES ('"+UUID+"','"+pname+"','"+password+"','1')");
			t.sendMessage(Utils.prefix + "§3Du hast das Konto für den Spieler §e" + pname + "§3 erstellt!");
			t.sendMessage("§cPassword: " + password);
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
