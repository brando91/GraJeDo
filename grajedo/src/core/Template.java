package core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.rythmengine.Rythm;

public class Template {

	private static boolean initialized = false;
	private HashMap<String, Object> parameters;
	private String templateName;

	public Template(String templateName) throws IOException {
		initialize();
		this.templateName = templateName;
		this.parameters = new HashMap<String, Object>();
	}

	public Template add(String key, Object value) {
		this.parameters.put(key, value);
		return this;
	}

	public String render() {
		return Rythm.render(this.templateName, this.parameters);
	}
	
	public static void initialize() throws IOException {
		if(initialized) return;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("home.template.dir", new File("resources/views").getCanonicalPath());
        Rythm.init(map);
        initialized = true;
	}
}
