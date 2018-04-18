package de.unknown.sup_command;

import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.support.Support_MySQL;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Support extends Command {


	private BungeeRange main;

	public Support(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/support report [Player] [Grund]");
				p.sendMessage("§3/support Live-Chat");
				if(p.hasPermission("Bungee.support.info")) {
					p.sendMessage("§3/support list");
					p.sendMessage("§3/support read [ID]");
					p.sendMessage("§3/support close [ID]");
					p.sendMessage("§3/support join [Player]");
				}
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("report")) {
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/support report [Player] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("list")) {
					if(!p.hasPermission("bungee.support.exectue")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					ArrayList<Integer>ticket = Support_MySQL.getOpenTickets();
					if(ticket.size() != 0) {
						String tickets = null;
						for(Integer i : ticket) {
							tickets = tickets + " " + i;
						}
						tickets = tickets.replace("null", "");
						p.sendMessage(Utils.prefix + "Offene Tickets!");
						p.sendMessage("§b" + tickets);
					}else {p.sendMessage(Utils.prefix + "§cEs gibt zur Zeit keine Offenen Tickets!");}
				}else if(args[0].equalsIgnoreCase("livechat")) {
					new Command_Sub_LiveChat(p,this.main,args);
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("report")) {
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/support report [Player] [Grund]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length >= 3) {
				if(args[0].equalsIgnoreCase("report")) {
					new Command_Sub_Report(p,args,this.main);
				}
			}
		}
	}

}
