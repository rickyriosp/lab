package com.riosr;

import com.riosr.web.MyBankServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();

        String serverPort = System.getProperty("server.port");
        if (serverPort != null && !serverPort.isBlank() && !serverPort.isEmpty()) {
            tomcat.setPort(Integer.parseInt(serverPort));
            System.out.println("Tomcat port is " + serverPort);
        } else {
            tomcat.setPort(8080);
            System.out.println("Tomcat port is 8080");
        }
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", null);
        Wrapper servlet = Tomcat.addServlet(ctx, "mybank", new MyBankServlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }
}
