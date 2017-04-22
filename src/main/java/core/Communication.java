package core;

public interface Communication {

	Communication withRouteParameter(String group);
	
	String getRouteParameter();

	String getParameter(String name);

	String getMultiPartParameter(String name) throws Exception;

	void redirectTo(String route) throws Exception;

}
