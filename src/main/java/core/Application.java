package core;

import java.io.File;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

import pages.AlivePage;
import pages.ChecksPage;
import pages.HomePage;

public class Application {

	private Server server;

	public Application productionStart() throws Exception {
		Template.initializeForProduction();
		startServer(80);
		return this;
	}
	
	public Application startLocally() throws Exception {
		Template.initializeForDevelopment();
		startServer(8080);
		return this;
	}

	public void stop() throws Exception {
		this.server.stop();
	}
	
	private void startServer(int port) throws Exception {
		Server server = new Server(port);
		server.setHandler(handlers());
        this.server = server;
        this.server.start();
	}
	
	private HandlerList handlers() throws Exception {
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[]{
				staticResources(),
				new Routing(new HomePage(), new AlivePage(), new ChecksPage())
		});
		return handlers;
	}
	
	private Handler staticResources() throws Exception {
		ResourceHandler resources = new ResourceHandler();
		resources.setDirectoriesListed(false);
		resources.setResourceBase(new File("resources/assets/").getCanonicalPath());
		ContextHandler contextHandler = new ContextHandler("/assets");
		contextHandler.setHandler(resources);
		return contextHandler;
	}
}
