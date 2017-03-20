package main.java.rest.car.config;

public class GlobalConfiguration {

	private int httpPort;
	private String ftpHost;
	private int ftpPort;
	private boolean debugMode;
	
	
	
	
	/**
	 * Construct the default instance of GlobalConfiguration, used when the config file
	 * not exist or it is invalid.
	 * @param httpP the HTTP port
	 * @param ftpH the FTP server host
	 * @param ftpP the FTP server port
	 */
	/* package */ GlobalConfiguration(int httpP, String ftpH, int ftpP, boolean d) {
		httpPort = httpP;
		ftpHost = ftpH;
		ftpPort = ftpP;
		debugMode = d;
	}
	
	/**
	 * Gson need a no-args constructor to construct this object from the config file
	 * without having to use unsafe operations
	 */
	@SuppressWarnings("unused")
	private GlobalConfiguration() {}
	
	
	
	
	
	public int getHTTPPort() {
		return httpPort;
	}
	
	public String getFTPHost() {
		return ftpHost;
	}
	
	public int getFTPPort() {
		return ftpPort;
	}
	
	public boolean isDebugMode() {
		return debugMode;
	}
	
	
}
