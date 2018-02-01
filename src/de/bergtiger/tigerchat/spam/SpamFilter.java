package de.bergtiger.tigerchat.spam;

import org.bukkit.entity.Player;

public interface SpamFilter {
	
	/**
	 * 
	 * @param p
	 * @param message
	 * @return true when message is spam
	 */
	public boolean handle(Player p, String message);
	
	/**
	 * @return FilterType (Pre/Post)
	 */
	public FilterType type();

}
