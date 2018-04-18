package de.unknown.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.unknown.bungeecord.BungeeRange;


public class Profil_Settings {
	public static Boolean getWarning(int Profil) {
		int i =0;
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Settings WHERE profil= '"+ Profil +"'");
				if((!rs.next()) ||(String.valueOf(rs.getString("Wartung")) == null));
				i = rs.getInt("Wartung");
				if(i == 1) {
					return true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public static Boolean getParty(int Profil) {
		int i =0;
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Settings WHERE profil= '"+ Profil +"'");
				if((!rs.next()) ||(String.valueOf(rs.getString("Party")) == null));
				i = rs.getInt("Party");
				if(i == 1) {
					return true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public static Boolean getSupport(int Profil) {
		int i =0;
			try {
				ResultSet rs = BungeeRange.my.query("SELECT * FROM Settings WHERE profil= '"+ Profil +"'");
				if((!rs.next()) ||(String.valueOf(rs.getString("Support")) == null));
				i = rs.getInt("Support");
				if(i == 1) {
					return true;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
}
