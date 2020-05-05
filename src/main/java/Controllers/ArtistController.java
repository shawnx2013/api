package Controllers;

import Models.Artist;
import Services.ArtistService;

import com.google.gson.Gson;
import spark.*;

import java.util.List;

import static spark.Spark.*;

public class ArtistController {
    public ArtistController(){
    }

    public void start(){
        get("/artist/:id", serveArtist);
    }

    public static Route serveArtist = (Request request, Response response) ->{
        String input = request.params(":id");
        ArtistService artistService = new ArtistService();
        response.type("application/json");
        if(isNumeric(input)) {
            Artist artist = artistService.getArtistById(input);
            if (artist.getName() != null) {
                return new Gson().toJson(artist);
            } else
                return new Gson().toJson("the Artist Id provided does not exist.");
        }
        else{
            List list = artistService.getArtistByName(input);
            if(list.size() > 0){
                return new Gson().toJsonTree(list).getAsJsonArray();
            }
            else
                return new Gson().toJson("Nothing returned from the input provided");
        }

    };

    //some dumb method to test if the request param is a int,
    // should have better approaches
    public static boolean isNumeric(String str){
        try{
            int i = Integer.parseInt(str);
            double j = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
