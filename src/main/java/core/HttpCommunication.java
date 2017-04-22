package core;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

public class HttpCommunication implements Communication{

	private static final String UTF8 = StandardCharsets.UTF_8.toString();
	private String routeParameter;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public HttpCommunication(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	@Override
	public Communication withRouteParameter(String routeParameter) {
		this.routeParameter = routeParameter;
		return this;
	}

	@Override
	public String getRouteParameter() {
		return decode(this.routeParameter);
	}
	
	@Override
	public String getParameter(String name) {
		return decode(this.request.getParameter(name));
	}
	
	@Override
	public String getMultiPartParameter(String name) throws Exception {
		Part part = this.request.getPart(name);
		String param = IOUtils.toString(part.getInputStream(), UTF8);
		return decode(param);
	}
	
	@Override
	public void redirectTo(String route) throws Exception {
		this.response.sendRedirect(route);
	}

	private String decode(String parameter) {
		try {
			return URLDecoder.decode(parameter, UTF8);
		} 
		catch (Exception e) {
			Events.error("Unable to decode parameter", e);
			return parameter;
		}
	}
}
