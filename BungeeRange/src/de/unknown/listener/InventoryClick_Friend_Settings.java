package de.unknown.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.unknown.bungeecommand.SendCommand;
import de.unknown.gui.GUI;

public class InventoryClick_Friend_Settings implements Listener {

	public InventoryClick_Friend_Settings(GUI gui) {
		
	}

	@EventHandler
	public void onInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory() == null)return;
		if(e.getCurrentItem() == null)return;
		if(e.getCurrentItem().getItemMeta() == null)return;
		
		if(GUI.UUIDs.get(p).contains(e.getInventory().getName().replace("§3", ""))) {
			e.setCancelled(true);
			switch(e.getCurrentItem().getItemMeta().getDisplayName()) {
				case "§3§lFolgen(Comming Soon!)":
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				break;
				
				case "§c§lIn Party einladen!":
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				break;
				
				case "§e§lIn Clan einladen!":
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				break;
				
				case "§e§lNachricht schreiben!":
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				break;
				
				case "§c§lSpieler Löschen!":
					Inventory inv = e.getInventory();
					inv.clear();
					
					ItemStack i = new ItemStack(Material.WOOL,1,(short)13);
					ItemMeta im = i.getItemMeta();
					im.setDisplayName("§aLöschen!");
					im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					i.setItemMeta(im);
					inv.setItem(2, i);
					
					i = new ItemStack(Material.WOOL,1,(short)14);
					im = i.getItemMeta();
					im.setDisplayName("§cAbbrechen!");
					im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					i.setItemMeta(im);
					inv.setItem(6, i);
					
					p.updateInventory();
				break;
				
				case "§cAbbrechen!":
					p.closeInventory();
				break;
				
				case "§aLöschen!":
					SendCommand.sendCommand("friend remove " + e.getInventory().getName().replace("§3", ""), p);
				break;
			}
		}
	}
	
}
