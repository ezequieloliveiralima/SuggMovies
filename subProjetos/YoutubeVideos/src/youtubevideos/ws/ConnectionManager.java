/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubevideos.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
public class ConnectionManager {
    public static String readContent(String uri) {
        String content = "";

        try {
            URL url = new URL(uri);
            // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
            // HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            
            rd.close();
            conn.disconnect();
            
            content = sb.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return content;
    }
}
