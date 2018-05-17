package de.unknown.sup_command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.support.Support_MySQL;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Command_Sub_Report {

	@SuppressWarnings("deprecation")
	public Command_Sub_Report(ProxiedPlayer p, String[] args, BungeeRange main) {
		String UUID = uuidfetcher.getUUID(p.getName()).toString();
		String UUID_TARGET = uuidfetcher.getUUID(args[1]).toString();
		String Reason = null;
		for(int i = 2; i<args.length; i++) {
			Reason = Reason + " " + args[i];
		}
		Reason = Reason.replace("null", "");
		
		p.sendMessage(Utils.prefix + "§cDu hast den Spieler §e" + args[1] + "§cReportet!");
		p.sendMessage("§cDer Report lautet §d" + Reason);
		
		ProxiedPlayer t = ProxyServer.getInstance().getPlayer(args[1]);
		TextComponent msg = new TextComponent("§aNach springen");
		if(t != null) {
			msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/follow " + args[1]));
			msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§2Möchtest du den Spieler Nachspringen?").create() ) );
		}
		for(ProxiedPlayer pp : main.getProxy().getPlayers()) {
			if(pp.hasPermission("Bungee.support.sendmessage")) {
				pp.sendMessage(Utils.prefix + "§cDer Spieler §e" + p.getName() + "§c hat soebend den Spieler §e" + args[1] + "§creportet!");
				p.sendMessage("\n\n");
				pp.sendMessage(msg);
			}
		}
		Support_MySQL.createReport(UUID, Reason, UUID_TARGET,p.getName(),args[1]);
	}

}
