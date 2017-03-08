package pages;

public class AlivePage extends MainPage
{
	private String body;

	@Override
	public String route() {
		return "/alive";
	}

	@Override
	public String body() {
		return this.body;
	}

	@Override
	public Page process() throws Exception {
		this.body = "Alive";
		return this;
	}
}
