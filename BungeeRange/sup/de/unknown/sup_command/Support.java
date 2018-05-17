package de.unknown.sup_command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.livechat.LiveChat;
import de.unknown.support.Support_MySQL_SetStats;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Support extends Command {


	private BungeeRange main;

	public Support(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.support.join")) {
					new Command_Sub_LiveChat(p,this.main,args);
				}else {
					p.sendMessage(Utils.prefix + "§cTeammitglieder können keine Frage stellen!");
					return;
				}
			}else if(args.length == 1) {
				 if(args[0].equalsIgnoreCase("help")) {
					if(p.hasPermission("Bungee.support.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
						p.sendMessage("§3/support join");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 2) {
				 if(args[0].equalsIgnoreCase("join")) {
					if(!p.hasPermission("Bungee.support.join")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					ProxiedPlayer target = this.main.getProxy().getPlayer(args[1]);
					if(target == null) {p.sendMessage(Utils.prefix + "§cAchtung der Spieler hat den Server verlassen oder keinen Report gesendet!"); return;}
					if(!LiveChat.chat_player.containsKey(target)) {p.sendMessage(Utils.prefix + "§cDer Spieler hat keine Anfrage gesendet!"); return;}
					if(LiveChat.chat_player.get(target) != null) {p.sendMessage(Utils.prefix + "§cEs wird sich bereits um den Spieler gekümmert!"); return;}
					Support_MySQL_SetStats b = new Support_MySQL_SetStats(p, uuidfetcher.getUUID(p.getName()).toString());
					b.start();
					LiveChat.chat_player.put(target, p);
					LiveChat.chats.put(p, target);
					target.sendMessage(Utils.prefix + "Der Spieler §e" + p.getName() + "§3 ist dem Chat gejoint!\n §cschreib einfach im Chat, schreibe Leav um den Chat zu verlassen!");
					p.sendMessage(Utils.prefix + "Du bist dem Chat mit dem Spieler §e" + target.getName() + "§3 gejoint!\n §cschreib einfach im Chat, schreibe Leav um den Chat zu verlassen!");
					p.sendMessage(Utils.prefix + "§cNutze option um deine Optionen zu sehen!");
				}
			}
		}
	}

}
