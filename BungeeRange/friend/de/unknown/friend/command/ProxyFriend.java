package de.unknown.friend.command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.friend.mysql.MySQL_Add;
import de.unknown.friend.mysql.MySQL_Deny;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ProxyFriend extends Command {

	private BungeeRange main;

	public ProxyFriend(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/friend add [Player]");
				p.sendMessage("§3/friend accept [Player]");
				p.sendMessage("§3/friend deny [Player]");
				p.sendMessage("§3/friend block [Player]");
				p.sendMessage("§3/friend unblock [Player]");
				p.sendMessage("§3/friend friends");
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("add")) {
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/friend add [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("accept")) {
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/friend accept [Player]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("add")) {
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§cDer Spieler spielt hier leider nicht!");}
					MySQL_Add b = new MySQL_Add(p, UUID, args[1], main);
					b.start();
				}else if(args[0].equalsIgnoreCase("accept")) {
					p.sendMessage("Hallo !" + args[1]);
				}else if(args[0].equalsIgnoreCase("deny")) {
					MySQL_Deny b = new MySQL_Deny(p, uuidfetcher.getUUID(p.getName()).toString(), uuidfetcher.getUUID(args[1]).toString(), main, args[1]);
					b.start();
				}
			}
		}
	}

}
