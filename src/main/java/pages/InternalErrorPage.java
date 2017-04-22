package pages;

import java.util.Arrays;

import org.eclipse.jetty.http.HttpStatus;

import core.Communication;

public class InternalErrorPage implements Page{

	private String body;

	public InternalErrorPage(Exception exception) {
		this.body = String.join("<br>", Arrays.asList("500 Internal Server Error", exception.getMessage()));
	}

	@Override
	public ActionResult process(Communication communication){
		return new ActionResult(this.body, HttpStatus.INTERNAL_SERVER_ERROR_500);
	}
}
