/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author rene
 */
public class HttpClient {

    public static void main(String[] args) throws IOException {
        String API_KEY = "c2c1f7a67098b72c11454da6e8633a91";
        String LOCATION = "Brno";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=metric";
        String hostname = "api.openweathermap.org";
        int port = 80;
        try {
            Socket socket = new Socket(hostname, port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String command = "GET " + urlString + " HTTP/1.1\n" + "Host: " + hostname + "\n\n";
            out.write(command);
            out.flush();
            String line, lineold = "";
          
            while ((line = in.readLine()) != null) {
                //System.out.println(line);
                //System.out.println(line);
                lineold = line;
            }
            out.close();
            in.close();
            String json = lineold;
            JSONObject obj = new JSONObject(json);
            String name = obj.getString("name");
            System.out.println(name);
            JSONArray arr = obj.getJSONArray("weather");
            for (int i = 0; i < arr.length(); i++) {
                String description = arr.getJSONObject(i).getString("description");
                System.out.println(description);
            }
            double temp = obj.getJSONObject("main").getDouble("temp");
            System.out.println("temp:" + temp + " °C ");
            double humidity = obj.getJSONObject("main").getDouble("humidity");
            System.out.println("humidity:" + humidity + " % ");
            double pressure = obj.getJSONObject("main").getDouble("pressure");
            System.out.println("pressure:" + pressure + " hPa ");
            double speed = obj.getJSONObject("wind").getDouble("speed");
            System.out.println("wind-speed:" + speed + " km/h ");
            double deg = obj.getJSONObject("wind").getDouble("deg");
            System.out.println("wind-deg " + deg);
            if (speed == 0) {
                System.out.println("wind-deg:" + deg + " - ");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
