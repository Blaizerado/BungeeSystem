package de.unknown.ban.subcommand;




import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import de.unknown.ban.mysql.Time_Ban;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_BanID {
	ProxiedPlayer p;
	String UUID;
	String pUUID;
	String t;
	String i;
	String r;
	public Command_BanID(ProxiedPlayer a, String c, String d, String e) {
		p = a;
		t = c;
		i = d;
		r = e;
		UUID = uuidfetcher.getUUID(t).toString();
		pUUID = uuidfetcher.getUUID(a.getName()).toString();
	}
	public void BanByID() {
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
			Long l = getDate();
			Time_Ban b = new Time_Ban(p.getName(),UUID, output, l, r, p.getName(), t, pUUID);
			b.start();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			target.disconnect("§3Achtung §c" + target.getName() + "§3,\nDu wurdest soebend vom Netzwerk gebannt!\n\n§3Grund: §c" +r+ "\n§3Admin: §c" + p.getName()
			+ "\n§3Bis: §c" + df.format(l) + "\n§3BanID: §c" + output + "\n\n§3Du denkst das dieser Bann nicht rechtens ist?"
			+ "\n§3Dan melde dich in unserem Forum: §ewww.mineup.de!");
		}else {
			Long l = getDate();
			Time_Ban b = new Time_Ban(p.getName(),UUID, output, l, r, p.getName(), t, pUUID);
			b.start();
		}
		p.sendMessage(Utils.prefix + "§cDu hast den Spieler §e" + t + "§c vom Server gebannt!");
	}
	
	public Long getDate() {
		Date d = new Date();
		switch(i) {
			case "1":
				d.setHours(d.getHours() + 24*7);
			break;
			case "1:1":
				d.setHours(d.getHours() + 24*8);
				break;
			case "1:2":
				d.setHours(d.getHours() + 24*9);
				break;
			case "1:3":
				d.setHours(d.getHours() + 24*10);
				break;
			case "2":
				d.setHours(d.getHours() + 24*7);
				break;
			case "2:1":
				d.setHours(d.getHours() + 24*8);
				break;
			case "2:2":
				d.setHours(d.getHours() + 24*9);
				break;
			case "2:3":
				d.setHours(d.getHours() + 24*10);	
				break;
			case "3":
				d.setHours(d.getHours() + 24*30);
				break;
			case "3:1":
				d.setHours(d.getHours() + 24*40);
				break;
			case "3:2":
				d.setHours(d.getHours() + 24*50);
				break;
			case "3:3":
				d.setHours(d.getHours() + 24*60);
				break;
			case "5":
				d.setHours(d.getHours() + 24*7);
				break;
			case "5:1":
				d.setHours(d.getHours() + 24*10);
				break;
			case "5:2":
				d.setHours(d.getHours() + 24*20);
				break;
			default:
					
				break;
		}
		return d.getTime();
	}
}
