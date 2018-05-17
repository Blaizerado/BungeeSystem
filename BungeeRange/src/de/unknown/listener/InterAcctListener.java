package de.unknown.listener;


import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.unknown.gui.GUI;
import de.unknown.mysql.MySQL_GetFriends;
import de.unknown.mysql.MySQL_GetRequest;
import de.unknown.uuid.uuidfetcher;


public class InterAcctListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void InterAcct(PlayerInteractEvent e) {
		if(e.getItem() == null) return;
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(e.getPlayer().getItemInHand().getType().equals(Material.SKULL_ITEM)) {
				if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8<§eDeine Freunde§8>")) {
					e.setCancelled(true);
					
					MySQL_GetFriends b = new MySQL_GetFriends(uuidfetcher.getUUID(e.getPlayer().getName()).toString());
					
					
					
					Inventory inv = Bukkit.createInventory(null, 54, "§3Freunde");
					ItemStack i;
					ItemMeta im;
					
					ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short)3);
					SkullMeta sm = (SkullMeta) skull.getItemMeta();
					
					if(!GUI.UUIDs.containsKey(e.getPlayer())) {
						GUI.UUIDs.put(e.getPlayer(), new ArrayList<>());
					}
					
					for(String s : b.getIDs()) {
						UUID uu = UUID.fromString(s);
						String name = uuidfetcher.getName(uu);
						sm.setOwner(name);
						sm.setDisplayName("§c"+name);
						skull.setItemMeta(sm);
						inv.addItem(skull);
						if(!GUI.UUIDs.get(e.getPlayer()).contains(s)) {
							GUI.UUIDs.get(e.getPlayer()).add(name);
						}
					}
					
					
					i = new ItemStack(Material.ENDER_CHEST);
					im = i.getItemMeta();
					im.setDisplayName("§d§lEinstellungen");
					i.setItemMeta(im);
					inv.setItem(50, i);
					
					i = new ItemStack(Material.BARRIER);
					im = i.getItemMeta();
					im.setDisplayName("§c§lZurück");
					i.setItemMeta(im);
					inv.setItem(45, i);
					
					i = new ItemStack(Material.ARMOR_STAND);
					im = i.getItemMeta();
					im.setDisplayName("§a§lWeiter");
					i.setItemMeta(im);
					inv.setItem(53, i);
					
					i = new ItemStack(Material.CHEST);
					im = i.getItemMeta();
					im.setDisplayName("§e§lAnfragen");
					i.setItemMeta(im);
					MySQL_GetRequest h = new MySQL_GetRequest(e.getPlayer(), uuidfetcher.getUUID(e.getPlayer().getName()).toString(), inv, i);
					h.start();
				}
			}
		}
	}
	
}
