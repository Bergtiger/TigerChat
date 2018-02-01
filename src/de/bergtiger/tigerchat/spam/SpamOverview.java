package de.bergtiger.tigerchat.spam;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.bergtiger.tigerchat.TigerChat;

public class SpamOverview {

	private TigerChat plugin;
	private List<SpamFilter> preFilter = new ArrayList<SpamFilter>();
	private List<SpamFilter> postFilter = new ArrayList<SpamFilter>();
	
	public SpamOverview(TigerChat plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * adds a filter to list
	 * @param filter
	 * @return true when filter was added
	 */
	public boolean addFilter(SpamFilter filter) {
		if(filter != null) {
			if(filter.type() == FilterType.Pre) {
				return this.addPreFilter(filter);
			} else {
				return this.addPostFilter(filter);
			}
		}
		return false;
	}

	public boolean addPreFilter(SpamFilter filter) {
		if(filter != null) {
			
		}
		return false;
	}
	
	public boolean addPostFilter(SpamFilter filter) {
		if(filter != null) {
			
		}
		return false;
	}
	
	
	public boolean removeFilter(SpamFilter filter) {
		return false;
	}
	
	public boolean removePreFilter() {
		return false;
	}
	
	public boolean removePostFilter() {
		return false;
	}
	
	
	/**
	 * Can block player's message. Can cause Server lags
	 * @param p
	 * @param message
	 * @return true when message is spam in one preFilter (first fit)
	 */
	public boolean isSpam(Player p, String message) {
		for(SpamFilter filter : this.preFilter) {
			return filter.handle(p, message);
		}
		return false;
	}

	/**
	 * Does not block player's message. Does not stop Server main thread.
	 * @param p
	 * @param message
	 */
	public void postSpam(Player p, String message) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
			
			@Override
			public void run() {
				for(SpamFilter filter : postFilter) {
					filter.handle(p, message);
				}
			}
		});
	}
}
