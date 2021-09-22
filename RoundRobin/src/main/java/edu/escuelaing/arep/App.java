package edu.escuelaing.arep;
import edu.escuelaing.arep.ServerClient.HttpClient;

import java.net.UnknownHostException;

import static spark.Spark.*;
public class App {
    public static void main( String[] args ) throws UnknownHostException {
        port(getPort());
        staticFileLocation("/static");
        HttpClient httpClient = new HttpClient();
        get("/", (req, res) -> {
            res.redirect("index.html");
            return null;
        });
        get("/ConnectLogs", (req, res) -> {
            res.status(200);
            res.type("application/json");
            String response = httpClient.getMessage();
            httpClient.changeServer();
            return response;
        });
        post("/ConnectLogs", (req, res) -> {
            httpClient.postMessage(req.body());
            httpClient.changeServer();
            return "";
        });
    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
