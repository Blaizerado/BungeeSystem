package de.unknown.ban.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;

public class BanCheckPlayer {
	public static boolean isBanned(String UUID) {
		ResultSet rs = BungeeRange.my.query("SELECT * FROM TimeBan WHERE UUID='"+UUID+"' ORDER BY ban_activ='1'");
		try {
			while (rs.next()) {
				if(rs.getInt("ban_activ") == 1) {return true;}
			}
		}catch (Exception e) {}
		return false;
	}
	
	public static boolean isPermaBan(String UUID) {
		ResultSet rs = BungeeRange.my.query("SELECT * FROM PermaBan WHERE UUID='"+UUID+"' ORDER BY ban_active='1'");
		try {
			while (rs.next()) {
				if(rs.getInt("ban_active") == 1) {return true;}
			}
		} catch (Exception e) {}
		return false;
	}
}
