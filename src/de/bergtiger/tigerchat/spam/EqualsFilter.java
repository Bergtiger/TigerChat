package de.bergtiger.tigerchat.spam;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import de.bergtiger.tigerchat.TigerChat;

public class EqualsFilter implements SpamFilter {

	private TigerChat plugin;
	private HashMap<String, List<String>> players = new HashMap<String, List<String>>();
	private int maxMessages = 10;
	private int maxCount = 5;
	
	public EqualsFilter() {
		
	}

	@Override
	public boolean handle(Player p, String message) {
		if((p != null) && (message != null)) {
			if(this.players.containsKey(p.getName())) {
				List<String> messages = this.players.get(p.getName());
				messages.add(message);
				int count = 0;
				for(String m : messages) {
					if(m.equals(message)) {
						count++;
					}
				}
				if(messages.size() > maxMessages) {
					messages.remove(0);
				}
				if(count > maxCount) {
					return true;
				}
			} else {
				this.players.put(p.getName(), Arrays.asList(message));
			}
		}
		return false;
	}

	@Override
	public FilterType type() {
		return FilterType.Pre;
	}

}
