package de.unknown.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.unknown.bungeecord.BungeeRange;

public class Player_Account {
	public static boolean playExists(String UUID) {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID= '" + UUID +"'");
			if(rs.next()) {
				return rs.getString("UUID") !=null;
			}
			return false;
		} catch (SQLException e) {}
		return false;
	}
	
	public static void createPlayer(String UUID,String Name,String Ip) {
		if(!playExists(UUID)) {
			BungeeRange.my.update("INSERT INTO Account(UUID,Name,player_kick,player_bans,player_warns,player_ip,groups) VALUES ('" + UUID +"','"+Name+"','0','0','0','"+Ip+"','0');");
		}
	}
	
	public static void updateName(String UUID,String Name) {
		if(playExists(UUID)){
			BungeeRange.my.update("UPDATE User SET Name='"+Name+"' WHERE UUID='"+UUID+"'");
		}else {}
	}
	
	public static Integer getRang(String UUID) {
		int s = 0;
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID='"+UUID+"'");
			if(rs.next()) {
				s = rs.getInt("groups");
			}else {}
		}catch(Exception e) {}
		return s;
	}
	
	public static String getName(String UUID) {
		String Lau = null;
		if(playExists(UUID)) {
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID= '" + UUID +"'");
				if((!rs.next()) || (String.valueOf(rs.getString("Name")) == null));
				Lau = rs.getString("Name");
			} catch (SQLException e) {}
		}
		return Lau;
	}
	
	public static String getIP(String UUID) {
		String Lau = null;
		if(playExists(UUID)) {
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID= '" + UUID +"'");
				if((!rs.next()) || (String.valueOf(rs.getString("player_ip")) == null));
				Lau = rs.getString("player_ip");
			} catch (SQLException e) {}
		}
		return Lau;
	}
	
	public static int getWarns(String UUID) {
		int warns = 0;
		if(playExists(UUID)) {
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID= '" + UUID +"'");
				if((!rs.next()) || (String.valueOf(rs.getString("player_warns")) == null));
				warns = rs.getInt("player_warns");
			} catch (SQLException e) {}
		}
		return warns;
	}
	
	public static int getBans(String UUID) {
		int warns = 0;
		if(playExists(UUID)) {
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID= '" + UUID +"'");
				if((!rs.next()) || (String.valueOf(rs.getString("player_bans")) == null));
				warns = rs.getInt("player_bans");
			} catch (SQLException e) {}
		}
		return warns;
	}
	
	public static int getKicks(String UUID) {
		int warns = 0;
		if(playExists(UUID)) {
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID= '" + UUID +"'");
				if((!rs.next()) || (String.valueOf(rs.getString("player_kick")) == null));
				warns = rs.getInt("player_kick");
			} catch (SQLException e) {}
		}
		return warns;
	}
	
}
