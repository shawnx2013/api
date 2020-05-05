package Services;

import Core.Database;
import Models.Album;
import Models.Artist;

import java.sql.*;
import java.util.*;

public class ArtistService {
    Database db = new Database();
    public ArtistService(){

    }

    public Artist getArtistById(String id){
        Artist artist = new Artist();
        artist.setId(id);
        String name;
        String albumCount;
        String sql = "select Name\n" +
                "from Artist \n" +
                "where ArtistId = ?";
        String sql2 = "select count(AlbumId) as count\n" +
                "from Artist join Album using(ArtistId)\n" +
                "where ArtistId = ?";
        if(db.connect()){
            try{
                PreparedStatement pstmt = db.conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(id));
                ResultSet rs = pstmt.executeQuery();
                name = rs.getString("Name");
                artist.setName(name);
                artist.setId(id);

                pstmt = db.conn.prepareStatement(sql2);
                pstmt.setInt(1, Integer.parseInt(id));
                rs = pstmt.executeQuery();
                albumCount = rs.getString("count");
                /*System.out.println("Get artist by id: " + id +
                        " name: " + name +
                        " albumCount: " + albumCount);*/
                artist.setAlbumCount(albumCount);
            }
            catch (SQLException sqle){
                //sqle.printStackTrace();
                System.out.println("SQLException: " + sqle.getMessage());
            }
            catch (NumberFormatException nfe){
                //System.out.println(nfe.getCause());
                System.out.println("Number format exception: " + nfe.getMessage());
            }
        }
        db.close();
        return artist;
    }

    public List<Artist> getArtistByName(String name){
        List<Artist> list = new ArrayList<>();
        String sql = "select ArtistId, Name\n" +
                "from Artist\n" +
                "where Name like ?";
        if(db.connect()){
            try{
                PreparedStatement pstmt = db.conn.prepareStatement(sql);
                pstmt.setString(1, "%" + name + "%");
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()) {
                    Artist artist = new Artist();
                    String n = rs.getString("Name");
                    String id = rs.getString("ArtistId");
                    //System.out.println("Artist id:" + id + " Artist Name: " + n);
                    artist.setId(id);
                    artist.setName(n);
                    list.add(artist);
                }
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }
        db.close();
        list = getAlbumCount(list);
        return list;
    }

    public List<Artist> getAlbumCount(List <Artist> in_list){
        List<Artist> list = in_list;
        String sql = "select count(AlbumId) as count\n" +
                "from Artist join Album using(ArtistId)\n" +
                "where Name = ?";
        if(db.connect()){
            try{
                PreparedStatement pstmt = db.conn.prepareStatement(sql);
                for(int i=0; i<list.size(); i++){
                    pstmt.setString(1, list.get(i).getName());
                    ResultSet rs = pstmt.executeQuery();
                    String count = rs.getString("count");
                    list.get(i).setAlbumCount(count);
                }
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
        }
        db.close();
        return list;
    }
}
