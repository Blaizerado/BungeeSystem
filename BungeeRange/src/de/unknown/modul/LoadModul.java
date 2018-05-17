package de.unknown.modul;


import de.unknown.gui.GUI;
import de.unknown.listener.InterAcctListener;
import de.unknown.listener.InventoryClick_Request;
import de.unknown.listener.InventoryClick;
import de.unknown.listener.InventoryClick_Friend_Settings;
import de.unknown.listener.PlayerJoinListener;

public class LoadModul {

	@SuppressWarnings("unused")
	private GUI main;

	public LoadModul(GUI gui) {
		loadListener(gui);
	}

	private void loadListener(GUI gui) {
		gui.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), gui);
		gui.getServer().getPluginManager().registerEvents(new InterAcctListener(), gui);
		gui.getServer().getPluginManager().registerEvents(new InventoryClick(gui), gui);
		gui.getServer().getPluginManager().registerEvents(new InventoryClick_Friend_Settings(gui), gui);
		gui.getServer().getPluginManager().registerEvents(new InventoryClick_Request(), gui);
	}
}
