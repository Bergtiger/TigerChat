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
		
		this.config.create();
		this.config.check();
		
		this.sOver = new SpamOverview(this);
		this.lOver = new ListenerOverview(this);
		
		this.config.load();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public SpamOverview getSpamOverview() {
		return this.sOver;
	}
	
	public ListenerOverview getListenerOverview() {
		return this.lOver;
	}
}