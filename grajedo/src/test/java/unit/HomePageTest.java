package unit;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import pages.HomePage;

public class HomePageTest{

	@Test
	public void body() throws Exception {
		assertThat(new HomePage().process().body(), containsString("Home Page"));
	}
}
