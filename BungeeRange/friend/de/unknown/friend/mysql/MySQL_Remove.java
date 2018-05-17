package de.unknown.friend.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Remove extends Thread{
	ProxiedPlayer p;
	String PUUID;
	String pname;
	private BungeeRange main;
	public MySQL_Remove(ProxiedPlayer t, String UUID, String name, BungeeRange bungeeRange) {
		p = t;
		PUUID = UUID;
		pname = name;
		this.main = bungeeRange;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Friend Thread wird gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Friend_Friend WHERE UUID1='"+PUUID+"' UNION SELECT * FROM Friend_Friend WHERE UUID2='"+PUUID+"'");
			if(rs.next()) {
				BungeeRange.my.update("DELETE FROM Friend_Friend WHERE UUID1='"+PUUID+"' ORDER BY UUID2='"+uuidfetcher.getUUID(p.getName()).toString()+"'");
				BungeeRange.my.update("DELETE FROM Friend_Friend WHERE UUID2='"+PUUID+"' ORDER BY UUID1='"+uuidfetcher.getUUID(p.getName()).toString()+"'");
				p.sendMessage(Utils.prefix + "§cDu hast die Freundschaft mit §e" + pname + "§c beendet!");
				ProxiedPlayer t = this.main.getProxy().getPlayer(pname);
				if(t!=null) {
					t.sendMessage(Utils.prefix + "§cDer Spieler §e" + p.getName() + "§c hat die Freundschaft mit dir beendet!");
				}
			}else {p.sendMessage(Utils.prefix + "§cAchtung du bist mit diesem Spieler nicht befreundet!"); Thread.sleep(100); return;}
			Thread.sleep(100);
		}catch(Exception e) {}
	}
}
