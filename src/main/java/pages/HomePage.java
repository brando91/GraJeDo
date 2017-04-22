package pages;

import core.Communication;
import core.Template;

public class HomePage implements ActionPage{

	@Override
	public String route() {
		return "/";
	}

	@Override
	public PageResult process(Communication communication) throws Exception {
		String content = new Template("home")
								.add("who", "World")
								.render();
		return new ActionResult(content);
	}

}
