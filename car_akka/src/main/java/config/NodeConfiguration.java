package config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodeConfiguration {
	private String name;
	private String remoteURL;
	private List<String> successors;


	/**
	 * Construct the default instance, used when the config file
	 * not exist or it is invalid.
	 * @param init the default values
	 */
	NodeConfiguration(String n, String remote, List<String> s) {
		name = n;
		successors = new ArrayList<>(s);
		remoteURL = remote;
	}
	
	
	/**
	 * Gson need a no-args constructor to construct this object from the config file
	 * without having to use unsafe operations
	 */
	@SuppressWarnings("unused")
	private NodeConfiguration() {}
	
	public String getName() {
		return name;
	}
	
	public String getRemoteURL() {
		return remoteURL;
	}
	
	public List<String> getSuccessors() {
		return Collections.unmodifiableList(successors);
	}
	
}