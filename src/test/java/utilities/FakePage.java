package utilities;

import core.Communication;
import pages.ActionPage;
import pages.ActionResult;

public class FakePage implements ActionPage{

	private String route;
	private boolean alwaysFail;
	private Communication communication;
	private String content;

	public FakePage(String route) {
		this.route = route;
		this.alwaysFail = false;
		this.content = "Fake Page";
	}
	
	@Override
	public String route() {
		return this.route;
	}

	@Override
	public ActionResult process(Communication communication) throws Exception {
		this.communication = communication;
		if(this.alwaysFail){
			throw new Exception("Always Fail Exception");
		}
		return new ActionResult(this.content);
	}

	public ActionPage alwaysFail() {
		this.alwaysFail = true;
		return this;
	}

	public String parameter() {
		return this.communication.getRouteParameter();
	}

	public FakePage withContent(String content) {
		this.content = content;
		return this;
	}
}
