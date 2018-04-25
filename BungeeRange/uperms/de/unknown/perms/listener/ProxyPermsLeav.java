package de.unknown.perms.listener;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPermsLeav implements Listener {
	@EventHandler
	public void ProxyLeav(PlayerDisconnectEvent e) {
			System.out.println(e.getPlayer().getPermissions());
	}
}
