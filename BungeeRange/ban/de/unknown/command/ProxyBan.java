package de.unknown.command;

import de.unknown.ban.subcommand.Command_Sub_Perma;
import de.unknown.ban.subcommand.Command_Sub_Time;
import de.unknown.bungeecord.BungeeRange;
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
				p.sendMessage("§3/ban unban [Player|ID] [Grund]");
				p.sendMessage("§3/ban check [Player]");
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban time [Player] [s:m:st-dd:mm:yy] [Zeit] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/ban perma [Player] [Entbann-> true-false] [IPBan-> true-false] [Grund]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length >= 5) {
				if(args[0].equalsIgnoreCase("time")) {
					if(!p.hasPermission("Bungee.ban.time")) {p.sendMessage("§cDas darfst du nicht!");}
					new Command_Sub_Time(p,args,this.main);
				}else if(args[0].equalsIgnoreCase("perma")) {
					if(!p.hasPermission("Bungee.ban.perma")) {p.sendMessage("§cDas darfst du nicht!");}
					new Command_Sub_Perma(p,args,this.main);
				}
			}
		}else if(sender instanceof ConsoleCommandSender) {
			
			
		}
	}

}
