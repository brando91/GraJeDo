package core;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import pages.ActionPage;
import pages.InternalErrorPage;
import pages.NotFoundPage;
import pages.PageResult;

public class Routing extends AbstractHandler{

	private List<ActionPage> pages;

	public Routing(ActionPage... page) {
		this.pages = Arrays.asList(page);
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpCommunication communication = new HttpCommunication(request, response);
		PageResult result = routeTo(target, communication);
		
		response.setContentType(result.contentType());
		response.setCharacterEncoding(result.encoding());
		response.setStatus(result.status());
		response.getWriter().write(result.content());
		
        baseRequest.setHandled(true);
        
        logRequest(target, baseRequest, result.status());
	}

	public PageResult routeTo(String route, Communication communication) {
		try{
			for (ActionPage page : pages) {
				Matcher matcher = Pattern.compile(page.route()).matcher(route);
				if(matcher.matches()){
					return process(page, matcher, communication);
				}
			}
			return new NotFoundPage().process(communication);
		}
		catch (Exception e) {
			Events.error("Error handling route " + route, e);
			return new InternalErrorPage(e).process(communication);
		}
	}

	private PageResult process(ActionPage page, Matcher matcher, Communication communication) throws Exception {
		if(matcher.groupCount() > 0){
			communication.withRouteParameter(matcher.group(1));
		}
		return page.process(communication);
	}
	
	private void logRequest(String target, Request baseRequest, int status) {
		Events.access(baseRequest.getRemoteAddr(), 
        			  baseRequest.getHeader("User-Agent"), 
        			  baseRequest.getMethod(), 
        			  target + queryString(baseRequest), 
        			  status);
	}
	
	private String queryString(Request base){
		return (base.getQueryString() == null) ? "" : "?" + base.getQueryString();
	}
}
