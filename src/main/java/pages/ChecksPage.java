package pages;

import java.io.File;

import com.google.gson.Gson;

import core.Communication;
import core.JsonChecks;

public class ChecksPage implements ActionPage{

	@Override
	public PageResult process(Communication communication) {
		JsonChecks checks = new JsonChecks();
		checks.LogsExists = new File("logs").exists();
		checks.AssetsExists = new File("resources/assets").exists();
		
		return new JsonResult(new Gson().toJson(checks));
	}

	@Override
	public String route() {
		return "/checks";
	}
	
	

}
