package utilities;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import core.Communication;
import core.Events;

public class FakeCommunication implements Communication{

	private String routeParameter;
	private HashMap<String, String> parameters;
	private String redirectPage;
	private static String utf8 = StandardCharsets.UTF_8.toString();
	
	public FakeCommunication() {
		this.parameters = new HashMap<String, String>();
	}
	
	@Override
	public String getRouteParameter() {
		return decoded(this.routeParameter);
	}
	
	@Override
	public String getParameter(String name) {
		return decoded(this.parameters.get(name));
	}
	
	@Override
	public String getMultiPartParameter(String name) {
		return getParameter(name);
	}
	
	public FakeCommunication withRouteParameter(String routeParameter) {
		this.routeParameter = encoded(routeParameter);
		return this;
	}

	public FakeCommunication withParameter(String name, String value){
		this.parameters.put(name, encoded(value));
		return this;
	}
	
	private String encoded(String value) {
		try {
			return URLEncoder.encode(value, utf8);
		} catch (Exception e) {
			Events.error("Unable to encode " + value, e);
			return value;
		}
	}
	
	private String decoded(String parameter) {
		try {
			return URLDecoder.decode(parameter, StandardCharsets.UTF_8.toString());
		} 
		catch (Exception e) {
			Events.error("Unable to decode parameter", e);
			return parameter;
		}
	}

	@Override
	public void redirectTo(String route) throws Exception {	
		this.redirectPage = route;
	}

	public String redirectPage() {
		return this.redirectPage;
	}

}
