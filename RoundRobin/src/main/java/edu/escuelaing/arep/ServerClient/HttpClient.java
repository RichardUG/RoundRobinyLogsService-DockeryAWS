package edu.escuelaing.arep.ServerClient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Logger;

public class HttpClient{
    private String[] ports = {":35001",":35002",":35003"};
    private int nServer = 0;
    private String url="";

    public HttpClient( ) throws IOException {
        //url="http://"+start();
        System.out.println(url);
    }


    public static String getHostIp(){

        String realIp = null;

        try {
            InetAddress address = InetAddress.getLocalHost();

            // Si es la dirección de la tarjeta de red de bucle invertido, obtenga la dirección ipv4
            if (address.isLoopbackAddress()) {
                address = getInet4Address();
            }

            realIp = address.getHostAddress();

            return address.getHostAddress();
        } catch (Exception e) {
            System.out.println(e);
        }

        return realIp;
    }

    private static InetAddress getInet4Address() throws SocketException {
        // Obtén toda la información de la tarjeta de red
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) networkInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            System.out.println(addresses);
            while (addresses.hasMoreElements()) {
                InetAddress ip = (InetAddress) addresses.nextElement();
                System.out.println(ip);
                if (ip instanceof Inet4Address) {
                    return ip;
                }
            }
        }
        return null;
    }

    /**
     * Metodo encargado de realizar una peticion GET al LogService.
     * @return String Cadena que contiene la peticion GET realizado.
     * @throws UnirestException Excepcion que se arroja si no se encuentra ninguna peticion.
     */
    public String getMessage() throws UnirestException {
        System.out.println(url+ports[nServer]+"/connect");
        HttpResponse<String> apiResponse = Unirest.get(url+ports[nServer]+"/connect").asString();
        return apiResponse.getBody();
    }
    /**
     * Metodo encargado de realizar una peticion POST al LogService.
     * @param message Mensaje ingresado por el usuario que es asimismo ingresado a la base de datos.
     * @return String Cadena que contiene la peticion POST que se realizo.
     * @throws UnirestException Excepcion que se arroja si no se encuentra ningun mensaje.
     */
    public String postMessage(String message) throws UnirestException {
        System.out.println(url+ports[nServer]+"/connect");
        HttpResponse<String> apiResponse = Unirest.post(url+ports[nServer]+"/connect")
                .body(message)
                .asString();
        return apiResponse.getBody();
    }
    /**
     * Metodo encargado de cambiar el valor de la variable nServer para cambiar entre los tres puertos disponibles del LogService.
     */
    public void changeServer(){
        nServer=(nServer + 1) % ports.length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
