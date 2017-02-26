package core;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Events {

	static{
		PropertyConfigurator.configureAndWatch("resources/log4j.properties");
	}
	
	public static void error(String message, Exception exception) {
		mainLogger().error(message, exception);
	}
	
	public static void info(String message) {
		mainLogger().info(message);
	}
	
	public static void debug(String message) {
		mainLogger().debug(message);
	}
	
	public static void access(String ip, String userAgent, String method, String path, int status) {
		accessLogger().info(String.join("|", Arrays.asList(ip, userAgent, method, path, status + "")));
	}
	
	private static Logger mainLogger() {
		return Logger.getLogger("mainLog");
	}
	
	private static Logger accessLogger() {
		return Logger.getLogger("accessLog");
	}
}
