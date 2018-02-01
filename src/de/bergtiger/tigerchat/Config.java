package de.bergtiger.tigerchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.logging.log4j.core.util.Loader;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private TigerChat plugin;
	
	public Config(TigerChat plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * creates files
	 */
	public void create() {
		this.createConfig();
		this.createReadMe();
		this.createFilter();
	}
	
	/**
	 * checks if files are correct (readme version)
	 */
	public void check() {
		this.checkReadMe();
	}
	
	/**
	 * loads files (config)
	 */
	public void load() {
		this.loadConfig();
//		this.loadFilters();
	}
	
	/**
	 * creates plugin config
	 */
	private void createConfig() {
		FileConfiguration cfg = this.plugin.getConfig();
		
		cfg.options().copyDefaults(true);
		cfg.options().copyHeader(true);
		
		cfg.options().header(this.plugin.getName());
		
		this.plugin.saveConfig();
	}
	
	/**
	 * creates readme
	 */
	private void createReadMe() {
		File file = new File("plugins/" + this.plugin.getName() + "/README.txt");
		if(!file.exists()){
			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Tiger's Halloween - ReadMe"
					+ "\n"
					+ "\n" + "Version " + this.plugin.getDescription().getVersion()
					+ "\n"
					+ "\n" + "History:"
					+ "\n" + "\t- 1.0"
				);
				bw.close();
				fw.close();
			} catch (IOException e) {
				this.plugin.getLogger().info("could not save ReadME(" + file + ")");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * checks readme version
	 */
	private void checkReadMe() {
		File file = new File("plugins/" + this.plugin.getName() + "/README.txt");
		if(file.exists()){
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String line = null;
				while((line = br.readLine()) != null){
					if(line.toLowerCase().contains("version ")){
						if(!line.equalsIgnoreCase("version " + this.plugin.getDescription().getVersion())){
							br.close();
							fr.close();
							file.delete();
							this.createReadMe();
							return;
						}
						break;
					}
				}
				br.close();
				fr.close();
			} catch (IOException e) {
				this.plugin.getLogger().info("could not save or load ReadME(" + file + ")");
				e.printStackTrace();
			}
}
	}
	
	/**
	 * loads data from config
	 */
	private void loadConfig() {
		
	}
	
	private void createFilter() {
		File file = new File("plugins/" + this.plugin.getName() + "/filter");
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
//	private void loadFilters() {
//		File file = new File("plugins/" + this.plugin.getName() + "/filter");
//		if(file.exists()) {
//			if(file.isDirectory()) {
//				String[] files = file.list();
//				for(int i = 0; i < files.length; i++) {
//					this.loadFilter(files[i]);
//				}
//			}
//		}
//	}
//	
//	private void loadFilter(String file) {
//		System.out.println(1);
//		this.plugin.getLogger().info("loading filter " + file);
//		System.out.println(2);
////		ClassLoader parentLoader = this.getClass().getClassLoader();
//		System.out.println(3);
//		try {
//			URL[] urls = {new File("plugins/" + this.plugin.getName() + "/filter").toURI().toURL()};
//			System.out.println(4);
//			for(URL blub: urls) {
//				System.out.println(blub.toString());
//			}
////			URLClassLoader loader1 = new URLClassLoader(new URL[] {(new URL(file.toString()))}, parentLoader);
////			URLClassLoader loader1 = new URLClassLoader(urls, parentLoader);
////			URLClassLoader loader1 = new URLClassLoader(urls);
////			System.out.println(5);
////				System.out.println(loader1.findResource(file));
////			
////			System.out.println(51);
////			String args = file.substring(0, file.indexOf(".class"));
////			System.out.println("52 " + args);
////			Class cls1 = loader1.loadClass(args);
////			System.out.println(6);
////			SpamFilter filter = (SpamFilter) cls1.newInstance();
////			System.out.println(7);
//			System.out.println(5);
//			ClassLoader cl = new URLClassLoader(urls);
//			System.out.println(6);
//			Object o = Class.forName("de.bergtiger.tigerchat.spam", true, cl).newInstance();
//			System.out.println(7);
//			
//		} catch (MalformedURLException e) {
//			this.plugin.getLogger().info("could not load filterURL (" + file + ")");
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			this.plugin.getLogger().info("could not find filterCLass (" + file + ")");
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			this.plugin.getLogger().info("could not initiate filterCLass (" + file + ")");
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			this.plugin.getLogger().info("what are you doing there! (" + file + ")");
//			e.printStackTrace();
//		}
//	}
}
