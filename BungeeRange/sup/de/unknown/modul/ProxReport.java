package de.unknown.modul;

import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.sup_command.Command_Sub_Read;
import de.unknown.sup_command.Command_Sub_Report;
import de.unknown.support.Support_MySQL;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxReport extends Command {

	private BungeeRange main;

	public ProxReport(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage("§3/report <Player> <Grund>");
				if(p.hasPermission("Bungee.support.info")) {
					p.sendMessage("§3/report gettickets");
					p.sendMessage("§3/report read <ID>");
					p.sendMessage("§3/report close <ID>");
				}
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("gettickets")) {
					if(!p.hasPermission("Bungee.support.gettickets")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					ArrayList<Integer> id = Support_MySQL.getOpenTickets();
					p.sendMessage(Utils.prefix + "§cFolgende Tickes sind noch Offen!");
					p.sendMessage("§3"+id.toString().replace("[", "").replace("]",""));
				}else if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.support.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3/report read <ID>");
				}else if(args[0].equalsIgnoreCase("close")) {
					if(!p.hasPermission("Bungee.support.close")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3/report close <ID>");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.support.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					new Command_Sub_Read(p, this.main, args);
				}else if(args[0].equalsIgnoreCase("close")) {
					if(!p.hasPermission("Bungee.support.close")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(Integer.valueOf(args[1]) == null) {p.sendMessage(Utils.prefix + "Achtung gib bitte eine ID ein!"); return;}
					if(!Support_MySQL.ticketCreate(Integer.valueOf(args[1]))) {p.sendMessage(Utils.prefix + "§cAchtung diese Ticket gibt es nicht!"); return;}
					Support_MySQL.closeTicket(Integer.valueOf(args[1]));
					p.sendMessage(Utils.prefix + "§cDas Ticket §e" + args[1] + "§c wurde geschlossen!");
				}
			}else if(args.length >= 3) {
				if(args[0].equalsIgnoreCase("report")) {
					new Command_Sub_Report(p,args,this.main);
				}
			}
		}
	}
}
