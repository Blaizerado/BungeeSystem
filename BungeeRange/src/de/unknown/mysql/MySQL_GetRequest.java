package de.unknown.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.unknown.gui.GUI;

public class MySQL_GetRequest extends Thread{
	String UU;
	Player t;
	final Inventory in;
	ItemStack s;
	public MySQL_GetRequest(Player p, String UUID, Inventory inv, ItemStack is) {
		UU = UUID;
		t = p;
		in = inv;
		s = is;
	}
	
	@Override
	public void run() {
		System.out.println("Friend Thread wird gestartet!");
		try {
			int i = 0;
			ResultSet rs = GUI.my.query("SELECT * FROM Friend_Request WHERE player_target='"+UU+"'");
			while (rs.next()) {
				i++;
			}
			if(i == 0) {
				ArrayList<String>Lore = new ArrayList<>();
				Lore.add("§3Du hast zurzeit keine Anfragen!");
				ItemMeta im = s.getItemMeta();
				im.setLore(Lore);
				s.setItemMeta(im);
				in.setItem(48, s);
			}else {
				ArrayList<String>Lore = new ArrayList<>();
				Lore.add("§3Du hast zurzeit §e" + i + "§3 Anfrage/en");
				ItemMeta im = s.getItemMeta();
				im.setLore(Lore);
				s.setItemMeta(im);
				in.setItem(48, s);
			}
			t.openInventory(in);
			Thread.sleep(100);
		} catch (Exception e) {	}
	}
}
