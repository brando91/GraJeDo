package production;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import utilities.ClientHttp;
import utilities.ServerResponse;

public class AliveTest {

	@Test
	public void alive() throws Exception {
		ServerResponse httpResponse = new ClientHttp().production().get("alive");
		
		assertThat(httpResponse.statusCode(), is(equalTo(HttpServletResponse.SC_OK)));
		assertThat(httpResponse.body(), containsString("Alive"));
	}
}
