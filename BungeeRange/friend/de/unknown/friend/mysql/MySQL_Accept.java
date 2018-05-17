package de.unknown.friend.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Accept extends Thread{
	ProxiedPlayer t;
	String pUUID;
	String pname;
	private BungeeRange main;
	public MySQL_Accept(ProxiedPlayer p, String UUID,String name, BungeeRange bungeeRange) {
		t = p;
		pUUID = UUID;
		pname = name;
		this.main = bungeeRange;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Friend Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Friend_Request WHERE player_requestet='"+pUUID+"' ORDER BY player_target='"+uuidfetcher.getUUID(t.getName()).toString()+"'");
			if(rs.next()) {
				BungeeRange.my.update("INSERT INTO Friend_Friend(UUID1,UUID2) VALUES('"+pUUID+"','"+uuidfetcher.getUUID(t.getName()).toString()+"');");
				BungeeRange.my.update("DELETE FROM Friend_Request WHERE player_requestet='"+pUUID+"' ORDER BY player_target='"+uuidfetcher.getUUID(t.getName()).toString()+"'");
				t.sendMessage(Utils.prefix + "Du hast den Spieler §e" + pname + "§3 angenommen!");
				ProxiedPlayer target = this.main.getProxy().getPlayer(pname);
				if(target != null) {
					target.sendMessage(Utils.prefix + "Der Spieler §e" + t.getName() + "§3 hat deine Anfrage angenommen, ihr seid nun befreundet!");
				}
			}else {t.sendMessage(Utils.prefix + "§cAchtung, der Spieler hat dir keine Anfrage gesendet!"); Thread.sleep(100); return;}
			Thread.sleep(100);
		} catch (Exception e) {}
	}
}
