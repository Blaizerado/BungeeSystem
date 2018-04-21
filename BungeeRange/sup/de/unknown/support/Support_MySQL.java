package de.unknown.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import de.unknown.bungeecord.BungeeRange;

public class Support_MySQL extends Thread{
	
	public static void createReport(String UUID, String Reason, String Target,String pName, String tName) {
		BungeeRange.my.update("INSERT INTO Support(UUID,support_reason,support_target,support_activ,support_worker,support_create,TName,NHName) VALUES ('"+UUID+"','"+Reason+"','"+Target+"','1','frei','"+new Date().getTime()+"','"+pName+"','"+tName+"');");
		//BungeeRange.my.update("INSERT INTO Support('UUID','support_reason','support_target,player_bans,player_warns,player_ip) VALUES ('" + UUID +"','"+Name+"','0','0','0','"+Ip+"');");
	}
	
	public static boolean ticketCreate(Integer id) {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Support WHERE id= '" + id +"'");
			if(rs.next()) {
				return rs.getString("support_reason") !=null;
			}
			return false;
		} catch (SQLException e) {}
		return false;
	}
	
	public static void closeTicket(Integer id) {
		BungeeRange.my.update("UPDATE Support SET support_activ='2' WHERE id='"+id+"'");
	}
	
	public static ArrayList<Integer> getOpenTickets() {
		ArrayList<Integer>ticket = new ArrayList<>();
		ResultSet rs = BungeeRange.my.query("SELECT * FROM Support ORDER BY support_activ='1'");
		try {
			while (rs.next()) {
				if(rs.getInt("id") == 1) {
					ticket.add(rs.getInt("id"));
				}
			}
		} catch (SQLException e) {}
		return ticket;
	}
	
}
