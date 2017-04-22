package pages;

import org.eclipse.jetty.http.HttpStatus;

import core.Communication;

public class NotFoundPage implements Page {
	
	@Override
	public ActionResult process(Communication communication){
		return new ActionResult("404 Page Not Found", HttpStatus.NOT_FOUND_404);
	}
}
