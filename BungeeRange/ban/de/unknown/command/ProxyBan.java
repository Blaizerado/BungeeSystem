package de.unknown.command;

import de.unknown.ban.mysql.ClearBan;
import de.unknown.ban.mysql.GetBanInfoTime;
import de.unknown.ban.mysql.GetMySQLBanPerma;
import de.unknown.ban.mysql.GetMySQLBanTime;
import de.unknown.ban.subcommand.Command_Sub_Perma;
import de.unknown.ban.subcommand.Command_Sub_Time;
import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class ProxyBan extends Command {

	private BungeeRange main;

	public ProxyBan(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.ban.info")) {p.sendMessage("§cDas darfst du nicht!");}
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
				p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
				p.sendMessage("§3/ban getban [Time:perma][Player]");
				p.sendMessage("§3/ban read [Time:Perma] [ID]");
				p.sendMessage("§3/ban clear");
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("getban")) {
					if(!p.hasPermission("Bungee.ban.getban")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban getban [Time:perma][Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.ban.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("/ban read [Time:Perma] [ID]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("clear")) {
					if(!p.hasPermission("Bungee.ban.clear")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					ClearBan b = new ClearBan();
					b.start();
					p.sendMessage(Utils.prefix + "§cAchtung, alle Bans die nicht mehr aktiv sind werden nun gelöscht!!!");
					p.sendMessage("§cAb jetzt kann man nur noch auf Bans zurück greifen, die noch Aktiv sind!");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("getban")) {
					if(!p.hasPermission("Bungee.ban.getban")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban getban [Time:perma][Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.ban.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("/ban read [Time:Perma] [ID]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("getban")) {
					if(!p.hasPermission("Bungee.ban.getban")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(args[1].equalsIgnoreCase("time")) {
						String UUID = uuidfetcher.getUUID(args[2]).toString();
						if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix +"§cAchtung der Spieler ist nicht in der Datenbank!"); return;}
						GetMySQLBanTime b = new GetMySQLBanTime(p, UUID);
						b.start();
					}else if(args[1].equalsIgnoreCase("perma")) {
						String UUID = uuidfetcher.getUUID(args[2]).toString();
						if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix +"§cAchtung der Spieler ist nicht in der Datenbank!"); return;}
						GetMySQLBanPerma b = new GetMySQLBanPerma(UUID, p);
						b.start();
					}
				}else if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.ban.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(args[1].equalsIgnoreCase("Time")) {
						GetBanInfoTime b = new GetBanInfoTime(p, Integer.valueOf(args[2]));
						b.start();
					}else if(args[1].equalsIgnoreCase("Perma")) {
						
					}
				}
			}else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length >= 5) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					new Command_Sub_Time(p,args,this.main);
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					new Command_Sub_Perma(p,args,this.main);
					System.out.println("ban");
				}
			}
		}else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender c = (ConsoleCommandSender) sender;
			c.sendMessage("§cAchtung aus Gründen der Sicherheit ist es der Console nicht erlaubt den ban command zu benutzten!");
		}
	}

}
