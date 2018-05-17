package de.unknown.packetcommands;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import de.unknown.bungeecord.BungeeRange;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChannelNeed implements Listener{
	
	private BungeeRange main;
	
	public ChannelNeed(BungeeRange bungeeRange) {
		this.main = bungeeRange;
	}

	@EventHandler
	public void onNeed(PluginMessageEvent e) {
		System.out.println("Nachricht erhalten!");
		 String[] bytedata = readData(e.getData());
		 System.out.println(bytedata[0]);
		    for (String line : bytedata)
		      if (line.startsWith("C4B§")) {
		        String command = line.split("§")[1];
		        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), command);
		      } else if (line.startsWith("PC4B§")) {
		        String command = line.split("§")[1];
		        ProxiedPlayer p = BungeeCord.getInstance().getPlayer(e.getReceiver().toString());
		        if (p != null)
		        ProxyServer.getInstance().getPluginManager().dispatchCommand(p, command);
		        else
		        System.out.println("Command kann nicht gefunden werde'" + command + "': Spieler Ist Offline");
		}
	}
	
	private String[] readData(byte[] data)
	  {
	    List<String> readed = new ArrayList<>();
	    DataInputStream di = new DataInputStream(new ByteArrayInputStream(data));
	    for (int i = 0; i < 255; i++)
	    {
	      try
	      {
	        String dr = di.readUTF();
	        readed.add(dr);
	      }
	      catch (IOException e)
	      {
	        if (readed.size() != 0)
	        {
	          return readed.toArray(new String[readed.size()]);
	        }

	        return new String[] { new String(data, Charset.forName("UTF-8")) };
	      }

	    }

	    String[] out = readed.toArray(new String[readed.size()]);
	    return out;
	  }
}
