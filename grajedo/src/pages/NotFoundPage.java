package pages;

import javax.servlet.http.HttpServletResponse;

import core.ContentTypes;

public class NotFoundPage implements Page {

	@Override
	public String body() {
		return "404 Page Not Found";
	}

	@Override
	public int status() {
		return HttpServletResponse.SC_NOT_FOUND;
	}
	
	@Override
	public String contentType() {
		return ContentTypes.Html;
	}
}
