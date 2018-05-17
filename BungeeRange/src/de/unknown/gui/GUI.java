package de.unknown.gui;


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.unknown.modul.LoadModul;
import de.unknown.mysql.MySQL;

public class GUI extends JavaPlugin{
	
	private static GUI in;
	public static MySQL my;
	public static HashMap<Player, ArrayList<String>>UUIDs = new HashMap<>();
	public static HashMap<Player, ArrayList<String>>request = new HashMap<>();
	@Override
	public void onEnable() {
		in = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		new LoadModul(this);
		this.my = new MySQL("84.200.24.26", "test", "Minecraft", "AAAA11cc");
	}
	
	@Override
	public void onDisable() {

	}
	
	public static Plugin getinstance() {
		return in;
	}
	
}
