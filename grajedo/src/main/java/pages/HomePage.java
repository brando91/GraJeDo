package pages;

import core.Template;

public class HomePage extends MainPage{

	private String body;

	@Override
	public String body() {
		return this.body;
	}

	@Override
	public String route() {
		return "/";
	}

	@Override
	public Page process() throws Exception {
		this.body = new Template("home")
							.add("who", "World")
							.render();
		return this;
	}

}
