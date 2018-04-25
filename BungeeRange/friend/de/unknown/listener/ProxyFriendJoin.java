package de.unknown.listener;

import de.unknown.friend.mysql.MySQL_GetRequest;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyFriendJoin implements Listener {
	@EventHandler
	public void ProxyJoin(PostLoginEvent e) {
		MySQL_GetRequest b = new MySQL_GetRequest(e.getPlayer(), uuidfetcher.getUUID(e.getPlayer().getName()).toString());
		b.start();
	}
}
