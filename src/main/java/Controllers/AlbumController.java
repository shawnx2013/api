package Controllers;

import Models.Album;
import Services.AlbumService;

import com.google.gson.Gson;
import spark.*;
import static spark.Spark.*;

public class AlbumController {
    public AlbumController(){
    }

    public void start(){
        get("/album/:id", serveAlbum);
    }

    public static Route serveAlbum = (Request request, Response response) ->{
        //String id = request.queryParams("id");
        String id = request.params(":id");
        AlbumService albumService = new AlbumService();
        response.type("application/json");
        Album album = albumService.getAlbumById(id);
        if(!album.getTitle().isBlank()){
            return new Gson().toJson(album);
        }
        else
            return new Gson().toJson("the Album Id provided does not exist.");

    };
}
