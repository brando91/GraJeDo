package unit;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import pages.HomePage;
import utilities.FakeCommunication;

public class HomePageTest{

	@Test
	public void body() throws Exception {
		assertThat(new HomePage().process(new FakeCommunication()).content(), containsString("Home Page"));
	}
}
