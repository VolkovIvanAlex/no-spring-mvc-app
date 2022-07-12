package network;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import network.annotation.RequestMapping;
import network.annotation.RestController;
import network.dto.UserDto;
import network.executor.SQLExecutor;
import network.executor.implementation.SQLiteExecutor;
import network.filter.AuthJwtFilter;
import network.filter.EmptyHandler;
import network.filter.Filter404;
import network.filter.RequestMappingFilter;
import network.filter.SetDefaultHeadersFilter;
import network.service.UserService;
import network.service.implementation.DefaultUserService;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

public class Application {

    private HttpServer server;

    public Application() throws IOException {
        server = HttpServer.create();
    }

    public void initializeDatabase(String name) throws SQLException {
        String createGroupTableQuery = "CREATE TABLE IF NOT EXISTS `group` (\n" +
                "`group_id`	INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "`group_name`	TEXT NOT NULL UNIQUE,\n" +
                "`group_description`	TEXT)";

        String createGoodTableQuery = "CREATE TABLE IF NOT EXISTS `good` (\n" +
                "`good_id`	INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "`good_name`	TEXT NOT NULL UNIQUE,\n" +
                "`group_id`	INTEGER NOT NULL,\n" +
                "`good_description`	TEXT,\n" +
                "`manufacturer`	TEXT,\n" +
                "`number`	INTEGER NOT NULL,\n" +
                "`price`	NUMERIC NOT NULL,\n" +
                "FOREIGN KEY(`group_id`) REFERENCES `group`(`group_id`) ON UPDATE CASCADE ON DELETE CASCADE)";
        String createUserTableQuery = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                "`login`    TEXT PRIMARY KEY,\n" +
                "`password`	TEXT NOT NULL)";

        SQLiteExecutor.setDatabaseName(name);
        SQLExecutor executor = new SQLiteExecutor();
        executor.update(createGroupTableQuery);
        executor.update(createGoodTableQuery);
        executor.update(createUserTableQuery);

        UserService userService = new DefaultUserService();
        try {
            userService.createNewUser(new UserDto("root", "root"));
        } catch (Exception e) {
        }
    }

    public void createContextes() {
        Map<String, HttpContext> contextMap = new HashMap<>();
        List<String> allowWithoutAuth = Arrays.asList("/login");

        HttpContext rootContext = server.createContext("/", new EmptyHandler());
        rootContext.getFilters().add(new SetDefaultHeadersFilter());
        rootContext.getFilters().add(new AuthJwtFilter());
        rootContext.getFilters().add(new Filter404());
        contextMap.put("/", rootContext);

        Reflections reflections = new Reflections(Application.class.getPackageName());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RestController.class);
        for (Class<?> clazz : classes) {
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    String path = method.getAnnotation(RequestMapping.class).path();
                    if (path.contains("{")) {
                        path = path.substring(0, path.indexOf('{'));
                    }
                    if (!contextMap.containsKey(path)) {
                        HttpContext context = server.createContext(path,
                                new EmptyHandler());
                        context.getFilters().add(new SetDefaultHeadersFilter());
                        if (!allowWithoutAuth.contains(path)) {
                            context.getFilters().add(new AuthJwtFilter());
                        }
                        contextMap.put(path, context);
                    }
                    contextMap.get(path).getFilters().add(new RequestMappingFilter(method));
                }
            }
            for (HttpContext context : contextMap.values()) {
                context.getFilters().add(new Filter404());
            }
        }
    }

    public void start(final int port) throws IOException {
        server.bind(new InetSocketAddress(8080), 0);
        System.out.println("Start listening at port " + server.getAddress().getPort());
        server.start();
    }

    public static void main(String[] args) throws SQLException, IOException {
        Application application = new Application();
        application.initializeDatabase("data.db");
        application.createContextes();
        application.start(8080);
    }
}
