package gps.socket; /**
 * Created by Gabe on 05/07/2016.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class InitSocket implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public InitSocket() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        try {
        ServerSocket MyService;
            ServerSocket socket = new ServerSocket(8989);
                Socket serviceSocket = socket.accept();
                final BufferedReader is = new BufferedReader(new InputStreamReader(serviceSocket.getInputStream()));
                final PrintStream os = new PrintStream(serviceSocket.getOutputStream());
// As long as we receive data, echo that data back to the client.
                System.out.println("Waiting...");
                new Thread() {
                    @Override
                    public void run() {
            try {
                while (true) {
                    String line = is.readLine();
                    System.out.println(line);
                    //os.println(line);
                }
            }catch (IOException e) {
                                System.out.println(e);
                            }
                        }
                }.start();

        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
