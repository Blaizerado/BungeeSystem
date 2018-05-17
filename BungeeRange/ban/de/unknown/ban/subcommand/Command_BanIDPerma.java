package de.unknown.ban.subcommand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import de.unknown.ban.mysql.Perma_Ban;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_BanIDPerma {
	ProxiedPlayer p;
	String t;
	String r;
	String UUID;
	String pUUID;
	public Command_BanIDPerma(ProxiedPlayer a, String c, String b) {
		p = a;
		t = c;
		r = b;
		UUID = uuidfetcher.getUUID(c).toString();
		pUUID = uuidfetcher.getUUID(a.getName()).toString();
	}
	
	public void banID() {
		if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§cAchtung, der Spieler ist nicht in der Datenbank!"); return;}
		ProxiedPlayer target = ProxyServer.getInstance().getPlayer(t);
		char[] chars  = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		int length = 12;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		if(target != null) {
			Perma_Ban b = new Perma_Ban(UUID, pUUID, r, p.getName(), t, output, new Date().getTime(), false, false);
			b.start();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			target.disconnect("§3Achtung §c" + target.getName() +"§3,\n du wurdest soebend §cPermanet§3 vom Mineup.net\nNetzwerk gebannt!"
			+ "\n\n§3Grund: §c" + r + "\n§3Admin: §c" + p.getName() + "\n§3Gebannt: §c" + df.format(new Date().getTime()) + "\n§3BannID: §c" + output
			+ "\n\nDu denkst der Bann ist nicht rechtens?\nDann melde dich in useren Forum: §ewww.MineUp.net!");
		}else {
			Perma_Ban b = new Perma_Ban(UUID, pUUID, r, p.getName(), t, output, new Date().getTime(), false, false);
			b.start();
		}
		p.sendMessage(Utils.prefix + "§cDu hast den Spieler §e" + t + "§c vom Server gebannt!");
	}
}
