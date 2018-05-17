package de.unknown.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		if(!e.getPlayer().getInventory().contains(Material.SKULL_ITEM)) {
			ItemStack i = new ItemStack(Material.SKULL_ITEM,1,(short)3);
			SkullMeta im = (SkullMeta) i.getItemMeta();
			im.setOwner(e.getPlayer().getName());
			im.setDisplayName("§8<§eDeine Freunde§8>");
			i.setItemMeta(im);
			e.getPlayer().getInventory().addItem(i);
		}
	}
}
