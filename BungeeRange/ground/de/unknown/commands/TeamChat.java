package de.unknown.commands;


import de.unknown.bungeecord.BungeeRange;
import de.unknown.commands.sub.Sub_TeamChat_Console;
import de.unknown.commands.sub.Sub_TeamChat_Player;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class TeamChat extends Command {

	private BungeeRange main;

	public TeamChat(String name, BungeeRange bungeeRange) {
		super(name);
		this.main = bungeeRange;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if(args.length == 0) {
				if(!p.hasPermission("Bungee.tc")) {p.sendMessage("§cAchtung das darfst du nicht!"); return;}
				p.sendMessage("§3<============>§2Info§3<============>");
				p.sendMessage("§3/tc [Text]");
				p.sendMessage("§3<==============================>");
			}else if(args.length >= 1) {
				if(p.hasPermission("Bungee.tc.execute")) {
					new Sub_TeamChat_Player(p,args,this.main);
				}
			}else {p.sendMessage(Utils.prefix + "§cAchtung gib bitte eine Nachricht ein!");}
		}else if(sender instanceof ConsoleCommandSender) {
			ConsoleCommandSender c = (ConsoleCommandSender) sender;
			if(args.length == 0) {
				c.sendMessage("§3<============>§2Info§3<============>");
				c.sendMessage("§3/tc [Text]");
				c.sendMessage("§3<==============================>");
			}else if(args.length >= 1) {
				if(c.hasPermission("Bungee.tc.execute")) {
					new Sub_TeamChat_Console(c,args,this.main);
				}
			}else {c.sendMessage(Utils.prefix + "§cAchtung gib bitte eine Nachricht ein!");}
		}
	}

}
