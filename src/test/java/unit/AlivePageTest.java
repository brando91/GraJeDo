package unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import pages.AlivePage;
import utilities.FakeCommunication;

public class AlivePageTest {

	@Test
	public void body() throws Exception {
		assertThat(new AlivePage().process(new FakeCommunication()).content(), is(equalTo("Alive")));
	}
}
