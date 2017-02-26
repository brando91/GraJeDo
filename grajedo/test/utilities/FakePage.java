package utilities;

import javax.servlet.http.HttpServletResponse;

import pages.MainPage;

public class FakePage extends MainPage{

	private String route;
	private String body;
	private boolean alwaysFail;

	public FakePage(String route) {
		this.route = route;
		this.alwaysFail = false;
	}

	@Override
	public String route() {
		return this.route;
	}

	@Override
	public FakePage process() throws Exception {
		this.body = "Fake Page";
		if(this.alwaysFail){
			throw new Exception("Always Fail Exception");
		}
		return this;
	}
	
	@Override
	public String body() {
		return this.body;
	}

	public MainPage alwaysFail() {
		this.alwaysFail = true;
		this.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		return this;
	}
}
