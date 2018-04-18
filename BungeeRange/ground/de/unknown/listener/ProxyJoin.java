package de.unknown.listener;

import de.unknown.utils.Utils;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyJoin implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void ProxyJoinEvent(ServerConnectEvent e) {
		if(Utils.Wartung) {
			if(!e.getPlayer().hasPermission("Bungee.Join")) {
				e.setCancelled(true);
				e.getPlayer().disconnect("§c§lAchtung:\n §eUnser Netzwerk ist zur Zeit in Wartung!");
			}
		}
	}
	
}
