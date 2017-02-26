package pages;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import core.ContentTypes;

public class InternalErrorPage implements Page{

	private String body;

	public InternalErrorPage(Exception exception) {
		this.body = String.join("<br>", Arrays.asList("500 Internal Server Error", exception.getMessage()));
	}

	@Override
	public int status() {
		return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
	}

	@Override
	public String body() {
		return this.body;
	}

	@Override
	public String contentType() {
		return ContentTypes.Html;
	}
}
