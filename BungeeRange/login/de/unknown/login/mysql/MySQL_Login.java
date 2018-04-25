package de.unknown.login.mysql;

import java.sql.ResultSet;

import de.unknown.bungeecord.BungeeRange;
import de.unknown.login.utils.Utils_Login;
import de.unknown.utils.Utils;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL_Login extends Thread{
	String UUID;
	String pw;
	ProxiedPlayer t;
	public MySQL_Login(String PUUID, String Password,ProxiedPlayer p) {
		UUID = PUUID;
		pw = Password;
		t = p;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Login Thread gestartet!");
		try {
			ResultSet rs = BungeeRange.my.query("SELECT * FROM Login WHERE UUID='"+UUID+"'");
				if(rs.next()) {
					if(rs.getString("UUID") != null) {
						if(!rs.getString("login_password").equalsIgnoreCase(pw)) {t.sendMessage(Utils.prefix + "§cAchtung das angebene Passwort stimmt nicht überein!"); Thread.sleep(100); return;}
						if(rs.getInt("login_activ") == 0) {t.sendMessage(Utils.prefix + "§cAchtung, dein Login wurde gesperrt!"); Thread.sleep(100); return;}
						Utils_Login.Login.put(t, true);
						t.sendMessage(Utils.prefix + "§cDu wurdest erfolgreich freigeschaltet!");
					}else {t.sendMessage(Utils.prefix + "§cAchtung, dein Account ist nicht als Teammitglied festgelegt!"); Thread.sleep(100); return;}
				}else {t.sendMessage("§cAchtung dein Account wurde nicht gefunden!");}
			Thread.sleep(100);
		}catch(Exception e) {e.printStackTrace();}
	}
}
