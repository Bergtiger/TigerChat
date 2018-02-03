package de.bergtiger.tigerchat.listener;

import java.util.HashMap;

import de.bergtiger.tigerchat.TigerChat;

public class ListenerOverview {
	
	private TigerChat plugin;
	private HashMap<String, MyListener> listener = new HashMap<String, MyListener>();
	
	public ListenerOverview(TigerChat plugin) {
		this.plugin = plugin;
		this.addListener(new MyMessage(this.plugin));
	}
	
	/**
	 * @param listener
	 * @return true when listener was added
	 */
	public boolean addListener(MyListener listener) {
		if(listener != null) {
			if(this.listener != null) {
				this.listener.put(listener.getClass().getSimpleName(), listener);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * same as removeListener(String)
	 * @param listener
	 * @return true when listener was removed
	 */
	public boolean removeListener(MyListener listener) {
		return this.removeListener(listener.getClass().getSimpleName());
	}
	
	/**
	 * save remove (with listener disable)
	 * @param listener
	 * @return true when listener was emoved
	 */
	public boolean removeListener(String listener) {
		if((this.listener != null) && (!this.listener.isEmpty())) {
			MyListener myListener = this.listener.remove(listener);
			if(myListener != null) {
				myListener.end();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * aktivates all listener
	 */
	public void aktivate() {
		if((this.listener != null) && (!this.listener.isEmpty())) {
			this.listener.forEach((name, listener) -> {
				listener.aktivate();
			});
		}
	}
	
	/**
	 * disables all listener
	 */
	public void disable() {
		if((this.listener != null) && (!this.listener.isEmpty())) {
			this.listener.forEach((name, listener) -> {
				listener.disable();
			});
		}
	}
	
	public void end() {
		if((this.listener != null) && (!this.listener.isEmpty())) {
			this.listener.forEach((name, listener) -> {
				listener.end();
			});
		}
	}
}
