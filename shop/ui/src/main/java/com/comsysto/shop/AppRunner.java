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
        if (args.length < 1) {
            System.out.println("AppRunner <httpport>");
            return;
        }

        System.setProperty("spring.profiles.active", "heroku");

        AppRunner runner = new AppRunner(Integer.valueOf(args[0]));
        runner.startServer();
    }

    public AppRunner(int port){
        Validate.notNull(port, "A Port is needed to start the server");

        server = new Server(port);
        WebAppContext context = new WebAppContext();
        context.setContextPath("/pizza");
        context.setResourceBase("src/main/webapp/");
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
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
}
