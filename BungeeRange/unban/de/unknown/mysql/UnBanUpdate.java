package de.unknown.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UnBanUpdate extends Thread{
	String pUUID;
	ProxiedPlayer t;
	public UnBanUpdate(String UUID,ProxiedPlayer p) {pUUID = UUID; t = p;}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Unban Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM TimeBan WHERE UUID='"+pUUID+"' ORDER BY ban_activ='1'");
			if(rs.next()) {
				if(rs.getString("UUID") != null) {
					BungeeRange.my.update("UPDATE TimeBan SET ban_activ='0' WHERE UUID='"+pUUID+"' ORDER BY ban_activ='1'");
					t.sendMessage(Utils.prefix + "§c Der Spieler §e" + rs.getString("tbanner") + "§c wurde entbannt!");
				}else {t.sendMessage(Utils.prefix + "§cAchtung der Spieler wurde nicht gefunden!"); Thread.sleep(100); return;}
			}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
