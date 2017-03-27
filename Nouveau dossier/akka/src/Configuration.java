import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


public class Configuration {
	
	public Configuration(String configFile){
		Config conf = ConfigFactory.load(configFile);
		System.out.println(conf);
	}
}
