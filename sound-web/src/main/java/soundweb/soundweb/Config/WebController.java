package soundweb.soundweb.Config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//에러시 갈곳

@Controller
public class WebController implements ErrorController {
    @GetMapping( {"/react","/error"})
    public String index() {
        return "/Home/sample";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
