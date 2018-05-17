package de.unknown.bungeecommand;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.unknown.gui.GUI;

public class SendCommand {
	public static void sendCommand(String command, Player sender)
	  {
	    Object[] players = Bukkit.getServer().getOnlinePlayers().toArray();
	    if (players.length == 0) return;
	    String commandprefix = "C4B";
	    if (sender == null) {
	      sender = (Player)players[0];
	      System.out.println("Sende Command:'" + command + "'...");
	    } else {
	      commandprefix = "PC4B";
	      System.out.println("Sende Command:'" + command + "'für" + sender.getName() + "...");
	    }

	    ByteArrayOutputStream b = new ByteArrayOutputStream();
	    DataOutputStream out = new DataOutputStream(b);
	    try
	    {
	      out.writeUTF(commandprefix + "§" + command);
	    } catch (IOException ee) {
	      ee.printStackTrace();
	    }

	    sender.sendPluginMessage(GUI.getinstance(), "BungeeCord", b.toByteArray());
	  }
}
