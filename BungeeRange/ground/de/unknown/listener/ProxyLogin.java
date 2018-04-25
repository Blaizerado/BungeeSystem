package de.unknown.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.UUID_Map;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyLogin implements Listener {
	
	private BungeeRange main;

	public ProxyLogin(BungeeRange bungeeRange) {
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ProxyLoginEvent(PostLoginEvent e) {
		int i = this.main.getProxy().getPlayers().size();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		e.getPlayer().setTabHeader(new TextComponent("§3§lWillkommen auf MineUp.net §3⚑§e"+e.getPlayer().getName()+"§3⚑§3\n§3§lDein Server für §c§lMiniGames!"), 
				new TextComponent("§c§lDatum§8: §3" + df.format(new Date().getTime()) + "\n§c§lSpieler Online§8:§3 " + i + "§8/§3" + "30"));
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
