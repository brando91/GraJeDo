package pages;

import javax.servlet.http.HttpServletResponse;

import core.ContentTypes;

public abstract class MainPage implements Page {

	protected int status = HttpServletResponse.SC_OK;
	protected String contentType = ContentTypes.Html;

	@Override
	public int status(){
		return this.status;
	}
	
	@Override
	public String contentType(){
		return this.contentType;
	}
	
	public abstract String route();

	public abstract Page process() throws Exception;
}
