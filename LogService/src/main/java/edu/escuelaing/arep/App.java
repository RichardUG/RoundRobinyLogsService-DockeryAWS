package edu.escuelaing.arep;

import com.google.gson.Gson;
import edu.escuelaing.arep.model.Message;
import edu.escuelaing.arep.persistence.DBConnection;

import static spark.Spark.*;

public class App {
    /**
     * Metodo principal encargado de recibir peticiones GET y POST.
     * @param args Argumentos que entran a la clase principal.
     */
    public static void main( String[] args ) {
        port(getPort());
        DBConnection connection = new DBConnection();

        get("/connect", (req, res) -> {
            res.status(200);
            res.type("application/json");
            return new Gson().toJson(connection.getMessages());
        });
        post("/connect", (req, res) -> {
            System.out.println(req+" "+res);
            Message a = new Message(req.body());
            connection.insertMessage(a);
            return null;
        });
    }
    /**
     * Metodo encargado de ejecutar el programa de manera local con un puerto predeterminado.
     * @return Puerto asignado por defecto para ejecutar el programa de forma local, que es 4567.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}
