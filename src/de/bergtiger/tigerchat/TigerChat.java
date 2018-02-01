package de.bergtiger.tigerchat;

import org.bukkit.plugin.java.JavaPlugin;

import de.bergtiger.tigerchat.listener.ListenerOverview;
import de.bergtiger.tigerchat.spam.SpamOverview;

public class TigerChat extends JavaPlugin {

	private Config config;
	private SpamOverview sOver;
	private ListenerOverview lOver;
	
	@Override
	public void onEnable() {
		
		this.config = new Config(this);
		
		this.sOver = new SpamOverview(this);
		this.lOver = new ListenerOverview(this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
}
