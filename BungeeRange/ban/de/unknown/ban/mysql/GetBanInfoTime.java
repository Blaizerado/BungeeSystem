package de.unknown.ban.mysql;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GetBanInfoTime extends Thread{
	Integer ind;
	ProxiedPlayer p;
	public GetBanInfoTime(ProxiedPlayer target, Integer tid) {
		ind = tid;
		p = target;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Ban Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM TimeBan WHERE id='"+ind+"'");
			if(rs.next()) {if(rs.getString("UUID") == null) {p.sendMessage(Utils.prefix + "§cDer Ban wurde nicht gefunden!"); Thread.sleep(100); return;}}
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			p.sendMessage("§3Spieler: §c" + rs.getString("tbanner"));
			p.sendMessage("§3UUID: §c" + rs.getString("UUID"));
			p.sendMessage("§3Gebannt von: §c" + rs.getString("banner"));
			p.sendMessage("§3Bis: " + df.format(rs.getLong("ban_time")));
			p.sendMessage("§3BanId: §c" + rs.getString("ban_id"));
			p.sendMessage("§3Grund: §c" + rs.getString("ban_reason"));
			Thread.sleep(100);
			System.out.println("Ban Thread geschlossen!");
		}catch(Exception e) {}
	}
}
