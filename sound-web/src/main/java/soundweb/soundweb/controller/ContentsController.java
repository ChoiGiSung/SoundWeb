package soundweb.soundweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContentsController {

    @GetMapping("/")
    public String HomePage(){
        return "Home/HomePage";
    }

    @RequestMapping("/jupasu")
    public String jupasu(){
        return "contents/jupasu";
    }

    @RequestMapping("/summary")
    public String Summary(){
        return "contents/summary";
    }
}
