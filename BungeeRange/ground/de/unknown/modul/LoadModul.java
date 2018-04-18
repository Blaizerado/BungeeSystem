package de.unknown.modul;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.commands.Find;
import de.unknown.commands.Info;
import de.unknown.commands.Lobby;
import de.unknown.commands.Ping;
import de.unknown.commands.TeamChat;
import de.unknown.commands.Wartung;
import de.unknown.config.SetConfig;
import de.unknown.listener.ProxyJoin;
import de.unknown.listener.ProxyLeav;
import de.unknown.listener.ProxyLogin;
import de.unknown.mysql.MySQL;
import de.unknown.sup_command.Support;
import de.unknown.utils.Utils;

public class LoadModul {
	public LoadModul(BungeeRange bungeeRange) {
		loadMySQL(bungeeRange);
		loadConfig(bungeeRange);
		loadCommands(bungeeRange);
		loadListener(bungeeRange);
	}

	private void loadConfig(BungeeRange bungeeRange) {
		new SetConfig(bungeeRange);
		new Utils();
	}

	private void loadListener(BungeeRange bungeeRange) {
		bungeeRange.getProxy().getPluginManager().registerListener(bungeeRange, new ProxyJoin());
		bungeeRange.getProxy().getPluginManager().registerListener(bungeeRange, new ProxyLogin());
		bungeeRange.getProxy().getPluginManager().registerListener(bungeeRange, new ProxyLeav());
	}

	private void loadCommands(BungeeRange bungeeRange) {
		bungeeRange.getProxy().getPluginManager().registerCommand(bungeeRange, new Info("info",bungeeRange));
		bungeeRange.getProxy().getPluginManager().registerCommand(bungeeRange, new TeamChat("tc",bungeeRange));
		bungeeRange.getProxy().getPluginManager().registerCommand(bungeeRange, new Ping("ping"));
		bungeeRange.getProxy().getPluginManager().registerCommand(bungeeRange, new Wartung("wartung",bungeeRange));
		bungeeRange.getProxy().getPluginManager().registerCommand(bungeeRange, new Lobby("lobby",bungeeRange));
		bungeeRange.getProxy().getPluginManager().registerCommand(bungeeRange, new Find("find",bungeeRange));
		bungeeRange.getProxy().getPluginManager().registerCommand(bungeeRange, new Support("support",bungeeRange));
	}

	@SuppressWarnings("static-access")
	private void loadMySQL(BungeeRange bungeeRange) {
		bungeeRange.my = new MySQL("84.200.24.26", "test", "Minecraft", "AAAA11cc");
		bungeeRange.my.update("CREATE TABLE IF NOT EXISTS Settings(Wartung int,Party int,Support int,profil int);");
		bungeeRange.my.update("CREATE TABLE IF NOT EXISTS Account(UUID varchar(64),Name varchar(20),player_kick int,player_bans int, player_warns int, player_ip varchar(50));");
		bungeeRange.my.update("CREATE TABLE IF NOT EXISTS Support(id MEDIUMINT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id),UUID varchar(50),support_reason varchar(900),support_target varchar(40),support_activ int, support_worker varchar(30), support_create varchar(50));");
	}

}