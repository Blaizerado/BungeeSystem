package de.unknown.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import de.unknown.gui.GUI;

public class MySQL_GetFriends extends Thread{
	private static ArrayList<String>friend = new ArrayList<>();
	private static String pUUID;
	
	public MySQL_GetFriends(String UUID) {
		this.pUUID = UUID;
	}
	
	public static ArrayList<String> getIDs() {
		System.out.println("Friend Thread wird gestartet!");
		friend.clear();
		try {
			ResultSet rs = GUI.my.query("SELECT * FROM Friend_Friend WHERE UUID1='"+pUUID+"' UNION SELECT * FROM Friend_Friend WHERE UUID2='"+pUUID+"'");
			while (rs.next()) {
				if(!rs.getString("UUID1").equalsIgnoreCase(pUUID)) {
					friend.add(rs.getString("UUID1"));
				}
				if(!rs.getString("UUID2").equalsIgnoreCase(pUUID)) {
					friend.add(rs.getString("UUID2"));
				}
			}
			Thread.sleep(100);
		} catch (Exception e) {}
		return friend;
	}
}
