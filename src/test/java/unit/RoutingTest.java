package unit;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import core.Routing;
import pages.PageResult;
import utilities.FakeCommunication;
import utilities.FakePage;

public class RoutingTest {

	@Test
	public void routeToExistingPage() throws Exception {
		Routing routing = new Routing(new FakePage("/hello"));
		
		PageResult page = routing.routeTo("/hello", new FakeCommunication());
		
		assertThat(page.content(), is(equalTo("Fake Page")));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_OK)));
	}
	
	@Test
	public void routeToHomePage() throws Exception {
		Routing routing = new Routing(new FakePage("/"));
		
		PageResult page = routing.routeTo("/", new FakeCommunication());
		
		assertThat(page.content(), is(equalTo("Fake Page")));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_OK)));
	}
	
	@Test
	public void routeToCorrectPage() throws Exception {
		Routing routing = new Routing(new FakePage("/right").withContent("Right"), 
									  new FakePage("/wrong").withContent("Wrong"));
		
		PageResult page = routing.routeTo("/right", new FakeCommunication());
		
		assertThat(page.content(), is(equalTo("Right")));
	}
	
	@Test
	public void notFoundPage() throws Exception {
		Routing routing = new Routing();
		
		PageResult page = routing.routeTo("/unknown", new FakeCommunication());
		
		assertThat(page.content(), containsString("404 Page Not Found"));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_NOT_FOUND)));
	}
	
	@Test
	public void internalErrorPage() throws Exception {
		Routing routing = new Routing(new FakePage("/error").alwaysFail());
		
		PageResult page = routing.routeTo("/error", new FakeCommunication());
		
		assertThat(page.content(), containsString("500 Internal Server Error"));
		assertThat(page.content(), containsString("Always Fail Exception"));
		assertThat(page.status(), is(equalTo(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)));
	}
	
	@Test
	public void routeStartingWithSamePrefix() throws Exception {
		Routing routing = new Routing(new FakePage("/route").withContent("Prefix"),
									  new FakePage("/route/submit").withContent("Submit"));
		
		PageResult page = routing.routeTo("/route/submit", new FakeCommunication());
		
		assertThat(page.content(), is(equalTo("Submit")));
	}
	
	@Test
	public void routeWithParameter() throws Exception {
		Routing routing = new Routing(new FakePage("/route").withContent("No parameter"),
									  new FakePage("/route/(.*)").withContent("With parameter"));
		
		FakeCommunication communication = new FakeCommunication();
		PageResult page = routing.routeTo("/route/parameter", communication);
		
		assertThat(page.content(), is(equalTo("With parameter")));
		assertThat(communication.getRouteParameter(), is(equalTo("parameter")));
	}
}
