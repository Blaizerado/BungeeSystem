package de.unknown.warn.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_DeleteTicket extends Thread{
	ProxiedPlayer p;
	Integer id;
	public MySQL_DeleteTicket(ProxiedPlayer t, Integer i) {
		p = t;
		id = i;
	}
	@Override
	public void run() {
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Warn WHERE id='"+id+"'");
			if(rs.next()) {
				p.sendMessage(Utils.prefix + "§cDu hast den Warn mit der ID §e" + id + "§c gelöscht!");
				BungeeRange.my.update("DELETE FROM Warn WHERE id='"+id+"'");
			}else {p.sendMessage(Utils.prefix + "§cAchtung diese Id gibt es nicht!");}
			Thread.sleep(100);
		} catch (Exception e) {}
	}
}
