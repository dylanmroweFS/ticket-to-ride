package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Created by dylan on 9/13/2017.
 */
public class ParseIntegerHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = exchange.getRequestBody().toString();
        int result = StringProcessor.SINGLETON.parseInteger(requestBody);
        exchange.getResponseBody().write(mapper.writeValueAsBytes(result));
    }
}
