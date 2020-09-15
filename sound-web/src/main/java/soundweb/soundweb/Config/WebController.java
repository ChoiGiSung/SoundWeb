package soundweb.soundweb.Config;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//react 프로젝트 경루

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
