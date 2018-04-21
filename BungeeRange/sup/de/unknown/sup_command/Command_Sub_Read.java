package de.unknown.sup_command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.support.Support_MySQL_GetTicket;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_Sub_Read {

	@SuppressWarnings("deprecation")
	public Command_Sub_Read(ProxiedPlayer p, BungeeRange main, String[] args) {
		Integer ticketid = Integer.valueOf(args[1]);
		if(ticketid == null) {p.sendMessage(Utils.prefix + "Bitte gib eine ID an!"); return;}
		Support_MySQL_GetTicket t = new Support_MySQL_GetTicket(p, ticketid);
		t.start();
	}
	
}
