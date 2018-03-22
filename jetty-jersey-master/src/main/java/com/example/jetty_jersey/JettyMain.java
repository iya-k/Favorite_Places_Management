package com.example.jetty_jersey;

import com.irif.projet.genielogiciel.jetty_jersey.DAO.DAOFactory;

public class JettyMain {

	public static void main(String[] args) throws Exception {
		/*
		// Initialize the server
		Server server = new Server();

		// Add a connector
		ServerConnector connector = new ServerConnector(server);
		connector.setHost("localhost");
		connector.setPort(8080);
		connector.setIdleTimeout(30000);
		server.addConnector(connector);

		// Configure Jersey
		ResourceConfig rc = new ResourceConfig();
		rc.packages(true, "com.example.jetty_jersey.ws");
		rc.register(JacksonFeature.class);
		rc.register(LoggingFilter.class);

		// Add a servlet handler for web services (/ws/*)
		ServletHolder servletHolder = new ServletHolder(new ServletContainer(rc));
		ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handlerWebServices.setContextPath("/ws");
		handlerWebServices.addServlet(servletHolder, "/*");

		String[] pages = {
				"home.html",
				"pages/login.html",
				"pages/register.html",
				"pages/show_map.html",
				"pages/index_map.html"
		};

		String[] paths = {
				"/",
				"/login",
				"/register",
				"/map_show",
				"/map_index"
		};

		// Add a handler for resources (/*)
		ArrayList<ResourceHandler> handlerPortals = new ArrayList<ResourceHandler>();
		ArrayList<ContextHandler> handlerPortalCtxs = new ArrayList<ContextHandler>();

		for(int i=0; i<pages.length; i++){
			ResourceHandler handlerPortal = new ResourceHandler();
			handlerPortal.setResourceBase("src/main/webapp");
			handlerPortal.setDirectoriesListed(false);
			handlerPortal.setWelcomeFiles(new String[] { pages[i] });
			ContextHandler handlerPortalCtx = new ContextHandler();
			handlerPortalCtx.setContextPath(paths[i]);
			handlerPortalCtx.setHandler(handlerPortal);
 
			handlerPortals.add(handlerPortal);
			handlerPortalCtxs.add(handlerPortalCtx);
		}

		// Activate handlers
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.addHandler(handlerWebServices);
		for(ContextHandler contextHandler : handlerPortalCtxs){
			contexts.addHandler(contextHandler);
		}
		server.setHandler(contexts);

		// Start server
		server.start();*/
		DAOFactory daf = new DAOFactory();
		daf.initClient();
	}
}
