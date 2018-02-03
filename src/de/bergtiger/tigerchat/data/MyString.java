package de.bergtiger.tigerchat.data;

import org.bukkit.ChatColor;

public enum MyString {
	
	NOPERMISSION	("&cYou have no Permission to perform this command."),
	
	CHAT_NOTALLOWED	("&cYou are not allowed to write.");
	
	private String args;
	
	MyString(String args){
		this.args = args;
	}
	
	public String get(){
		return this.args;
	}
	
	public void set(String args){
		this.args = args;
	}
	
	public String colored(){
		return colories(this.get());
	}
	
	public static String colories(String args){
		return ChatColor.translateAlternateColorCodes('&', args);
	}
}
