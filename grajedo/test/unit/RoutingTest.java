package unit;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import core.Routing;
import pages.MainPage;
import pages.Page;
import utilities.ClientHttp;
import utilities.FakePage;
import utilities.ServerResponse;
import utilities.WebUnitTest;

public class RoutingTest extends WebUnitTest {

	@Test
	public void routeToExistingPage() throws Exception {
		Routing routing = new Routing(new FakePage("hello"));
		
		MainPage page = (MainPage) routing.routeTo("hello");
		
		assertThat(page.route(), is(equalTo("hello")));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_OK)));
	}
	
	@Test
	public void routeToHomePage() throws Exception {
		Routing routing = new Routing(new FakePage(""));
		
		MainPage page = (MainPage) routing.routeTo("");
		
		assertThat(page.route(), is(equalTo("")));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_OK)));
	}
	
	@Test
	public void routeToCorrectPage() throws Exception {
		Routing routing = new Routing(new FakePage("right"), new FakePage("wrong"));
		
		MainPage page = (MainPage) routing.routeTo("right");
		
		assertThat(page.route(), is(equalTo("right")));
	}
	
	@Test
	public void notFoundPage() throws Exception {
		Routing routing = new Routing();
		
		Page page = routing.routeTo("unknown");
		
		assertThat(page.body(), containsString("404 Page Not Found"));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_NOT_FOUND)));
	}
	
	@Test
	public void internalErrorPage() throws Exception {
		Routing routing = new Routing(new FakePage("error").alwaysFail());
		
		Page page = routing.routeTo("error");
		
		assertThat(page.body(), containsString("500 Internal Server Error"));
		assertThat(page.body(), containsString("Always Fail Exception"));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)));
	}
	
	@Test
	public void staticResources() throws Exception {
		ServerResponse httpResponse = new ClientHttp().locally().get("assets/grajedo.js");
		
		assertThat(httpResponse.statusCode(), is(equalTo(HttpServletResponse.SC_OK)));
	}
}
