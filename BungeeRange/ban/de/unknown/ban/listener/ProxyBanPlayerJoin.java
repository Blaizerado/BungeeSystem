package de.unknown.ban.listener;

import de.unknown.ban.kickjoin.KickJoinPerma;
import de.unknown.ban.kickjoin.KickJoinTime;
import de.unknown.ban.mysql.BanCheckPlayer;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyBanPlayerJoin implements Listener {
	@EventHandler
	public void ProxyJoinEvent(PostLoginEvent e) {
		String UUID = uuidfetcher.getUUID(e.getPlayer().getName()).toString();
		
		if(BanCheckPlayer.isBanned(UUID)) {
			KickJoinTime k = new KickJoinTime(e.getPlayer(), UUID);
			k.start();
		}else if(BanCheckPlayer.isPermaBan(UUID)) {
			KickJoinPerma k = new KickJoinPerma(e.getPlayer(), UUID);
			k.start();
		}
	}
}
