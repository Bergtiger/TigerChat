package de.bergtiger.tigerchat.data;

public enum MyPermission {
	
	ADMIN			("tigerchat.admin"),
	PLAYER			("tigerchat.player"),
	
	CHAT			("tigerchat.chat"),
	CHAT_COLOR		("tigerchat.chat.color"),
	CHAT_FORMAT		("tigerchat.chat.format"),
	CHAT_FORMAT_K	("tigerchat.chat.format.k");
	
	private String args;
	
	private MyPermission(String args) {
		this.args = args;
	}
	
	public String get() {
		return this.args;
	}
}
