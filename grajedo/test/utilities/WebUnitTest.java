package utilities;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import core.Application;

public class WebUnitTest {

	private static Application application;
	
	@BeforeClass
	public static void startServer() throws Exception{
		application = new Application(8080);
		application.start();
	}

	@AfterClass
	public static void stopServer() throws Exception{
		application.stop();
	}
}
