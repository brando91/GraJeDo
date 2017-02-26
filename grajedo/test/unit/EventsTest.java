package unit;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import core.Events;

public class EventsTest {
	static File logsDirectory = new File("logs/");
	static File errorLogs = new File("logs/grajedo.log");
	static File accessLogs = new File("logs/access.log");
	
	@BeforeClass
	public static void setUp() throws IOException{
		FileUtils.forceMkdir(logsDirectory);
		FileUtils.deleteQuietly(logsDirectory);
	}

	@Test
	public void logErrors() throws Exception {
		Events.error("Some error log", new Exception());
		
		String logContent = FileUtils.readFileToString(errorLogs, "UTF-8");
		
		assertThat(logContent, containsString("Some error log"));
		assertThat(logContent, containsString("Exception"));
	}
	
	@Test
	public void logInfos() throws Exception {
		Events.info("Some info");
		
		String logContent = FileUtils.readFileToString(errorLogs, "UTF-8");
		
		assertThat(logContent, containsString("Some info"));
	}
	
	@Test
	public void dontLogDebug() throws Exception {
		Events.debug("Some debug");
		
		String logContent = FileUtils.readFileToString(errorLogs, "UTF-8");
		
		assertThat(logContent, not(containsString("Some debug")));
	}
	
	@Test
	public void logAccesses() throws Exception {
		Events.access("192.0.0.1", "User-Agent", "GET", "/alive", 200);
		
		String logContent = FileUtils.readFileToString(accessLogs, "UTF-8");
		
		assertThat(logContent, containsString("192.0.0.1|User-Agent|GET|/alive|200"));
	}
}
