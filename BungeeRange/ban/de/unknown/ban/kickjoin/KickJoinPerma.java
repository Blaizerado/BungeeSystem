package de.unknown.ban.kickjoin;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class KickJoinPerma extends Thread{
	ProxiedPlayer kick;
	String UID;
	public KickJoinPerma(ProxiedPlayer p, String UUID) {
		kick = p;
		UID = UUID;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			System.out.println("Ban Thread gestartet!");
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			ResultSet rs = BungeeRange.my.query("SELECT * FROM PermaBan WHERE UUID='"+UID+"' ORDER BY ban_active='1'");
			while (rs.next()) {
				kick.disconnect("§3Achtung §c" + kick.getName() + "§3,\nDu wurdest Permanet vom MineUp.net Netzwerk gebannt!"
				+ "\n\n§3Grund: §c" + rs.getString("ban_reason") + "\n§3Admin: §c" + rs.getString("ban_bname") + "\n§3Gebannt: §c" + df.format(rs.getLong("ban_create"))
				+ "\n§3BannID: §c" + rs.getString("ban_id") + "\n\nDu denkst der Bann ist nicht rechtens? \nDann melde dich auf unseren Forum: §ewww.mineup.net!");
				Thread.sleep(100);
			}
			Thread.sleep(100);
			System.out.println("Ban Thread wird geschlossen!");
		}catch (Exception e) {}
	}
}
