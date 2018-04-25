package de.unknown.perms.listener;

import de.unknown.perms.mysql.MySQL_SetPermission;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPermsJoin implements Listener {
	@EventHandler
	public void ProxyJoin(PostLoginEvent e) {
		MySQL_SetPermission b = new MySQL_SetPermission(e.getPlayer());
		b.start();
	}
}
