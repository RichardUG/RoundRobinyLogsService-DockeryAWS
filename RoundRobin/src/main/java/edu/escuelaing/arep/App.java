package edu.escuelaing.arep;
import edu.escuelaing.arep.ServerClient.HttpClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import static spark.Spark.*;
public class App {
    public static void main( String[] args ) throws IOException {
        port(getPort());
        staticFileLocation("/static");
        HttpClient httpClient = new HttpClient();
        get("/", (req, res) -> {
            res.redirect("index.html");
            return null;
        });
        get("/ConnectLogs", (req, res) -> {
            System.out.println(httpClient.getUrl());
            res.status(200);
            res.type("application/json");
            String response = httpClient.getMessage();
            httpClient.changeServer();
            return response;
        });
        post("/ConnectLogs", (req, res) -> {
            System.out.println((req.url().split("//")[1]).split(":")[0]);
            httpClient.setUrl("http://"+(req.url().split("//")[1]).split(":")[0]);
            System.out.println(httpClient.getUrl());
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
