package Services;

import Core.Database;
import Models.Album;

import java.sql.*;
import java.text.Normalizer;
import java.util.*;

public class AlbumService {

    Database db = new Database();
    public AlbumService(){

    }

    public String getAlbumTitle(String id){
        String albumTitle = "";
        String sql = "select Title from Album where AlbumId = ?";
        if(db.connect()){
            try{
                PreparedStatement pstmt = db.conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(id));
                ResultSet rs = pstmt.executeQuery();
                albumTitle = rs.getString("Title");
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
        /*System.out.println("getAlbumTitle by id: " + id +
                " Title: " + albumTitle);*/
        return albumTitle;
    }

    public Album getAlbumById(String id){
        String title = this.getAlbumTitle(id);

        Album album = new Album();
        album.setId(id);
        album.setTitle(title);

        String sql = "select Name\n"+
                     "from Album join Track using(AlbumId)\n"+
                     "where AlbumId = ? ";
        List<String> list = new ArrayList<String>();
        if(db.connect()){
            try{
                PreparedStatement pstmt = db.conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(id));
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    String trackName = rs.getString("Name");
                    //System.out.println("Track: " + trackName);
                    list.add(trackName);
                }
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
            catch (NumberFormatException nfe){
                //System.out.println(nfe.getCause());
                System.out.println("Number format exception: " + nfe.getMessage());
            }
        }
        db.close();
        album.setTracks(list);
        return album;
    }
}
