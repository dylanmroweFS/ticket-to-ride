
import com.sun.net.httpserver.HttpServer;
import handlers.ParseIntegerHandler;
import handlers.ToLowerCaseHandler;
import handlers.TrimHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by dylan on 9/13/2017.
 */
public class ServerCommunicator {
    public static ServerCommunicator SINGLETON = new ServerCommunicator();

    private HttpServer server;

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private ServerCommunicator(){}

    public void run(String portNumber) {
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Indicate that we are using the default "executor".
        // This line is necessary, but its function is unimportant for our purposes.
        server.setExecutor(null);

        // Log message indicating that the server is creating and installing
        // its HTTP handlers.
        // The HttpServer class listens for incoming HTTP requests.  When one
        // is received, it looks at the URL path inside the HTTP request, and
        // forwards the request to the handler for that URL path.
        System.out.println("Creating contexts");


        server.createContext("/strings/toLowerCase", new ToLowerCaseHandler());
        server.createContext("/strings/trim", new TrimHandler());
        server.createContext("/strings/parseInteger", new ParseIntegerHandler());

        // Log message indicating that the HttpServer is about the start accepting
        // incoming client connections.
        System.out.println("Starting server");

        // Tells the HttpServer to start accepting incoming client connections.
        // This method call will return immediately, and the "main" method
        // for the program will also complete.
        // Even though the "main" method has completed, the program will continue
        // running because the HttpServer object we created is still running
        // in the background.
        server.start();

        // Log message indicating that the server has successfully started.
        System.out.println("Server started");
    }

    public static void main(String[] args) {
        String portNumber = args[0];
        ServerCommunicator.SINGLETON.run(portNumber); //intelliJ is configured to make it run on port 8080
        System.out.println("Running on port " + portNumber + ".");
    }
}
