package de.unknown.command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.mysql.UnBanUpDatePermaID;
import de.unknown.mysql.UnBanUpDatePermaPlayer;
import de.unknown.mysql.UnBanUpdateID;
import de.unknown.mysql.UnBanUpdate;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class ProxyUnBan extends Command {


	@SuppressWarnings("unused")
	private BungeeRange main;

	public ProxyUnBan(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.unban.info")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/unban [Time:Perma] [Player:ID] [Player]");
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.unban.time")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/unban [Time] [Player:ID] [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("Perma")){
					if(!p.hasPermission("Bungee.unban.perma")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/unban [Perma] [Player:ID] [Player]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.unban.time")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/unban [Time] [Player:ID] [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("Perma")){
					if(!p.hasPermission("Bungee.unban.perma")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/unban [Perma] [Player:ID] [Player]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("time")) {
					if(args[1].equalsIgnoreCase("Player")) {
						if(!p.hasPermission("Bungee.unban.time")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
						String UUID = uuidfetcher.getUUID(args[2]).toString();
						if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§cAchtung der Spieler ist nicht in der Datenbank"); return;}
						UnBanUpdate b = new UnBanUpdate(UUID, p);
						b.start();
					}else if(args[1].equalsIgnoreCase("id")) {
						if(!p.hasPermission("Bungee.unban.time")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
						String ID = args[2];
						UnBanUpdateID b = new UnBanUpdateID(ID, p);
						b.start();
					}
				}else if(args[0].equalsIgnoreCase("Perma")){
					if(args[1].equalsIgnoreCase("Player")) {
						if(!p.hasPermission("Bungee.unban.perma")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
						String UUID = uuidfetcher.getUUID(args[2]).toString();
						if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§cAchtung der Spieler ist nicht in der Datenbank"); return;}
						UnBanUpDatePermaPlayer b = new UnBanUpDatePermaPlayer(UUID, p);
						b.start();
						p.sendMessage(Utils.prefix + "§c Der Spieler §e" + args[2] + "§c wurde entbannt!");
					}else if(args[1].equalsIgnoreCase("id")) {
						if(!p.hasPermission("Bungee.unban.perma")) {p.sendMessage("§cDas darfst dú nicht!"); return;}
						String id = args[2];
						UnBanUpDatePermaID b = new UnBanUpDatePermaID(id, p);
						b.start();
						p.sendMessage(Utils.prefix + "§c Der Spieler mit der ID §e" + id + "§c wurde entbannt!");
					}
				}
			}
		}else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender s = (ConsoleCommandSender) sender;
			s.sendMessage("§cAus sicherheits gründen hat die Console keinen Zugriff auf das Ban system!");
		}
	}

}
