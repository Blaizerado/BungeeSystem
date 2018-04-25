package de.unknown.login.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_UnBlock extends Thread{
	String pUUID;
	ProxiedPlayer t;
	String name;
	public MySQL_UnBlock(String UUID, String pname, ProxiedPlayer p) {
		pUUID = UUID;
		t = p;
		name = pname;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Login Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Login WHERE UUID='"+pUUID+"'");
			if(rs.next()) {
				BungeeRange.my.update("UPDATE Login SET login_activ='1' WHERE UUID='"+pUUID+"'");
				t.sendMessage(Utils.prefix + "§cDer Login für den Spieler §e" + name + "§c wurde freigegeben!");
				Thread.sleep(100);
			}else {t.sendMessage(Utils.prefix + "§cAchtung, der Account wurde nicht gefunden!"); Thread.sleep(100); return;}
		}catch(Exception e) {}
	}
}
