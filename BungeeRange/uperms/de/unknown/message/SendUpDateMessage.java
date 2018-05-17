package de.unknown.message;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

public class SendUpDateMessage {
	public static void sendData(String Message, String Permission,String Group, String addorremov) {
		String msg = Message+","+Permission+","+Group+","+addorremov;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);
		try {
			out.writeUTF("UPERMS_UPDATE");
			out.writeUTF(msg);
			for(ServerInfo info : ProxyServer.getInstance().getServers().values()) {
				info.sendData("PERM_UPDATE", stream.toByteArray());
			}
		} catch (IOException e) {}
	}
	
	public static void sendGroupUpdate(String Message,String group, String UUID, String addorremove) {
		String msg = Message+","+group+","+UUID+","+addorremove;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(stream);
		try {
			out.writeUTF("UPERMS_UPDATE");
			out.writeUTF(msg);
			for(ServerInfo info : ProxyServer.getInstance().getServers().values()) {
				info.sendData("PERM_UPDATE", stream.toByteArray());
			}
		} catch (IOException e) {}
	}
}
