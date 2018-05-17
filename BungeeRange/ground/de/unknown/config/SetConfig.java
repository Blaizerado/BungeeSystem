package de.unknown.config;

import java.io.File;
import java.io.IOException;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class SetConfig {
	public static Configuration config;
	@SuppressWarnings("static-access")
	public SetConfig(BungeeRange bungeeRange) {
		if(!bungeeRange.getDataFolder().exists()) {
			bungeeRange.getDataFolder().mkdir();
		}
		
		File f = new File(bungeeRange.getDataFolder().getPath(), "Config.yml");
		if(!f.exists()) {
			try {f.createNewFile();} catch (IOException e) {}
			System.out.println("file Create");
		}
		try {config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(f);} catch (IOException e) {e.printStackTrace();}
		if(config.get("Config.Profil") == null) {
			config.set("Config.Profil", 1);
		}
		if(config.get("Config.Prefix") == null) {
			config.set("Config.Prefix", "&cUnknown_Bungee&8:&3 ");
		}
		try {ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, f);} catch (IOException e) {}
	}

}
