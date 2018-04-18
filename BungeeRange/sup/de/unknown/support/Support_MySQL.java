package de.unknown.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import de.unknown.bungeecord.BungeeRange;

public class Support_MySQL {
	public static void createReport(String UUID, String Reason, String Target) {
		BungeeRange.my.update("INSERT INTO Support(UUID,support_reason,support_target,support_activ,support_worker,support_create) VALUES ('"+UUID+"','"+Reason+"','"+Target+"','1','frei','"+new Date().getTime()+"');");
		//BungeeRange.my.update("INSERT INTO Support('UUID','support_reason','support_target,player_bans,player_warns,player_ip) VALUES ('" + UUID +"','"+Name+"','0','0','0','"+Ip+"');");
	}
	
	public static ArrayList<Integer> getOpenTickets() {
		ArrayList<Integer>ticket = new ArrayList<>();
		ResultSet rs = BungeeRange.my.query("SELECT id FROM Support ORDER BY support_activ='1'");
		try {
			while (rs.next()) {
				ticket.add(rs.getInt("id"));
			}
		} catch (SQLException e) {}
		return ticket;
	}
	
}
