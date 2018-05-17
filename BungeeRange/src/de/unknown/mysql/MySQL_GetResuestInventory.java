package de.unknown.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import de.unknown.gui.GUI;
import de.unknown.uuid.uuidfetcher;

public class MySQL_GetResuestInventory extends Thread{
	String cUUID;
	ArrayList<String>request = new ArrayList<>();
	public MySQL_GetResuestInventory(Player t) {
		cUUID = uuidfetcher.getUUID(t.getName()).toString();
	}
	
	public ArrayList<String> getRequestUUID() {
		try {
			request.clear();
			ResultSet rs = GUI.my.query("SELECT * FROM Friend_Request WHERE player_target='"+cUUID+"'");
			while (rs.next()) {
				request.add(rs.getString("player_requestet"));
			}
			Thread.sleep(100);
		}catch(Exception e) {e.printStackTrace();}
		return request;
	}
}
