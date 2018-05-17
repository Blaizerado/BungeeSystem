package de.unknown.kick;

import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyKick extends Command {

	public ProxyKick(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.kick")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				p.sendMessage(Utils.prefix + " §4Kick-IDs: §7Nutze eines der IDs, um einen Spieler zu kicken!");
				p.sendMessage("§6Grund §7- §eID");
				p.sendMessage("§6Hack §7- §e1");
				p.sendMessage("§6Beleidigung §7- §e2");
				p.sendMessage("§6Username §7- §e3");
				p.sendMessage("§6Bugusing §7- §e4");
				p.sendMessage("§6Bannumgehung §7- §e5");
				p.sendMessage("§6Radikalismus §7- §e6");
				p.sendMessage("§6Skin §7- §e7");
				p.sendMessage("§6Werbung §7- §e8");
				p.sendMessage("§6Mobbing §7- §e9");
				p.sendMessage("§6Provokation §7- §e10");
				p.sendMessage(Utils.prefix + " §cNutze: §e/kick <Spieler> <ID/Grund>");
			}else if(args.length == 1) {
				if(!p.hasPermission("Bungee.kick")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				p.sendMessage(Utils.prefix + " §4Kick-IDs: §7Nutze eines der IDs, um einen Spieler zu kicken!");
				p.sendMessage("§6Grund §7- §eID");
				p.sendMessage("§6Hack §7- §e1");
				p.sendMessage("§6Beleidigung §7- §e2");
				p.sendMessage("§6Username §7- §e3");
				p.sendMessage("§6Bugusing §7- §e4");
				p.sendMessage("§6Bannumgehung §7- §e5");
				p.sendMessage("§6Radikalismus §7- §e6");
				p.sendMessage("§6Skin §7- §e7");
				p.sendMessage("§6Werbung §7- §e8");
				p.sendMessage("§6Mobbing §7- §e9");
				p.sendMessage("§6Provokation §7- §e10");
				p.sendMessage(Utils.prefix + " §cNutze: §e/kick <Spieler> <ID/Grund>");
			}else if(args.length >= 2) {
				if(!p.hasPermission("Bungee.kick.execute")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
				if(target != null) {
					if(args[1].equalsIgnoreCase("1")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: Hacking");
					}else if(args[1].equalsIgnoreCase("2")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cBeleidigung");
					}else if(args[1].equalsIgnoreCase("3")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cUsername");
					}else if(args[1].equalsIgnoreCase("4")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cBugusing");
					}else if(args[1].equalsIgnoreCase("5")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cBannumgehung");
					}else if(args[1].equalsIgnoreCase("6")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cRadikalismus");
					}else if(args[1].equalsIgnoreCase("7")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cSkin");
					}else if(args[1].equalsIgnoreCase("8")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cWerbung");
					}else if(args[1].equalsIgnoreCase("9")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cMobbing");
					}else if(args[1].equalsIgnoreCase("10")) {
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §cProvokation");
					}else {
						String s = null;
						for(int i = 1; i< args.length; i++) {
							s = s + " " + args[i];
						}
						s = s.replace("null", "");
						target.disconnect("§eAchtung, §c" + target.getName() + "§e\nDu wurdest soebend vom Server gekickt!\n\nGrund: §c"+s);
					}
				}else {p.sendMessage(Utils.prefix + "§cAchtung, der Spieler §e" + args[1] + "§c ist nicht online!");}
			}
		}
	}
}
