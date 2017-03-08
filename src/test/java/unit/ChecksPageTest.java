package unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import core.JsonChecks;
import pages.ChecksPage;
import core.ContentTypes;

public class ChecksPageTest {

	@BeforeClass
	public static void setUp() throws IOException{
		FileUtils.forceMkdir(new File("logs/"));
	}
	
	@Test
	public void statusCode() throws Exception {
		assertThat(new ChecksPage().contentType(), is(equalTo(ContentTypes.Json)));
	}
	
	@Test
	public void body() throws Exception {
		ChecksPage checksPage = new ChecksPage().process();
		
		JsonChecks checks = new Gson().fromJson(checksPage.body(), JsonChecks.class);
		
		assertThat(checks.LogsExists, is(equalTo(true)));
		assertThat(checks.AssetsExists, is(equalTo(true)));
	}
}
