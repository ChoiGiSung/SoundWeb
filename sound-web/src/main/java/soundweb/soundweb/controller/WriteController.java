package soundweb.soundweb.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Entity.DTO.Pagination;
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
        
        //아이디값 없으면 로그인 화면으로
        if (userId==null){
            redirectAttributes.addFlashAttribute("errorMessage","로그인 하라고");
            return "redirect:/login";
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
        return "redirect:/allpost";
    }



    //게시글 목록
    @GetMapping("/allpost")
    public String AllPost(Model model,
                          // HttpServletRequest httpServletRequest,
                          @CookieValue(value = "count",defaultValue ="0",required = true )String value,
                          HttpServletResponse httpServletResponse,
                          @RequestParam(defaultValue = "1")int page
                         )
    {

//        List<BoardEntity> findPostALl = boardService.ReadPostAll();
//        model.addAttribute("AllPost",findPostALl);

        //방문수 쿠키
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

        //페이징
        //총 게시물 수
        int totalListCnt=boardService.getAllPostCnt();

        //생성인자로 총 게시물 수, 현재 페이지를 전달
        Pagination pagination=new Pagination(totalListCnt,page);

        //DB 탐색 시작
        int startIndex=pagination.getStartIndex();
        //페이징 당 보여지는 게시글의 최대 개수
        int pageSize=pagination.getPageSize();

        List<BoardEntity> boardList=boardService.ReadPageList(startIndex,pageSize);

        model.addAttribute("boardList",boardList);
        model.addAttribute("pagination",pagination);

        return "/AllPost";
    }
}
