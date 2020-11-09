package soundweb.soundweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import soundweb.soundweb.Dto.UserDto;
import soundweb.soundweb.jdbcServiceIMP.JdbcUserServiceIMP;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JdbcUserServiceIMP jdbcUserServiceIMP;

    @RequestMapping("/allpost")
    public String home(){
        return "redirect:/allpost";
    }



    //로그인
    @GetMapping("/login")
    public String Getlogin(Model model){
       // userService.join();
        //List<UserDto> users = jdbcUserServiceIMP.findAllUser();
       // logger.info(users+"");
       // model.addAttribute("User",users);
        
        //이미 세션에 로그인 되있으면 alert 주기
        return "/Home/main";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name ="userId") String userId,
                        @RequestParam(name="userPassword")String userPassword,
                        HttpSession session,
                        RedirectAttributes redirectAttributes,
                        Model model){
        //로그인 함수 만들고
        List<UserDto> findUser = jdbcUserServiceIMP.login(userId, userPassword);
        if(findUser.isEmpty()){
            redirectAttributes.addFlashAttribute("errorMessage","암호가 틀림");
            logger.info("로그인실패");
            return "redirect:/login";
        }else {
            logger.info("로그인성공");
            session.setAttribute("userName",userId);
            return "redirect:/allpost";
            //로그인 성고하면 글쓰기 페이지
        }
    }



    //로그인
    @GetMapping("/logout")
    public String logout(@SessionAttribute("userName") String userId,
                         HttpSession session){
        logger.info("로그아웃");
        session.removeAttribute("userName");
//        session.setAttribute("userName","");
        return "redirect:/login";
    }


}
