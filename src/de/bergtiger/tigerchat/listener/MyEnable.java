package de.bergtiger.tigerchat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.PluginEnableEvent;

import de.bergtiger.tigerchat.TigerChat;

public class MyEnable extends MyListener {

	public MyEnable(TigerChat plugin) {
		super(plugin);
	}

	@EventHandler
	public void onEnable(PluginEnableEvent e) {
		if(e.getPlugin().getName().equalsIgnoreCase("PermissionsEx")){
			
		}
	}
}
