package de.unknown.listener;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.livechat.LiveChat;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyChat implements Listener {
	private BungeeRange main;

	public ProxyChat(BungeeRange bungeeRange) {
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void ProxyChatEvent(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		if(LiveChat.chat_player.containsKey(p) && LiveChat.chat_player.get(p) != null) {
			ProxiedPlayer target = LiveChat.chat_player.get(p);
			ProxiedPlayer online = this.main.getProxy().getPlayer(target.getName());
			if(online == null) {
				p.sendMessage(Utils.prefix + "Dein Chat Partner ist offline!");
				LiveChat.chat_player.remove(p);
				LiveChat.chats.remove(target);
				p.sendMessage(Utils.prefix + "§cDu wurdest wieder in den  Normalen Chat gebracht!");
				e.setCancelled(true);
				return;
			}
			if(e.getMessage().contains("leav")) {
				target.sendMessage(Utils.prefix + "Dein Chat parnter hat den Chat verlassen!");
				p.sendMessage(Utils.prefix + "§cDu hast den Chat verlassen!");
				LiveChat.chat_player.remove(p);
				LiveChat.chats.remove(target);
				p.sendMessage(Utils.prefix + "§cDu wurdest wieder in den  Normalen Chat gebracht!");
				e.setCancelled(true);
				return;
			}
			e.setCancelled(true);
			target.sendMessage("§eSupport->§ c" + p.getName()+"§8:§d " + e.getMessage());
			p.sendMessage("§eDu->" + target.getName() + "§8:§d " + e.getMessage());
		}else if(LiveChat.chats.containsKey(p)) {
			e.setCancelled(true);
			ProxiedPlayer target = LiveChat.chats.get(p);
			ProxiedPlayer online = this.main.getProxy().getPlayer(target.getName());
			if(online == null) {
				p.sendMessage(Utils.prefix + "Dein Chat Partner ist offline!");
				LiveChat.chats.remove(p);
				LiveChat.chat_player.remove(target);
				p.sendMessage(Utils.prefix + "§cDu wurdest wieder in den  Normalen Chat gebracht!");
				e.setCancelled(true);
				return;
			}
			if(e.getMessage().contains("leav")) {
				target.sendMessage(Utils.prefix + "Dein Chat parnter hat den Chat verlassen!");
				p.sendMessage(Utils.prefix + "§cDu hast den Chat verlassen!");
				LiveChat.chats.remove(p);
				LiveChat.chat_player.remove(target);
				p.sendMessage(Utils.prefix + "§cDu wurdest wieder in den  Normalen Chat gebracht!");
				e.setCancelled(true);
				return;
			}
			if(e.getMessage().contains("option")) {
				p.sendMessage("§3Jump");
				p.sendMessage("§3Telport");
				e.setCancelled(true);
				return;
			}
			if(e.getMessage().contains("jump")) {
				p.connect(target.getServer().getInfo());
				p.sendMessage(Utils.prefix + "§cDu bist dem Spieler gefolgt!");
				e.setCancelled(true);
				return;
			}
			if(e.getMessage().contains("teleport")) {
				target.connect(p.getServer().getInfo());
				target.sendMessage(Utils.prefix + "§cDu wurdest Teleportiert!");
				p.sendMessage(Utils.prefix + "§cDu hast §e" + target.getName() + "§c zu dir Jumpen lassen!");
				e.setCancelled(true);
				return;
			}
			target.sendMessage("§eSupport->§ c" + p.getName()+"§8:§d " + e.getMessage());
			p.sendMessage("§eDu->" + target.getName() + "§8:§d " + e.getMessage());
		}
	}
}
