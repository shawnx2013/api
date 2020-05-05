package Models;

import java.util.List;

public class Album {

    private String id;
    private String Title;
    private List<String> Tracks = null;

    public Album(){};

    public Album(String id, String name, List<String> tracks){
        this.id = id;
        this.Title = name;
        this.Tracks = tracks;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public List<String> getTracks() {
        return Tracks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String name) {
        this.Title = name;
    }

    public void setTracks(List<String> tracks){
        this.Tracks = tracks;
    }
}
