package edu.escuelaing.arep.persistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.escuelaing.arep.model.Message;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;


public class DBConnection {
    MongoClientURI uri;
    MongoClient mongoClient;
    /**
     * Metodo constructor de la clase DBConnection.
     */
    public DBConnection() {
        uri = new MongoClientURI("mongodb+srv://richard:Rsug3101@cluster0.c5enc.mongodb.net/RoundRobinLogsServer?retryWrites=true&w=majority");
        mongoClient = new MongoClient(uri);
    }
    /**
     * Metodo encargado de insertar el mensaje en la base de datos.
     * @param message Parametro que indica los datos a insertar en la base de datos.
     */
    public void insertMessage(Message message){
        System.out.println(message);
        mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("RoundRobinLogsServer");
        MongoCollection<Document> collection =database.getCollection("Messages");
        Document document=new Document();
        document.put("message",message.getInfo());
        document.put("date",message.getDate());
        collection.insertOne(document);
    }
    /**
     * Metodo encargado de realizar una consulta a la base de datos y obtener los datos ingresados.
     * @return Retorna una lista que contiene los mensajes almacenados en la base de datos.
     */
    public ArrayList<Message> getMessages() {
        MongoDatabase database = mongoClient.getDatabase("RoundRobinLogsServer");
        MongoCollection<Document> collection = database.getCollection("Messages");
        FindIterable findIterable = collection.find();
        ArrayList<Document> documents = new ArrayList<Document>();
        ArrayList<Message> messages = new ArrayList<Message>();
        findIterable.into(documents);
        for (int i =documents.size()-1;i>=documents.size()-10;i--) {
            if (documents.get(i).get("message") != null && documents.get(i).get("date") != null) {
                messages.add(new Message((String) documents.get(i).get("message"), (Date) documents.get(i).get("date")));
            }
        }
        System.out.println(messages);
        return messages;
    }
}
