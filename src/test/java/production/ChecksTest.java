package production;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.google.gson.Gson;

import core.JsonChecks;
import utilities.ClientHttp;
import utilities.ServerResponse;

public class ChecksTest {

	@Test
	public void checks() throws Exception {
		ServerResponse httpResponse = new ClientHttp().production().get("checks");
		
		JsonChecks checks = new Gson().fromJson(httpResponse.body(), JsonChecks.class);
		
		assertThat(httpResponse.statusCode(), is(equalTo(HttpServletResponse.SC_OK)));
		assertThat(checks.LogsExists, is(equalTo(true)));
		assertThat(checks.AssetsExists, is(equalTo(true)));
	}
}
