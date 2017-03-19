package main.java.rest.car.config;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.rest.car.Starter;


/**
 * Classe permettant de g�rer la configuration global de notre serveur. 
 * Utilisation du pattern singleton.
 * 
 */
public class Configuration {
	
	public static final String CONFIG_FILE = "config.json";
	
	
	
	private static GlobalConfiguration config;
	
	
	/** 
	 * Permet de r�cup�rer une configuration globale � partir d'un fichier JSON.
	 * @return La r�f�rence vers une configuration globale.
	 */
	public static synchronized GlobalConfiguration getConfig() {
		if (config == null) {
			// load from file
			try (FileReader reader = new FileReader(CONFIG_FILE)) {
				config = new Gson().fromJson(reader, GlobalConfiguration.class);
			} catch (Exception e) {
				Starter.error("Config file not found or not valid. Generating default configuration... ");
				config = defaultConfig();
				
				try (FileWriter writer = new FileWriter(CONFIG_FILE, false)) {
					new GsonBuilder().setPrettyPrinting().create().toJson(config, writer);
					
				} catch (Exception e1) {
					Starter.error("Error while saving the config file: " + e1);
				}
			}
			
			
		}
		return config;
	}
	
	
	
	/**
	 * Permet d'avoir une configuration globale par d�fault
	 * @return La r�f�rence vers cette configuration par d�fault
	 */
	private static GlobalConfiguration defaultConfig() {
		return new GlobalConfiguration(8080, "localhost", 2121, true);
	}
	
	
	
	private Configuration() {}
	
}