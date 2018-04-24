package de.unknown.ban.subcommand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import de.unknown.ban.mysql.Perma_Ban;
import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_Sub_Perma {

	@SuppressWarnings("deprecation")
	public Command_Sub_Perma(ProxiedPlayer p, String[] args, BungeeRange main) {
		ProxiedPlayer target = main.getProxy().getPlayer(args[1]);
		String UUID = uuidfetcher.getUUID(args[1]).toString();
		if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§Achtung der Spieler ist nicht in der Datenbank!"); return;}
		char[] chars  = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		int length = 12;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String msg = null;
		for(int i = 4; i<args.length; i++) {
			msg = msg + " " + args[i];
		}
		msg = msg.replace("null", "");
		String output = sb.toString();
		if(target != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			target.disconnect("§3Achtung §c" + target.getName() +"§3,\n du wurdest soebend §cPermanet§3 vom Mineup.net\nNetzwerk gebannt!"
			+ "\n\n§3Grund: §c" + msg + "\n§3Admin: §c" + p.getName() + "\n§3Gebannt: §c" + df.format(new Date().getTime()) + "\n§3BannID: §c" + output
			+ "\n\nDu denkst der Bann ist nicht rechtens?\nDann melde dich in useren Forum: §ewww.MineUp.net!");
			Perma_Ban b = new Perma_Ban(UUID, uuidfetcher.getUUID(p.getName()).toString(), msg, p.getName(), args[1], output, new Date().getTime(), Boolean.valueOf(args[2]), Boolean.valueOf(args[3]));
			b.start();
			p.sendMessage(Utils.prefix + "Du hast den Spieler mit dem Namen §e" + args[1] + "§c vom Server gebannt!");
		}else {
			Perma_Ban b = new Perma_Ban(UUID, uuidfetcher.getUUID(p.getName()).toString(), msg, p.getName(), args[1], output, new Date().getTime(), Boolean.valueOf(args[2]), Boolean.valueOf(args[3]));
			b.start();
			p.sendMessage(Utils.prefix + "Du hast den Spieler mit dem Namen §e" + args[1] + "§3 vom Server gebannt!");
		}
	}

}
