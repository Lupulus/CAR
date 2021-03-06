package config;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	private Map<String, SystemConfiguration> systems;
	


	/**
	 * Construct the default instance, used when the config file
	 * not exist or it is invalid.
	 * @param init the default values
	 */
	public Configuration(Map<String, SystemConfiguration> conf) {
		systems = new HashMap<>(conf);
	}
	
	
	/**
	 * Gson need a no-args constructor to construct this object from the config file
	 * without having to use unsafe operations
	 */
	@SuppressWarnings("unused")
	private Configuration() {}
	
	
	
	
	
	public Map<String, SystemConfiguration> getSystems() {
		return Collections.unmodifiableMap(systems);
	}
}
