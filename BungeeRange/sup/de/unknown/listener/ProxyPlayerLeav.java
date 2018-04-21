package de.unknown.listener;

import de.unknown.livechat.LiveChat;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyPlayerLeav implements Listener {
	@EventHandler
	public void ProxyLeavEvent(PlayerDisconnectEvent e) {
		if(LiveChat.chat_player.containsKey(e.getPlayer())) {
			LiveChat.chat_player.remove(e.getPlayer());
		}else if(LiveChat.chats.containsKey(e.getPlayer())) {
			LiveChat.chats.remove(e.getPlayer());
		}
	}
}
