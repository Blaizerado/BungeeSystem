package de.unknown.listener;

import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.UUID_Map;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyLogin implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ProxyLoginEvent(PostLoginEvent e) {
		String UUID = uuidfetcher.getUUID(e.getPlayer().getName()).toString();
		if(!Player_Account.playExists(UUID)) {
			Player_Account.createPlayer(UUID,e.getPlayer().getName(),e.getPlayer().getAddress().getAddress().toString());
			if(!e.getPlayer().getName().equalsIgnoreCase(Player_Account.getName(UUID))) {
				e.getPlayer().sendMessage(Utils.prefix + "§cAchtung du hast in letzter Zeit deinen Namen geändert!\n alter Name: §e" + Player_Account.getName(UUID));
			}
			if(!UUID_Map.players.containsKey(e.getPlayer())) {UUID_Map.players.put(e.getPlayer(), UUID);}
		}
	}
	
}
