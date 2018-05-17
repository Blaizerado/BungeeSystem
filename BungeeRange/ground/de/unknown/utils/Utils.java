package de.unknown.utils;

import de.unknown.config.SetConfig;
import net.md_5.bungee.api.ChatColor;

public class Utils {
	
	public static int profil = 0;
	public static String prefix = "";
	public static Boolean Support = false;
	public static Boolean Party = false;
	public static Boolean Wartung = false;

	public Utils() {
		profil = SetConfig.config.getInt("Config.Profil");
		prefix = ChatColor.translateAlternateColorCodes('&', SetConfig.config.getString("Config.Prefix"));
		
		System.out.println("Profil:" + profil + "\nSupport:" + Support + "\nParty:"+Party+"\nWartung:"+Wartung);
	}
	
}
