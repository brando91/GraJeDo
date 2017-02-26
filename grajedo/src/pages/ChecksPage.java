package pages;

import java.io.File;

import com.google.gson.Gson;

import core.JsonChecks;

public class ChecksPage extends JsonPage{

	private JsonChecks checks;

	@Override
	public String body() {
		return new Gson().toJson(this.checks);
	}

	@Override
	public ChecksPage process() {
		JsonChecks checks = new JsonChecks();
		checks.LogsExists = new File("logs").exists();
		checks.AssetsExists = new File("resources/assets").exists();
		this.checks = checks;
		
		return this;
	}

	@Override
	public String route() {
		return "/checks";
	}
	
	

}
