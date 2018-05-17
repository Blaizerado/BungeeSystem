package de.unknown.command;

import de.unknown.ban.mysql.BanClearBan;
import de.unknown.ban.mysql.GetBanInfoTime;
import de.unknown.ban.mysql.GetMySQLBanPerma;
import de.unknown.ban.mysql.GetMySQLBanTime;
import de.unknown.ban.subcommand.Command_BanID;
import de.unknown.ban.subcommand.Command_BanIDPerma;
import de.unknown.ban.subcommand.Command_Sub_Perma;
import de.unknown.ban.subcommand.Command_Sub_Time;
import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
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
				if(!p.hasPermission("Bungee.ban.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				p.sendMessage("§d====== §eMine§7-§bBann §d======");
				p.sendMessage("   ");
				p.sendMessage("§5Ban-IDs: §7Grund-IDs zum bannen");
				p.sendMessage("  ");
				p.sendMessage("  §6Hack §7- §e1§4§l:§b1,2 oder 3");
				p.sendMessage("  §6Beleidigung §7- §e2§4§l:§b1,2 oder 3");
				p.sendMessage("  §6Username §7- §e3§4§l:§b1,2 oder 3");
				p.sendMessage("  §6Bugusing §7- §e4");
				p.sendMessage("  §6Radikalismus §7- §e5§4§l:§b1,2 oder 3");
				p.sendMessage("  §6Bannumgehung §7- §e6 §7- §4Permanent");
				p.sendMessage("  §6Werbung §7- §e7 §7- §4Permanent");
				p.sendMessage("   ");
				p.sendMessage("§cNutze: §e/ban <Player> <Grund-ID:1,2 oder 3>");
				p.sendMessage("  ");
				p.sendMessage("§cNutze: §e/ban Perma <Player> <Grund>");
				p.sendMessage("  ");
				p.sendMessage("§cNutze: §e/ban Time <Player> <s:m:st-dd:mm:yy> <Zeit> <Grund>");
				if(p.hasPermission("Bungee.ban.Admin")) {
					p.sendMessage("  ");
					p.sendMessage("§3/ban getban <Time:perma> <Player>");
					p.sendMessage("§3/ban read <Time:Perma> <ID>");
					p.sendMessage("§3/ban clear");
				}
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
					BanClearBan b = new BanClearBan();
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
				}else {
					ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
					if(target != null) {
						if(args[1].equalsIgnoreCase("1")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Hacking");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("1:1")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Leichtes Hacking");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("1:2")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Mittlers Hacking");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("1:3")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Schweres Hacking");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("2")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Beleidigung");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("2:1")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Leichte Beleidigung");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("2:2")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], " Mittlere Beleidigung");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("2:3")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], " Schwere Beleidigung");
							b.BanByID();
						}if(args[1].equalsIgnoreCase("3")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Falscher Username");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("3:1")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Makaberer Username");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("3:2")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Makaberer Username");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("3:3")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Makaberer Username");
							b.BanByID();
						}if(args[1].equalsIgnoreCase("4")) {
							Command_BanIDPerma b = new Command_BanIDPerma(p, args[0], "Bug Using");
							b.banID();
						}if(args[1].equalsIgnoreCase("5")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Radikalismus");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("5:1")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Leichter Radikalismus");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("5:2")) {
							Command_BanID b = new Command_BanID(p, args[0], args[1], "Mittlerer Radikalismus");
							b.BanByID();
						}else if(args[1].equalsIgnoreCase("5:3")) {
							Command_BanIDPerma b = new Command_BanIDPerma(p, args[0], "Schwerer Radikalismus");
							b.banID();
						}if(args[1].equalsIgnoreCase("6")) {
							Command_BanIDPerma b = new Command_BanIDPerma(p, args[0], "Ban Umgehung");
							b.banID();
						}else if(args[1].equalsIgnoreCase("7")) {
							Command_BanIDPerma b = new Command_BanIDPerma(p, args[0], "Werbung");
							b.banID();
						}
					}else {p.sendMessage(Utils.prefix + "§cAchtung, der Spieler §e" + args[0] + "§c ist nicht online!"); return;}
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
