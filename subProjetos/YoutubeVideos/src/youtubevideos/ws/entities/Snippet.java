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
public class Snippet implements Serializable{
    
    private String title;
    private String description;

    public Snippet(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Snippet{" + "title=" + title + ", description=" + description + '}';
    }
    
    
}
