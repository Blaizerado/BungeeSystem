package de.unknown.bungeecord;

import de.unknown.modul.LoadModul;
import de.unknown.mysql.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeRange extends Plugin{

	public static MySQL my;
	private static BungeeRange b;
	@Override
	public void onEnable() {
		b = this;
		BungeeCord.getInstance().registerChannel("PERM_UPDATE");
		BungeeCord.getInstance().registerChannels();
		new LoadModul(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static Plugin getInstanze() {
		return b;
	}
}
