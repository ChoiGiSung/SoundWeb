package soundweb.soundweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import soundweb.soundweb.Dto.UserEntity;
import soundweb.soundweb.Service.UserService;

import java.util.List;

@Controller
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model){
       // userService.join();
        List<UserEntity> users = userService.findAllUser();
       // logger.info(users+"");
        model.addAttribute("User",users);
        return "/Home/main";
    }


}
