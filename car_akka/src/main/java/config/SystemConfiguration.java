package config;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SystemConfiguration {
	
	private ConnectConfiguration akkaConnectConfig;

	private List<NodeConfiguration> nodes;
	
	/**
	 * Construct the default instance, used when the config file
	 * not exist or it is invalid.
	 * @param init the default values
	 */
	SystemConfiguration(List<NodeConfiguration> init, ConnectConfiguration akkaConnectConf) {
		nodes = new ArrayList<>(init);
		akkaConnectConfig = akkaConnectConf;
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
	
	/**
	 * Retourne la configuration de la connextivité de ce système
	 * @return La configuration de connexion, ou null si il n'y a pas
	 * 		de connexion à configurer.
	 */
	public ConnectConfiguration getAkkaConnectConfig() {
		return akkaConnectConfig;
}
	
	
}