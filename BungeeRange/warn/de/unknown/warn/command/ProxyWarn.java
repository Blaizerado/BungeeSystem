package de.unknown.warn.command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.login.utils.Utils_Login;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import de.unknown.warn.mysql.MySQL_CreateWarn;
import de.unknown.warn.mysql.MySQL_DeleteTicket;
import de.unknown.warn.mysql.MySQL_GetWarnsByPlayer;
import de.unknown.warn.mysql.MySQL_ReadTicket;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyWarn extends Command {

	@SuppressWarnings("unused")
	private BungeeRange main;

	public ProxyWarn(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung du musst dich einloggen"); return;}
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.warn")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/warn id [Player]");
				p.sendMessage("§3/warn read id");
				p.sendMessage("§3/warn delete id");
				p.sendMessage("§3/warn create [Player] [Grund]");
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("id")) {
					if(!p.hasPermission("Bungee.warn.id")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/warn id [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("read")) {
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/warn read [id]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.warn.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/warn create [Player] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("delete")) {
					if(!p.hasPermission("Bungee.warn.delete")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/warn delete [id]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("id")) {
					if(!p.hasPermission("Bungee.warn.id")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					MySQL_GetWarnsByPlayer b = new MySQL_GetWarnsByPlayer(p, UUID);
					b.start();
				}else if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.warn.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(!p.hasPermission("Bungee.warn.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					MySQL_ReadTicket b = new MySQL_ReadTicket(p, Integer.valueOf(args[1]));
					b.start();
				}else if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.warn.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/warn create [Player] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("delete")) {
					if(!p.hasPermission("Bungee.warn.delete")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					MySQL_DeleteTicket b = new MySQL_DeleteTicket(p, Integer.valueOf(args[1]));
					b.start();
				}
			}else if(args.length >= 3) {
				if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.warn.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					String reason = null;
					if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§cAchtung, der Spieler ist nicht in der Datenbank!"); return;}
					for(int i = 2; i<args.length; i++) {
						reason = reason + " " + args[i];
					}
					reason = reason.replace("null", "");
					MySQL_CreateWarn b = new MySQL_CreateWarn(p, UUID, reason);
					b.start();
				}
			}
		}
	}

}
