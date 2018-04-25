package de.unknown.login.command;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.login.mysql.MySQL_Login;
import de.unknown.login.mysql.MySQL_Block;
import de.unknown.login.mysql.MySQL_Create;
import de.unknown.login.mysql.MySQL_Delete;
import de.unknown.login.mysql.MySQL_UnBlock;
import de.unknown.login.utils.Utils_Login;
import de.unknown.mysql.Player_Account;
import de.unknown.utils.Utils;
import de.unknown.uuid.uuidfetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class ProxyLoginCommand extends Command {

	public ProxyLoginCommand(String name, BungeeRange bungeeRange) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.login.info")) {p.sendMessage("§cDas darfst du nicht!"); return;}
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/login login [Passwort]");
				p.sendMessage("§3/login create [Player] [Passwort]");
				p.sendMessage("§3/login block [Player]");
				p.sendMessage("§3/login unblock [Player]");
				p.sendMessage("§3/login delete [Player]");
				p.sendMessage("§3<==============================>");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("login")) {
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/login login [Passwort]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.login.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/login create [Player] [Passwort]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("block")) {
					if(!p.hasPermission("Bungee.login.block")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung, du bist nicht eingeloggt!"); return;}
					if(!Utils_Login.Login.get(p)) {p.sendMessage(Utils.prefix + "§cAchtung, um diesen Command nutzten zu könen, musst du dich einloggen!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/login block [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("unblock")) {
					if(!p.hasPermission("Bungee.login.unblock")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung, du bist nicht eingeloggt!"); return;}
					if(!Utils_Login.Login.get(p)) {p.sendMessage(Utils.prefix + "§cAchtung, um diesen Command nutzten zu könen, musst du dich einloggen!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/login unblock [Player]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("delete")) {
					if(!p.hasPermission("Bungee.login.delete")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung, du bist nicht eingeloggt!"); return;}
					if(!Utils_Login.Login.get(p)) {p.sendMessage(Utils.prefix + "§cAchtung, um diesen Command nutzten zu könen, musst du dich einloggen!");}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/login delete [Player]");
					p.sendMessage("§3<==============================>");
				}
			}else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("login")) {
					String Password = args[1];
					MySQL_Login b = new MySQL_Login(uuidfetcher.getUUID(p.getName()).toString(), Password, p);
					b.start();
					System.out.println("Password");
				}else if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.login.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					p.sendMessage("§3<============>§2Info§3<============>");
					p.sendMessage("§3/login create [Player] [Passwort]");
					p.sendMessage("§3<==============================>");
				}else if(args[0].equalsIgnoreCase("block")) {
					if(!p.hasPermission("Bungee.login.block")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung, du bist nicht eingeloggt!"); return;}
					if(!Utils_Login.Login.get(p)) {p.sendMessage(Utils.prefix + "§cAchtung, um diesen Command nutzten zu könen, musst du dich einloggen!"); return;}
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix +"§cAchtung der Spieler ist nicht in der Datenbank!"); return;}
					MySQL_Block b = new MySQL_Block(UUID, args[1], p);
					b.start();
				}else if(args[0].equalsIgnoreCase("unblock")) {
					if(!p.hasPermission("Bungee.login.unblock")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung, du bist nicht eingeloggt!"); return;}
					if(!Utils_Login.Login.get(p)) {p.sendMessage(Utils.prefix + "§cAchtung, um diesen Command nutzten zu könen, musst du dich einloggen!"); return;}
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix +"§cAchtung der Spieler ist nicht in der Datenbank!"); return;}
					MySQL_UnBlock b = new MySQL_UnBlock(UUID, args[1], p);
					b.start();
				}else if(args[0].equalsIgnoreCase("delete")) {
					if(!p.hasPermission("Bungee.login.delete")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					if(!Utils_Login.Login.containsKey(p)) {p.sendMessage(Utils.prefix + "§cAchtung, du bist nicht eingeloggt!"); return;}
					if(!Utils_Login.Login.get(p)) {p.sendMessage(Utils.prefix + "§cAchtung, um diesen Command nutzten zu könen, musst du dich einloggen!"); return;}
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix +"§cAchtung der Spieler ist nicht in der Datenbank!"); return;}
					MySQL_Delete b = new MySQL_Delete(p, UUID, args[1]);
					b.start();
				}
			}else if(args.length == 3) {
				if(args[0].equalsIgnoreCase("create")) {
					if(!p.hasPermission("Bungee.login.create")) {p.sendMessage("§cDas darfst du nicht!"); return;}
					String UUID = uuidfetcher.getUUID(args[1]).toString();
					if(!Player_Account.playExists(UUID)) {p.sendMessage(Utils.prefix +"§cAchtung der Spieler ist nicht in der Datenbank!"); return;}
					String password = args[2];
					MySQL_Create b = new MySQL_Create(p, UUID, password, args[1]);
					b.start();
				}
			}
		}else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender c = (ConsoleCommandSender) sender;
			c.sendMessage("§cAchtung auf Gründen der Sicherheit ist es nicht möglich diesen Command zu benutzten!");
		}
	}

}
