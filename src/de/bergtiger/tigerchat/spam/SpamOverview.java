package de.bergtiger.tigerchat.spam;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
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
		this.loadFilters();
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
	
	private void loadFilters() {
		File file = new File("plugins/" + this.plugin.getName() + "/filter");
		if(file.exists()) {
			if(file.isDirectory()) {
				String[] files = file.list();
				for(int i = 0; i < files.length; i++) {
					this.loadFilter(files[i]);
				}
			}
		}
	}
	
	private void loadFilter(String file) {
		System.out.println(1);
		this.plugin.getLogger().info("loading filter " + file);
		System.out.println(2);
//		ClassLoader parentLoader = this.getClass().getClassLoader();
		System.out.println(3);
		try {
			URL[] urls = {new File("plugins/" + this.plugin.getName() + "/filter").toURI().toURL()};
			System.out.println(4);
			for(URL blub: urls) {
				System.out.println(blub.toString());
			}
////			URLClassLoader loader1 = new URLClassLoader(new URL[] {(new URL(file.toString()))}, parentLoader);
////			URLClassLoader loader1 = new URLClassLoader(urls, parentLoader);
//			URLClassLoader loader1 = new URLClassLoader(urls);
//			
//			System.out.println(5);
//			System.out.println(loader1.findResource(file));
//			System.out.println(51);
//			
//			String args = file.substring(0, file.indexOf(".class"));
//			System.out.println("52 " + args);
//			
//			Class cls1 = loader1.loadClass(args);
//			
////			Class cls1 = loader1.loadClass(file);
//			
//			System.out.println(6);
//			
//			SpamFilter filter = (SpamFilter) cls1.newInstance();
//			
//			System.out.println(7);
			System.out.println(5);
			ClassLoader cl = new URLClassLoader(urls);
			System.out.println(6);
			Object o = Class.forName("de.bergtiger.tigerchat.spam.EqualsFilter", true, cl).newInstance();
			System.out.println(7);
			
		} catch (MalformedURLException e) {
			this.plugin.getLogger().info("could not load filterURL (" + file + ")");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			this.plugin.getLogger().info("could not find filterCLass (" + file + ")");
			e.printStackTrace();
		} catch (InstantiationException e) {
			this.plugin.getLogger().info("could not initiate filterCLass (" + file + ")");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			this.plugin.getLogger().info("what are you doing there! (" + file + ")");
			e.printStackTrace();
		}
	}
}
