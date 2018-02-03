package de.bergtiger.tigerchat.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import de.bergtiger.tigerchat.TigerChat;

public class MyListener implements Listener{

	protected TigerChat plugin;
	protected boolean aktiv;
	
	public MyListener(TigerChat plugin) {
		this.plugin = plugin;
	}
	
	
	/**
	 * aktivates Listener
	 */
	public void aktivate() {
		if(!aktiv) {
			Bukkit.getPluginManager().registerEvents(this, this.plugin);
		}
	}
	
	/**
	 * disables Listener (short reaktivate)
	 */
	public void disable() {
		if(aktiv) {
			HandlerList.unregisterAll(this);
		}
	}
	
	/**
	 * save end
	 */
	public final void end() {
		if(aktiv) {
			HandlerList.unregisterAll(this);
		}
	}
}
