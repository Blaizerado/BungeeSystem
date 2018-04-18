package de.unknown.mysql;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.utils.Utils;

public class Server_Console {
	public static void updateWartung(int i) {
		BungeeRange.my.update("UPDATE Settings SET Wartung='"+i+"' WHERE profil='1'");
		new Utils();
	}
}
