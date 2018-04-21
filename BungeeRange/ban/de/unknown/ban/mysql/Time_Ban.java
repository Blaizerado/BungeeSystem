package de.unknown.ban.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.unknown.bungeecord.BungeeRange;

public class Time_Ban extends Thread{
	
	String reason;
	String UUID;
	String id;
	Long time;
	String banner;
	String bannername;
	String tbannername;
	String bUUID;
	int bans;
	public Time_Ban(String Ban, String UU,String ID, Long t, String Reason, String pname, String bname, String bUU) {
		reason = Reason;
		UUID = UU;
		id = ID;
		time = t;
		banner = Ban;
		bannername = pname;
		tbannername = bname;
		bUUID = bUU;
	}
	
	@Override
	public void run() {	
		System.out.println("Ban Thread wird gestartet!");
		ResultSet rs = BungeeRange.my.query("SELECT * FROM Account WHERE UUID='"+UUID+"'");
		try {
			if(rs.next()) {
				bans = rs.getInt("player_bans")+1;
			}
		} catch (SQLException e) {}
		BungeeRange.my.update("INSERT INTO TimeBan(UUID,ban_reason,ban_target,ban_activ,ban_worker,ban_time,banner,tbanner,ban_id) VALUES ('"+UUID+"','"+reason+"','"+UUID+"','1','"+bUUID+"','"+time+"','"+bannername+"','"+tbannername+"','"+id+"');");
		BungeeRange.my.update("UPDATE Account SET player_bans='"+bans+"' WHERE UUID='"+UUID+"'");
		try {Thread.sleep(100); System.out.println("Ban Thread wird geschlossen!");} catch (InterruptedException e){}
		super.run();
	}
	
}
