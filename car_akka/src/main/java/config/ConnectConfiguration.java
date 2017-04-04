package config;

/**
 * Configutation de la connexion réseau d'un système donné
 */
public class ConnectConfiguration {
	
	private String host;
	private int port;

	/**
	 * Construct the default instance, used when the config file
	 * not exist or it is invalid.
	 * @param h the default host value
	 * @param p the default port value
	 */
	public ConnectConfiguration(String h, int p) {
		host = h;
		port = p;
	}

	/**
	 * Gson need a no-args constructor to construct this object from the config file
	 * without having to use unsafe operations
	 */
	@SuppressWarnings("unused")
	private ConnectConfiguration() {}
	
	
	/**
	 * L'hôte d'écoute du système
	 * @return l'hôte
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Le port d'écoute du système
	 * @return le port
	 */
	public int getPort() {
		return port;
	}
	
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + host + ":" + port + "]";
	}
	
}