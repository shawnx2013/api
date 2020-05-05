package Models;

public class Artist {
    private String id;
    private String Name;
    private String AlbumCount;

    public Artist(){

    }

    public Artist(String id, String name, String albumCount){
        this.id = id;
        this.Name = name;
        this.AlbumCount = albumCount;
    }

    public String getName() {
        return Name;
    }

    public String getId() {
        return id;
    }

    public String getAlbumCount() {
        return AlbumCount;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAlbumCount(String albumCount) {
        this.AlbumCount = albumCount;
    }
}
