package de.unknown.perms.command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.login.utils.Utils_Login;
import de.unknown.mysql.Player_Account;
import de.unknown.perms.mysql.MySQL_GetGroup;
import de.unknown.perms.mysql.MySQL_GroupAddPermission;
import de.unknown.perms.mysql.MySQL_GroupCreate;
import de.unknown.perms.mysql.MySQL_RemoveGroup;
import de.unknown.perms.mysql.MySQL_RemovePermission;
import de.unknown.perms.mysql.MySQL_SetDefault;
import de.unknown.perms.mysql.MySQL_SetGroup;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyUnknownPermission extends Command {

	public ProxyUnknownPermission(String name, BungeeRange bungeeRange) {
		super(name);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung, du bist nicht eingeloggt!"); return;}
			if(!Utils_Login.Login.get(p)) {p.sendMessage(Utils.prefix + "§cAchtung, um diesen Command nutzten zu könen, musst du dich einloggen!"); return;}
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.uperms.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				p.sendMessage("§3Unknown_Perms§8:§c /uperms create [Name] [Erben ZB 1,0 ID]");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms groups");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] setdefault");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] add permission [Permission]");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] remove permission [Permission]");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] setprefix [Prefix]");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] setdisplay [Display]");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] setgroup [Group]");
				p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] removegroup [Ggroup]");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.uperms.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3Unknown_Perms§8:§c /uperms create [Name] [Erben ZB 1,0 ID]");
				}else if(args[0].equalsIgnoreCase("groups")) {
					if(!p.hasPermission("Bungee.uperms.groups")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					MySQL_GetGroup b = new MySQL_GetGroup(p);
					b.start();
				}else if(args[0].equalsIgnoreCase("group")) {
					if(!p.hasPermission("Bungee.uperms.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] setdefault");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] addpermission [Permission]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] removepermission [Permission]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] setprefix [Prefix]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms [Group] setdisplay [Display]");
				}else if(args[0].equalsIgnoreCase("user")) {
					if(!p.hasPermission("Bungee.uperms.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] setgroup [Group]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] removegroup [Ggroup]");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.uperms.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3Unknown_Perms§8:§c /uperms create [Name] [Erben ZB 1,0 ID]");
					System.out.println("test1");
				}else if(args[0].equalsIgnoreCase("group")) {
					if(!p.hasPermission("Bungee.uperms.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] setdefault");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] addpermission [Permission]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] removepermission [Permission]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] setprefix [Prefix]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] setdisplay [Display]");
				}else if(args[0].equalsIgnoreCase("user")) {
					if(!p.hasPermission("Bungee.uperms.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] setgroup [Group]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] removegroup [Ggroup]");
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.uperms.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					MySQL_GroupCreate b = new MySQL_GroupCreate(p, args[1], args[2]);
					b.start();
					System.out.println("test");
				}else if(args[0].equalsIgnoreCase("group")) {
					if(args[2].equalsIgnoreCase("setdefault")) {
						if(!p.hasPermission("Bungee.uperms.setdefault")) {p.sendMessage("§cDas darfst du nicht!"); return;}
						MySQL_SetDefault b = new MySQL_SetDefault(p, args[1]);
						b.start();
					}else{
						if(!p.hasPermission("Bungee.uperms.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
						p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] addpermission [Permission]");
						p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] removepermission [Permission]");
						p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] setprefix [Prefix]");
						p.sendMessage("§3Unknown_Perms§8:§c /uperms group [Group] setdisplay [Display]");
					}
				}else if(args[0].equalsIgnoreCase("user")) {
					if(!p.hasPermission("Bungee.uperms.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] setgroup [Group]");
					p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] removegroup [Ggroup]");
				}
			}else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("group")) {
					if(args[2].equalsIgnoreCase("addpermission")) {
						if(!p.hasPermission("Bungee.uperms.addpermssion")) {p.sendMessage("§cDas darfst du nicht!"); return;}
						String group = args[1];
						String perms = args[3];
						MySQL_GroupAddPermission b = new MySQL_GroupAddPermission(perms, group, p);
						b.start();
					}else if(args[2].equalsIgnoreCase("removepermission")) {
						if(!p.hasPermission("Bungee.uperms.removepermssion")) {p.sendMessage("§cDas darfst du nicht!"); return;}
						String group = args[1];
						String perms = args[3];
						MySQL_RemovePermission b = new MySQL_RemovePermission(p, group, perms);
						b.start();
					}
				}else if(args[0].equalsIgnoreCase("user")) {
					if(args[2].equalsIgnoreCase("setgroup")) {
						if(!p.hasPermission("Bungee.uperms.setgroup")) {p.sendMessage("§cDas darfst du nicht!"); return;}
						MySQL_SetGroup b = new MySQL_SetGroup(p, uuidfetcher.getUUID(args[1]).toString(), args[3], args[1]);
						b.start();
					}else if(args[2].equalsIgnoreCase("removegroup")){
						if(!p.hasPermission("Bungee.uperms.removegroup")) {p.sendMessage("§cDas darfst du nicht!"); return;}
						String UUID = uuidfetcher.getUUID(args[1]).toString();
						if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§cDer Spieler ist nicht in der Datenbank");}
						MySQL_RemoveGroup b = new MySQL_RemoveGroup(p, UUID);
						b.start();
					}else {
						p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] setgroup [Group]");
						p.sendMessage("§3Unknown_Perms§8:§c /uperms user [Player] removegroup [Ggroup]");
						p.sendMessage("Test");
					}
				}
			}
		}
	}

}
