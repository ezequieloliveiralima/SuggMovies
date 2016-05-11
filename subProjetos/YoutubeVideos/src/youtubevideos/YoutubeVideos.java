/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubevideos;

import java.util.List;
import youtubevideos.ws.ConnectionManager;
import youtubevideos.ws.VideoParser;
import youtubevideos.ws.entities.Video;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
public class YoutubeVideos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String content = ConnectionManager.readContent("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q=donnie+darko+trailer&regionCode=BR&key=AIzaSyBEquCWFhL4znvO2MrYtmxlr9UAOqmAf-I");
        List<Video> videos = VideoParser.parseFeed(content);
        
        for (Video video : videos) {
            System.out.println(video.toString());
        }
    }
    
}
