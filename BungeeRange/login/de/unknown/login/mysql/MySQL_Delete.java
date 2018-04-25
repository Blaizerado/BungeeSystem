package de.unknown.login.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Delete extends Thread{
	String pUUID;
	ProxiedPlayer p;
	String name;
	public MySQL_Delete(ProxiedPlayer t, String UUID, String pname) {
		pUUID = UUID;
		p = t;
		name = pname;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Login Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Login WHERE UUID='"+pUUID+"'");
			if(rs.next()) {
				BungeeRange.my.update("DELETE FROM Login WHERE UUID='"+pUUID+"'");
				p.sendMessage(Utils.prefix + "§3Der Spieler §e" + name + "§3 wurde vom Login gelöscht!");
			}else {p.sendMessage(Utils.prefix + "§cDer Account wurde nicht gefunden!"); Thread.sleep(100); return;}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
