package Core;

import spark.TemplateEngine;

import Controllers.*;

import static spark.Spark.*;

public class Server {

    private final TemplateEngine freeMarker;

    public Server(TemplateEngine freeMarker) {
        this.freeMarker = freeMarker;
    }

    public void start(){
        //staticFileLocation("/public");
        port(4567);//Spark will run on port 4567
        AlbumController albumControl = new AlbumController();
        albumControl.start();
        ArtistController artistController = new ArtistController();
        artistController.start();
        IndexController indexController = new IndexController(freeMarker);
        indexController.start();

    }
}

