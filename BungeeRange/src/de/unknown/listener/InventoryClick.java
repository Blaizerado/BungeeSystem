package de.unknown.listener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.unknown.gui.GUI;
import de.unknown.mysql.MySQL_GetResuestInventory;
import de.unknown.uuid.uuidfetcher;

public class InventoryClick implements Listener {
	
	public InventoryClick(GUI gui) {
		
	}
	
	@EventHandler
	public void onInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory() == null) {return;}
		if(e.getCurrentItem() == null) {return;}
		if(e.getInventory().getName() == null) {return;}
		if(e.getInventory().getName().equalsIgnoreCase("§3Freunde")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getType().equals(Material.SKULL_ITEM)) {
				if(e.getCurrentItem().getItemMeta() != null) {
					Inventory inv = Bukkit.createInventory(null, 9, "§3" + e.getCurrentItem().getItemMeta().getDisplayName().replace("§c", ""));
					
					ItemStack i = new ItemStack(Material.ARROW);
					ItemMeta im = i.getItemMeta();
					
					im.setDisplayName("§3§lFolgen(Comming Soon!)");
					im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					i.setItemMeta(im);
					inv.setItem(0, i);
					
					i = new ItemStack(Material.DIAMOND_SWORD);
					im = i.getItemMeta();
					im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					im.setDisplayName("§c§lIn Party einladen!");
					i.setItemMeta(im);
					inv.setItem(2, i);
					
					i = new ItemStack(Material.GOLD_SWORD);
					im = i.getItemMeta();
					im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					im.setDisplayName("§e§lIn Clan einladen!");
					i.setItemMeta(im);
					inv.setItem(4, i);
					
					i = new ItemStack(Material.ANVIL);
					im = i.getItemMeta();
					im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					im.setDisplayName("§e§lNachricht schreiben!");
					i.setItemMeta(im);
					inv.setItem(6, i);
					
					
					i = new ItemStack(Material.BARRIER);
					im = i.getItemMeta();
					im.setDisplayName("§c§lSpieler Löschen!");
					i.setItemMeta(im);
					inv.setItem(8, i);
					
					p.openInventory(inv);
				}
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lAnfragen")) {
				Inventory inv = Bukkit.createInventory(null, 54,"§cAnfragen");
				MySQL_GetResuestInventory b = new MySQL_GetResuestInventory(p);
				ItemStack i = new ItemStack(Material.SKULL_ITEM,1,(short)3);
				SkullMeta sm = (SkullMeta) i.getItemMeta();
				if(!GUI.request.containsKey(p)) {
					GUI.request.put(p, new ArrayList<>());
				}
				for(String s : b.getRequestUUID()) {
					String name = uuidfetcher.getName(UUID.fromString(s));
					if(!GUI.request.get(p).contains(name)) {
						GUI.request.get(p).add(name);
					}
					sm.setOwner(name);
					sm.setDisplayName("§c"+name);
					i.setItemMeta(sm);
					inv.addItem(i);
				}
				
				p.openInventory(inv);
			}
		}
	}
}
