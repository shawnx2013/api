import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

import Core.*;

public class Main{
    private final Server server;

    public Main(Server server){
        this.server = server;
    }

    public static void main(String[] args){
        final TemplateEngine freeMarkerEngine = new FreeMarkerEngine();
        final Server server = new Server(freeMarkerEngine);

        final Main main = new Main(server);

        main.init();


    }//main

    private void init(){
        this.server.start();
    }
}