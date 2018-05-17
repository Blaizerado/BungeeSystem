package de.unknown.warn.mysql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


import de.unknown.ban.mysql.Perma_Ban;
import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_CreateWarn extends Thread{
	ProxiedPlayer p;
	String cUUID;
	String reason;
	public MySQL_CreateWarn(ProxiedPlayer t, String UUID, String re) {
		cUUID = UUID;
		p = t;
		reason = re;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			Integer warns = Player_Account.getWarns(cUUID) +1;
			String name = uuidfetcher.getName(UUID.fromString(cUUID));
			BungeeRange.my.update("INSERT INTO Warn(UUID,warn_reason,warn_warner,warn_targetname,warn_warnername) VALUES('"+cUUID+"','"+reason+"','"+uuidfetcher.getUUID(p.getName()).toString()+"','"+name+"','"+p.getName()+"');");
			BungeeRange.my.update("UPDATE Account Set player_warns='"+warns+"' WHERE UUID='"+cUUID+"'");
			ProxiedPlayer pr = BungeeCord.getInstance().getPlayer(name);
			if(warns >= 3) {
				char[] chars  = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
				int length = 12;
				StringBuilder sb = new StringBuilder();
				Random random = new Random();
				for (int i = 0; i < length; i++) {
				    char c = chars[random.nextInt(chars.length)];
				    sb.append(c);
				}
				String output = sb.toString();
				Perma_Ban b = new Perma_Ban(cUUID, "Console", "Du wurdest zu oft verwarnt!", "Console",name,
						output, new Date().getTime(), true, false);
				b.start();
				p.sendMessage(Utils.prefix + "§cAchtung, der Spieler §e" + name + "§c wurde soebend vom Server gebannt!");
			
				if(pr != null) {
					SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
					pr.disconnect("§3Achtung §c" + pr.getName() +"§3,\n du wurdest soebend §cPermanet§3 vom Mineup.net\nNetzwerk gebannt!"
					+ "\n\n§3Grund: §c" + reason + "\n§3Admin: §c" + p.getName() + "\n§3Gebannt: §c" + df.format(new Date().getTime()) + "\n§3BannID: §c" + output
					+ "\n\nDu denkst der Bann ist nicht rechtens?\nDann melde dich in useren Forum: §ewww.MineUp.net!");
				}
			}
			p.sendMessage(Utils.prefix + "§cDer Warn gegen den Spieler §e" + name + "§c wurde erstellt!");
			if(pr != null) {
				p.sendMessage(Utils.prefix + "§cAchtung du wurdest von §e" + p.getName() + "§c verwarnt!");
				p.sendMessage(Utils.prefix + "§cGrund: §e" + reason);
			}
			Thread.sleep(100);
		} catch (Exception e) {}
	}
}
