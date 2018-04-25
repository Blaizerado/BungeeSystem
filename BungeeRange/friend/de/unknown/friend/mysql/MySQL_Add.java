package de.unknown.friend.mysql;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;

import java.sql.ResultSet;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Add extends Thread{
	ProxiedPlayer t;
	String pUUID;
	String tUUID;
	String name;
	private BungeeRange main;
	public MySQL_Add(ProxiedPlayer p, String UUID, String PlayerName, BungeeRange bungeeRange) {
		pUUID = UUID;
		t = p;
		name = PlayerName;
		tUUID = uuidfetcher.getUUID(p.getName()).toString();
		this.main = bungeeRange;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Friend Thread wird gestartet!");
		try {
			if(checkRequest(pUUID)) {t.sendMessage(Utils.prefix + "§cDer Spieler hat bereits eine Anfrage von dir erhalten!");  Thread.sleep(100); return;}
			BungeeRange.my.update("INSERT INTO Friend_Request(player_target,player_requestet) VALUES ('"+pUUID+"','"+tUUID+"')");
			t.sendMessage(Utils.prefix + "Du hast dem Spieler §e" + name + "§3 eine Anfrage gesendet!");
			ProxiedPlayer p = this.main.getProxy().getPlayer(name);
			if(p != null) {
				p.sendMessage(Utils.prefix + "§3Spieler §e" + t.getName() + "§3 hat dir eine Anfrage gesendet!");
				TextComponent msg = new TextComponent("§aAnnehmen");
				msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/friend accept " + t.getName()));
				msg.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§2Möchtest du diese Anfrage annehmen?").create() ) );
				TextComponent msg1 = new TextComponent("§cAblehnen!");
				msg1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/friend deny " + t.getName()));
				msg1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§4Möchtest du diese Anfrage ablehen?").create()));
				p.sendMessage("");
				p.sendMessage(msg);
				p.sendMessage("");
				p.sendMessage(msg1);
			}
			Thread.sleep(100);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public Boolean checkRequest(String UUID) {
		ResultSet rs = BungeeRange.my.query("SELECT * FROM Friend_Request WHERE player_target='"+UUID+"'");
		try {
			while (rs.next()) {
				if(rs.getString("player_requestet").equalsIgnoreCase(tUUID)) {
					return true;
				}
			}
		}catch(Exception e) {}
		return false;
	}
	
}
