package core;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import pages.InternalErrorPage;
import pages.MainPage;
import pages.NotFoundPage;
import pages.Page;

public class Routing extends AbstractHandler{

	private List<MainPage> pages;

	public Routing(MainPage... page) {
		this.pages = Arrays.asList(page);
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Page page = routeTo(target);
		
		response.setContentType(page.contentType());
		response.setCharacterEncoding("UTF-8");
		response.setStatus(page.status());
		response.getWriter().write(page.body());
		
        baseRequest.setHandled(true);
        
        logRequest(target, baseRequest, page.status());
	}

	public Page routeTo(String route) {
		try{
			Optional<MainPage> matchingPage = this.pages.stream()
														.filter(page -> page.route().equals(route))
														.findFirst();
			return matchingPage.isPresent() ?
						matchingPage.get().process() :
						new NotFoundPage();
		}
		catch (Exception e) {
			Events.error("Error handling route " + route, e);
			return new InternalErrorPage(e);
		}
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
