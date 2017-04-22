package pages;

import core.Communication;

public class AlivePage implements ActionPage
{
	@Override
	public String route() {
		return "/alive";
	}

	@Override
	public PageResult process(Communication communication) throws Exception {
		return new ActionResult("Alive");
	}
}
