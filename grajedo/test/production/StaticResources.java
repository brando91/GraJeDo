package production;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import utilities.ClientHttp;
import utilities.ServerResponse;

public class StaticResources {

	@Test
	public void staticResources() throws Exception {
		ServerResponse httpResponse = new ClientHttp().production().get("assets/grajedo.js");
		
		assertThat(httpResponse.statusCode(), is(equalTo(HttpServletResponse.SC_OK)));
	}
}
