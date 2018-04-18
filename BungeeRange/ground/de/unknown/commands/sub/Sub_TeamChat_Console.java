package de.unknown.commands.sub;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.command.ConsoleCommandSender;

public class Sub_TeamChat_Console {

	@SuppressWarnings("deprecation")
	public Sub_TeamChat_Console(ConsoleCommandSender c, String[] args, BungeeRange main) {
		String msg = null;
		for(String s : args) {
			msg = msg + " " + s;
		}
		
		msg = msg.replace("null", "");
		
		main.getProxy().getConsole().sendMessage("§cTeam-> §e"+c.getName()+": §3" + msg);
		
		for(ProxiedPlayer pp : main.getProxy().getPlayers()) {
			if(pp.hasPermission("Bungee.tc.read")) {
				pp.sendMessage("§cTeam-> §eServer-Admin: §3" + msg);
			}
		}
	}

}
