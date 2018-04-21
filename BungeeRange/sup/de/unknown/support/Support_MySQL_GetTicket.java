package de.unknown.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Support_MySQL_GetTicket extends Thread{
	ProxiedPlayer p;
	Integer id;
	public Support_MySQL_GetTicket(ProxiedPlayer a, Integer b) {
		p = a;
		id = b;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run(){
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String UUID = null;
		String reason = null;
		String target = null;
		Integer activ = null;
		String worker = null;
		Long create = null;
		try {
		System.out.println("Der Support Thread wurde gestartet!");
		ResultSet rs;
		p.sendMessage(new TextComponent(""));
		rs = BungeeRange.my.query("SELECT * FROM Support where id='"+id+"'");
		if(rs.next()) {
			
			UUID = rs.getString("NHName");
			System.out.println("Ka:" +  rs.getString("NHName"));
			target = rs.getString("TName");
			reason = rs.getString("support_reason");
			activ = rs.getInt("support_activ");
			worker = rs.getString("support_worker");
			create = rs.getLong("support_create");
		}
			
		if(UUID != null) {
			p.sendMessage("§cGrund:§8:§d " + reason);
			p.sendMessage("§cReporter§8:§d " + UUID);
			p.sendMessage("§cBeschuldigter§8:§d" + target);
			if(activ == 1) {
				p.sendMessage("§cActiv§8:§2 Ja");
			}else if(activ == 2) {
				p.sendMessage("§cActiv§8:§4 Nein");
			}
			if(worker.equalsIgnoreCase("frei")) {
				p.sendMessage("§cStatus§8:§2 Offen!");
			}else {p.sendMessage("§cStatus§8:§4 Geschlossen!");}
			p.sendMessage("§cErstellt§8:§d " + df.format(create));
		}else {
			p.sendMessage(Utils.prefix + "§cAchtung dieses Ticket wurde nicht erstellt!");
			Thread.sleep(100);
			System.out.println("Support Thread geschlossen!");
			return;
		}
		
		Thread.sleep(100);
		System.out.println("Support Thread geschlossen!");
		} catch (SQLException | InterruptedException e) {p.sendMessage(Utils.prefix + "§cDieses Ticket gibt es Nicht!"); e.printStackTrace();}
	}
}
