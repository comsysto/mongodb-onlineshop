package com.comsysto.shop;

import org.apache.commons.lang.Validate;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: christian.kroemer@comsysto.com
 * Date: 26/11/13
 * Time: 09:16
 */
public class AppRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

    private final Server server;

    public static void main(final String[] args) {

        System.setProperty("spring.profiles.active", "default");

        AppRunner runner = new AppRunner(8080);
        runner.startServer();
    }

    public AppRunner(int port){
        Validate.notNull(port, "A Port is needed to start the server");

        server = new Server(port);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/pizza");
        context.setResourceBase("src/main/webapp/");
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
    }

    public void startServer(){
        try {
            LOGGER.info("JETTY SERVER STARTING NOW ...");
            server.start();
            server.join();
        } catch (Exception e) {
            LOGGER.error("Jetty Server could not be started", e);
            System.exit(100);
        }
    }

    public void stopServer(){
        server.destroy();
    }

    public boolean isServerStarted(){
        return server.isStarted();
    }

    public boolean isServerFailed(){
        return server.isFailed();
    }
}
