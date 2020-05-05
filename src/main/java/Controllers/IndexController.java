package Controllers;

import spark.*;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class IndexController {
    private static TemplateEngine freeMarker = null;
    public IndexController(TemplateEngine freeMarker){
        this.freeMarker = freeMarker;
    }

    public void start(){
        get("/", serverIndex);
    }

    public static Route serverIndex = (Request request, Response response) ->{
        System.out.println("Request sent to IndexRoute");
        Map<String, Object> map = new HashMap<>();
        return freeMarker.render(new ModelAndView(map, "index.ftl"));
    };
}
