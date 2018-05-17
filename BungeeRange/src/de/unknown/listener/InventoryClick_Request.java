package de.unknown.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.unknown.bungeecommand.SendCommand;
import de.unknown.gui.GUI;

public class InventoryClick_Request implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory()== null)return;
		if(e.getCurrentItem() == null)return;
		if(e.getCurrentItem().getItemMeta() == null)return;
		
		if(e.getInventory().getName().equalsIgnoreCase("§cAnfragen")) {
			e.setCancelled(true);
			if(e.getCurrentItem().getType().equals(Material.SKULL_ITEM)) {
				if(GUI.request.get(p).contains(e.getCurrentItem().getItemMeta().getDisplayName().replace("§c", ""))) {
					Inventory inv = Bukkit.createInventory(null, 9, "§3" + e.getCurrentItem().getItemMeta().getDisplayName().replace("§c", ""));
					ItemStack i = new ItemStack(Material.WOOL,1,(short)13);
					ItemMeta im = (ItemMeta) i.getItemMeta();
					im.setDisplayName("§aAnnehmen!");
					i.setItemMeta(im);
					inv.setItem(2, i);
					
					i = new ItemStack(Material.WOOL,1,(short)14);
					im = i.getItemMeta();
					im.setDisplayName("§cAblehnen!");
					i.setItemMeta(im);
					inv.setItem(6, i);
					
					p.openInventory(inv);
				}
			}
		}else if(GUI.request.get(p).contains(e.getInventory().getName().replace("§3", ""))) {
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAblehnen!")) {
				SendCommand.sendCommand("friend deny " + e.getInventory().getName().replace("§3", ""), p);
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAnnehmen!")) {
				SendCommand.sendCommand("friend accept " + e.getInventory().getName().replace("§3", ""), p);
			}
		}
	}
}
