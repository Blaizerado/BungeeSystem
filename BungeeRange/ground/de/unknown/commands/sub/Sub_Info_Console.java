package de.unknown.commands.sub;


import de.unknown.bungeecord.BungeeRange;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.command.ConsoleCommandSender;

public class Sub_Info_Console {

	public Sub_Info_Console(ConsoleCommandSender c, String[] args, BungeeRange main) {
		if(Player_Account.playExists(uuidfetcher.getUUID(args[0]).toString())) {
			String UUID = uuidfetcher.getUUID(args[0]).toString();
			c.sendMessage("§3<============>§2Info§3<============>");
			c.sendMessage("§cName: §3" + Player_Account.getName(UUID));
			c.sendMessage("§cUUID: §3" + UUID);
			c.sendMessage("§cIP: §3" + Player_Account.getIP(UUID));
			c.sendMessage("§cWarns: §3" + Player_Account.getWarns(UUID));
			c.sendMessage("§cKicks: §3" + Player_Account.getKicks(UUID));
			c.sendMessage("§cBans: §3" + Player_Account.getBans(UUID));
			c.sendMessage("§cGebant: §2 Nein");
			c.sendMessage("§3<==============================>");
		}else {c.sendMessages(Utils.prefix + "§cDer Spieler ist nicht in der Datenbank aufgeführt!");}
	}

}
