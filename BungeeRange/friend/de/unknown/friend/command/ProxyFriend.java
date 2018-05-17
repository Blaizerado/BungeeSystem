package de.unknown.friend.command;


import de.unknown.bungeecord.BungeeRange;
import de.unknown.friend.mysql.MySQL_Accept;
import de.unknown.friend.mysql.MySQL_Add;
import de.unknown.friend.mysql.MySQL_Deny;
import de.unknown.friend.mysql.MySQL_GetFriends;
import de.unknown.friend.mysql.MySQL_Remove;
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
				p.sendMessage("§3/friend friends");
				p.sendMessage("§3/friend deny [Player]");
				p.sendMessage("§3/friend friends");
				p.sendMessage("§3/friend remove [Player]");
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
				}else if(args[0].equalsIgnoreCase("remove")){
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/friend remove [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("friends")) {
					MySQL_GetFriends b = new MySQL_GetFriends(uuidfetcher.getUUID(p.getName()).toString(), p);
					b.start();
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("add")) {
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					if(args[1].equalsIgnoreCase(p.getName())) {p.sendMessage(Utils.prefix + "§cDu kannst dir selber keine Anfrage senden!"); return;}
					if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix + "§cDer Spieler spielt hier leider nicht!");}
					MySQL_Add b = new MySQL_Add(p, UUID, args[1], main);
					b.start();
				}else if(args[0].equalsIgnoreCase("accept")) {
					MySQL_Accept b = new MySQL_Accept(p, uuidfetcher.getUUID(args[1]).toString(), args[1], this.main);
					b.start();
				}else if(args[0].equalsIgnoreCase("deny")) {
					MySQL_Deny b = new MySQL_Deny(p, uuidfetcher.getUUID(p.getName()).toString(), uuidfetcher.getUUID(args[1]).toString(), main, args[1]);
					b.start();
				}else if(args[0].equalsIgnoreCase("remove")) {
					MySQL_Remove b = new MySQL_Remove(p, uuidfetcher.getUUID(args[1]).toString(), args[1],this.main);
					b.start();
				}
			}
		}
	}
}
