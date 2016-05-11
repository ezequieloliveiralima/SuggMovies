/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubevideos.ws.entities;

import java.io.Serializable;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
public class Id implements Serializable{
    
    private String videoId;

    public Id(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "Id{" + "videoId=" + videoId + '}';
    }
    
    
}
