package unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import pages.AlivePage;

public class AlivePageTest {

	@Test
	public void body() throws Exception {
		assertThat(new AlivePage().process().body(), is(equalTo("Alive")));
	}
}
