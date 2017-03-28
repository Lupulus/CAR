package config;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemConfiguration {
	
	private String akkaConfigFile;

	private List<NodeConfiguration> nodes;
	
	/**
	 * Construct the default instance, used when the config file
	 * not exist or it is invalid.
	 * @param init the default values
	 */
	SystemConfiguration(List<NodeConfiguration> init, String akkaConfFile) {
		nodes = new ArrayList<>(init);
		akkaConfigFile = akkaConfFile;
	}
	
	/**
	 * Gson need a no-args constructor to construct this object from the config file
	 * without having to use unsafe operations
	 */
	@SuppressWarnings("unused")
	private SystemConfiguration() {}
	
	
	
	public List<NodeConfiguration> getNodes() {
		return Collections.unmodifiableList(nodes);
	}
	
	public String getAkkaConfigFile() {
		return akkaConfigFile;
	}
	
	
}