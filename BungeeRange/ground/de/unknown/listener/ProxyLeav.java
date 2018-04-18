package de.unknown.listener;

import de.unknown.uuid.UUID_Map;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyLeav implements Listener {
	
	@EventHandler
	public void ProxyLeavEvent(PlayerDisconnectEvent e) {
		if(UUID_Map.players.containsKey(e.getPlayer())) {
			UUID_Map.players.remove(e.getPlayer());
		}
	}
	
}
