/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package youtubevideos.ws.entities;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
public class Video {
    
    private Id id;
    private Snippet snippet;

    public Video(Id id, Snippet snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "Video{" + "id=" + id + ", snippet=" + snippet + '}';
    }
    
    
}
