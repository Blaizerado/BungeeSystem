package de.unknown.bungeecord;

import de.unknown.modul.LoadModul;
import de.unknown.mysql.MySQL;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeRange extends Plugin{

	public static MySQL my;
	
	@Override
	public void onEnable() {
		new LoadModul(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
