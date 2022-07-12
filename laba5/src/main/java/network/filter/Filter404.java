package network.filter;

import java.io.IOException;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

public class Filter404 extends Filter {
    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        try {
            ResponseSender.sendErrorMessage(exchange, 404, "Path not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String description() {
        return "Send 404 error to client";
    }
}
