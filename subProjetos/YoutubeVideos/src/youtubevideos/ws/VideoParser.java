/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubevideos.ws;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import youtubevideos.ws.entities.Video;
import javax.json.JsonReader;
import javax.json.JsonValue;
import youtubevideos.ws.entities.Id;
import youtubevideos.ws.entities.Snippet;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
public class VideoParser {
    
    public static List<Video> parseFeed(String q)
    {
        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q="+q+"+trailer&regionCode=BR&key=AIzaSyBEquCWFhL4znvO2MrYtmxlr9UAOqmAf-I";
        System.out.println(url);
        String content = ConnectionManager.readContent(url);
        List<Video> listaVideos = new ArrayList();
        
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject raiz = reader.readObject();
        reader.close();
        
        JsonArray videos = raiz.getJsonArray("items");
        
        for (int i = 0; i < videos.size(); i++) {
            JsonObject videoObject = videos.getJsonObject(i);
            JsonObject idObject = videoObject.getJsonObject("id");
            String videoId = idObject.getString("videoId");
            
            JsonObject snippetObject = videoObject.getJsonObject("snippet");
            String title = snippetObject.getString("title");
            String description = snippetObject.getString("description");
            
            Id id = new Id(videoId);
            
            Snippet snippet = new Snippet(title, description);
            
            Video video = new Video(id, snippet);
            listaVideos.add(video);
        }
        return listaVideos;
    }
}
