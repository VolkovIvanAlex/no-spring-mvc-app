package network.filter;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.List;

import network.dto.JwtTokenDto;
import network.exception.ResponseErrorException;
import network.service.UserService;
import network.service.implementation.DefaultUserService;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

public class AuthJwtFilter extends Filter {

    private static final String HEADER_NAME = "Authorization";

    private UserService userService = new DefaultUserService();

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        chain.doFilter(exchange);
        /* if (1 == 1) {
            return;
        } */
        try {
            List<String> authHeader = exchange.getRequestHeaders().get(HEADER_NAME);
            if (authHeader == null || authHeader.isEmpty()) {
                throw new ResponseErrorException(403, HEADER_NAME + " required");
            }
            String headerValue = authHeader.get(0);
            String[] values = headerValue.split("\\s+");
            if (values.length != 2) {
                throw new ResponseErrorException(403, "Invalid " + HEADER_NAME + " header value");
            }
            String schema = values[0];
            String token = values[1];
            if (!schema.equals("Bearer")) {
                throw new ResponseErrorException( 403, "Invalid " + HEADER_NAME + " schema");
            }
            boolean valid = userService.validateUserJwt(new JwtTokenDto(token));
            if (valid) {
                chain.doFilter(exchange);
            } else {
                throw new UnknownServiceException();
            }
        } catch (ResponseErrorException e) {
            ResponseSender.sendErrorMessage(exchange, e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            ResponseSender.sendErrorMessage(exchange, 500, "Internal server error");
        }
    }

    @Override
    public String description() {
        return "Check and validate jwt token";
    }

}
