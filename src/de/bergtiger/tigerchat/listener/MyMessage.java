package de.bergtiger.tigerchat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.bergtiger.tigerchat.TigerChat;

public class MyMessage extends MyListener {

	public MyMessage(TigerChat plugin) {
		super(plugin);
	}
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent e) {
		
	}

}
