package soundweb.soundweb.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Entity.DTO.WriteDtoForm;
import soundweb.soundweb.Domain.Repositoty.BoardRepository;
import soundweb.soundweb.Domain.Repositoty.UserRepository;
import soundweb.soundweb.Domain.Service.BoardService;
import soundweb.soundweb.Domain.Service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WriteController {
    Logger logger= LoggerFactory.getLogger(WriteController.class);

    //생성자 오토와이어
    private BoardService boardService;
    private UserService userService;

    public WriteController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    
    //s07일 여기 로그인이 안되있으면 버튼 안보이게
    //글쓰는곳으로
    @GetMapping("/write")
    public String writePostPage(@SessionAttribute(value = "userName",required = false)String userId,
                                RedirectAttributes redirectAttributes){
        logger.info(userId+"글쓰기 get");
        if (userId==null){
            redirectAttributes.addFlashAttribute("errorMessage","로그인 하라고");
        }
        return "/writePost";
    }

    //글쓰기 완료시
    @PostMapping("/write")
    public String writePost(WriteDtoForm writeDtoForm,
                            @SessionAttribute(value = "userName",required = false) String userId,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes){
        logger.info(writeDtoForm.getTitle()+"내용"+writeDtoForm.getContent());


        //세션을 이용한 로그인 아이디 넘겨주는 작업 필요
        if(userId==null){
            redirectAttributes.addFlashAttribute("errorMessage","로그인 하라고");
            return "redirect:/login";
        }
        String userid=userId;
        //글쓰기 pwd 체크 만들어야함
        boardService.WritePost(userid,null,writeDtoForm.getTitle(),writeDtoForm.getContent());
        return "/Home/main";
    }

    //게시글 목록
    @GetMapping("/allpost")
    public String AllPost(Model model,
                         // HttpServletRequest httpServletRequest,
                          @CookieValue(value = "count",defaultValue ="0",required = true )String value,
                          HttpServletResponse httpServletResponse)
    {

        List<BoardEntity> findPostALl = boardService.ReadPostAll();
        model.addAttribute("AllPost",findPostALl);

        //쿠키함수
//        String value=null;
//        boolean find =false;
//        Cookie[]cookies=httpServletRequest.getCookies();
//        if(cookies !=null){
//            for (Cookie cookie:cookies){
//                if ("count".equals(cookie.getName())){
//                    find=true;
//                    value=cookie.getValue();
//                    break;
//                }
//            }
//        }
//        if (!find) {
//            value = "1";
//        }else {
            try {
                int i=Integer.parseInt(value);
                value=Integer.toString(++i);
            }catch (Exception e){
                value="1";
            }
       // }
        Cookie cookie=new Cookie("count",value);
        cookie.setMaxAge(60*60*24*365);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);

        model.addAttribute("cookieCount",value);


        return "/AllPost";
    }
}
