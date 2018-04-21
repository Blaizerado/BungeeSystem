package de.unknown.ban.kickjoin;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class KickJoinTime extends Thread{
	String id;
	ProxiedPlayer kick;
	public KickJoinTime(ProxiedPlayer p, String UUID) {
		id = UUID;
		kick = p;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Ban Thread wird gestartet!");
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM TimeBan WHERE UUID='"+id+"' ORDER BY ban_activ='1'");
			while(rs.next()) {
				if(new Date().getTime() > rs.getLong("ban_time")) {
					BungeeRange.my.update("UPDATE TimeBan Set ban_activ='0' WHERE UUID='"+id+"' ORDER BY ban_activ='0'");
					kick.sendMessage("§cAchtung, du bist nun entbannt, jedoch solltest du auf passen!");
					Thread.sleep(100);
					return;
				}else {
					kick.disconnect("§3Achtung §c"+kick.getName()+"§3, du wurdest vom Minup.net\nNetzwerk gebannt!" 
					+ "\n\n§3Grund: §c" + rs.getString("ban_reason") + "\n§3Admin: §c" + rs.getString("banner")
					+ "\n§3Bis: §3" + df.format(rs.getLong("ban_time")) + "\n§3BanID: §3" + rs.getString("ban_id")
					+ "\n\n§3Du denkst das der Bann nicht rechtens ist ? \n dan melde dich in unserem Forum: §ewww.mineup.net!");
					Thread.sleep(100);
					return;
				}
			}
			Thread.sleep(100);
			System.out.println("Ban Thread wird geschlossen!");
		}catch (Exception e) {}
	}
	
}
