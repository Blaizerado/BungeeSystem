package de.unknown.sup_command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.livechat.LiveChat;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_Sub_LiveChat {

	@SuppressWarnings("deprecation")
	public Command_Sub_LiveChat(ProxiedPlayer p, BungeeRange main, String[] args) {
		int i = 0;
		for(ProxiedPlayer cp : main.getProxy().getPlayers()) {
			if(cp.hasPermission("Bungee.support.notify")) {
				i++;
				cp.sendMessage(Utils.prefix + "§cDer Spieler §e" + p.getName() + "§c wartet im LiveChat!");
			}
		}
		
		if(i == 0) {p.sendMessage(Utils.prefix + "Achtung zur Zeit ist leider kein Supporter/Admin Online!"); return;}
		p.sendMessage(Utils.prefix + "§cUnsere Supporter haben einen Nachricht erhalten!");
		LiveChat.chat_player.put(p, null);
	}

}
