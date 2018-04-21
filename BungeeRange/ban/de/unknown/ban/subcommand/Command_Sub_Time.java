package de.unknown.ban.subcommand;


import java.text.SimpleDateFormat;
import java.util.Date;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_Sub_Time {

	@SuppressWarnings("deprecation")
	public Command_Sub_Time(ProxiedPlayer p, String[] args, BungeeRange main) {
		ProxiedPlayer target=  main.getProxy().getPlayer(args[1]);
		String UUID = uuidfetcher.getUUID(args[1]).toString();
		String msg = null;
		for(int i = 4; i<args.length; i++) {
			msg = msg + " " + args[i];
		}
		
		msg = msg.replace("null", "");
		if(Player_Account.playExists(UUID)) {
			if(getBanTime(args[2], Integer.valueOf(args[3])) == 0){
				p.sendMessage(Utils.prefix + "§cAchtung dieser Wert ist nicht ansprechbar!");
				return;
			}
			if(target != null) {
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				target.disconnect("§3Achtung §c" + target.getName() + "§3,\nDu wurdest soebend vom Netzwerk gebannt!\n\n§3Grund: §c" +msg + "\n§3Admin: §c" + p.getName()
				+ "\n§3Bis: §c" + df.format(getBanTime(args[2], Integer.valueOf(args[3]))) + "\n\n§3Du denkst das dieser Bann nicht rechtens ist?"
				+ "\n§3Dan melde dich in unserem Forum: §ewww.mineup.de!");
			}
		}else {p.sendMessage(Utils.prefix + "§cDer Spieler ist nicht in der Datenbank!");}
		
	}

	@SuppressWarnings("deprecation")
	public Long getBanTime(String banInput, Integer banTime) {
		Long l = null;
		Date d = new Date();
		switch(banInput) {
			case "s":
				d.setSeconds(d.getSeconds() + banTime);
				l = d.getTime();
				break;
			case "m":
				d.setMinutes(d.getMinutes() + banTime);
				l = d.getTime();
				break;
			case "st":
				d.setHours(d.getHours() + banTime);
				l = d.getTime();
				break;
			case "dd":
				d.setHours(d.getHours() + banTime*24);
				l = d.getTime();
				break;
			case "mm":
				d.setMonth(d.getMonth() + banTime);
				l = d.getTime();
				break;
			case "yy":
				d.setYear(d.getYear() + banTime);
				l = d.getTime();
				break;
			default:
				l = (long) 0;
				break;
		}
		return l;
	}
	
}
