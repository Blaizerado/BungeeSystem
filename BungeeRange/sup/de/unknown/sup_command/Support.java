package de.unknown.sup_command;

import java.util.ArrayList;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.livechat.LiveChat;
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
				p.sendMessage("§3/support LiveChat");
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
					}else {p.sendMessage(Utils.prefix + "§cEs gibt zur Zeit keine Offenen Tickets!"); return;}
				}else if(args[0].equalsIgnoreCase("livechat")) {
					new Command_Sub_LiveChat(p,this.main,args);
				}else if(args[0].equalsIgnoreCase("join")) {
					if(!p.hasPermission("Bungee.support.join")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/support join [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.support.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/support read [ID]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("close")) {
					if(!p.hasPermission("Bungee.support.close")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/support close [ID]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("report")) {
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/support report [Player] [Grund]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("join")) {
					if(!p.hasPermission("Bungee.support.join")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					ProxiedPlayer target = this.main.getProxy().getPlayer(args[1]);
					if(target == null) {p.sendMessage(Utils.prefix + "§cAchtung der Spieler hat den Server verlassen oder keinen Report gesendet!"); return;}
					if(!LiveChat.chat_player.containsKey(target)) {p.sendMessage(Utils.prefix + "§cDer Spieler hat keine Anfrage gesendet!"); return;}
					if(LiveChat.chat_player.get(target) != null) {p.sendMessage(Utils.prefix + "§cEs wird sich bereits um den Spieler gekümmert!"); return;}
					LiveChat.chat_player.put(target, p);
					LiveChat.chats.put(p, target);
					target.sendMessage(Utils.prefix + "Der Spieler §e" + p.getName() + "§3 ist dem Chat gejoint!\n §cschreib einfach im Chat, schreibe Leav um den Chat zu verlassen!");
					p.sendMessage(Utils.prefix + "Du bist dem Chat mit dem Spieler §e" + target.getName() + "§3 gejoint!\n §cschreib einfach im Chat, schreibe Leav um den Chat zu verlassen!");
					p.sendMessage(Utils.prefix + "§cNutze option um deine Optionen zu sehen!");
				}else if(args[0].equalsIgnoreCase("read")) {
					if(!p.hasPermission("Bungee.support.read")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					new Command_Sub_Read(p, main, args);
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
