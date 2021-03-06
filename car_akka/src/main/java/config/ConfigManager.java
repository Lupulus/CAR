package config;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Classe permettant de gérer la configuration globale de notre serveur. 
 * Utilisation du pattern singleton.
 * 
 */
public class ConfigManager {
	
	public static final String CONFIG_FILE = "systems.json";
	
	private static Configuration config;
	
	
	/** 
	 * Permet de récupérer une configuration globale à partir d'un fichier JSON.
	 * @return La référence vers une configuration globale.
	 */
	public static synchronized Configuration getConfig() {
		if (config == null) {
			// load from file
			try (FileReader reader = new FileReader(CONFIG_FILE)) {
				config = new Gson().fromJson(reader, Configuration.class);
			} catch (Exception e) {
				//Log.error("Config file not found or not valid. Generating default configuration... ");
				//config = defaultConfig();
				
				try (FileWriter writer = new FileWriter(CONFIG_FILE, false)) {
					new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(config, writer);
					
				} catch (Exception e1) {
					//Log.error("Error while saving the config file: " + e1);
				}
			}
		}
		return config;
	}
	
	/**
	 * Permet d'avoir une configuration globale par défault
	 * @return La référence vers cette configuration par défault
	 */
	/** private static Configuration defaultConfig() {
		Map<String, SystemConfiguration> systems = new HashMap<>();

		systems.put("tree_single", new SystemConfiguration(Arrays.asList(
				new NodeConfiguration("act1", null, Arrays.asList("act2", "act5")),
				new NodeConfiguration("act2", null, Arrays.asList("act3", "act4")),
				new NodeConfiguration("act3", null, Arrays.asList()),
				new NodeConfiguration("act4", null, Arrays.asList()),
				new NodeConfiguration("act5", null, Arrays.asList("act6")),
				new NodeConfiguration("act6", null, Arrays.asList())
				),
				null));
		
		systems.put("tree_remote_1", new SystemConfiguration(Arrays.asList(
				new NodeConfiguration("act1", null, Arrays.asList("act2", "act5")),
				new NodeConfiguration("act2", "akka.tcp://tree_remote_2@localhost:5000/user/act2", Arrays.asList()),
				new NodeConfiguration("act5", null, Arrays.asList("act6")),
				new NodeConfiguration("act6", null, Arrays.asList())
				),
				"akka_tree_remote_1.conf"));
		
		systems.put("tree_remote_2", new SystemConfiguration(Arrays.asList(
				new NodeConfiguration("act2", null, Arrays.asList("act3", "act4")),
				new NodeConfiguration("act3", null, Arrays.asList()),
				new NodeConfiguration("act4", null, Arrays.asList())
				),
				"akka_tree_remote_2.conf"));

		systems.put("loop_graph", new SystemConfiguration(Arrays.asList(
				new NodeConfiguration("act1", null, Arrays.asList("act2", "act5", "act4")),
				new NodeConfiguration("act2", null, Arrays.asList("act3", "act4")),
				new NodeConfiguration("act3", null, Arrays.asList()),
				new NodeConfiguration("act4", null, Arrays.asList("act4", "act5")),
				new NodeConfiguration("act5", null, Arrays.asList("act6")),
				new NodeConfiguration("act6", null, Arrays.asList("act1", "act3"))
				),
				null));
		
		return new Configuration(systems);
	} **/
	
	
	
	public static String generateAkkaConnectConfig(String host, int port) {
		return "akka {\n"
				+ "  actor {\n"
				+ "    provider = akka.remote.RemoteActorRefProvider\n"
				+ "  }\n"
				+ "  remote {\n"
				+ "    enabled-transports = [\"akka.remote.netty.tcp\"]\n"
				+ "    netty.tcp {\n"
				+ "      hostname = \"" + host + "\"\n"
				+ "      port = " + port + "\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n";
}
	
	private ConfigManager() {}
}