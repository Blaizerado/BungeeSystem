package de.unknown.ban.mysql;


import de.unknown.bungeecord.BungeeRange;

public class Perma_Ban extends Thread{
	String UUID;
	String BUUID;
	String reason;
	String bname;
	String tname;
	String id;
	Long bancreate;
	int unban;
	int ipban;
	public Perma_Ban(String UID, String BUID,String re, String bn, String tn, String i, Long banc, Boolean unb, Boolean ipb) {
		
		if(unb) {unban = 1;}else{unban = 0;}
		if(ipb) {ipban = 1;}else{ipban = 0;}
		UUID = UID;
		BUUID = BUID;
		reason = re;
		bname = bn;
		tname = tn;
		id = i;
		bancreate = banc;
	}
	
	@Override
	public void run() {
		try{
			System.out.println("Ban Thread gestartet!");
			System.out.println(unban);
			BungeeRange.my.update("INSERT INTO PermaBan(UUID,ban_active,ban_banner,ban_bname,ban_tname,ban_create,unbane_chance,ip_ban,ban_reason,ban_id)"
			+ " VALUES ('"+UUID+"','1','"+BUUID+"','"+bname+"','"+tname+"','"+bancreate+"','"+unban+"','"+ipban+"','"+reason+"','"+id+"');");
			
			Thread.sleep(100); System.out.println("Ban Thread geschlossen!");
		}catch(Exception e) {e.printStackTrace();}
	}
}
