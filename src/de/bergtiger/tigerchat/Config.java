package de.bergtiger.tigerchat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
}
