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

import javax.servlet.http.*;
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
                          @SessionAttribute(value = "userName",required = false) String userId,
                          @RequestParam(name ="page",defaultValue = "1")int page,
                          @RequestParam(name = "post",required = false)Integer postNum
                         )
    {
        logger.info(page+"dd");
        if(postNum!=null){
            logger.info(postNum+"dd");// 게시글 번호가 있으니 거기로 가자
            BoardEntity findOne = boardService.ReadPost(postNum.longValue());

            if(userId!=null && userId.equals(findOne.getUserName().getUserName())){
                model.addAttribute("check","true");
                logger.info("수정 가능");
            }

            model.addAttribute("onePost",findOne);
            return "/readPostOne";
        }


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
        Pagination pagination = new Pagination(totalListCnt, page);
        logger.info(pagination.getPage()+":"+pagination.getBlockSize()+":"+pagination.getBlock());

        //DB 탐색 시작
        int startIndex = pagination.getStartIndex();
        //페이징 당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();

        List<BoardEntity> boardList=boardService.ReadPageList(startIndex,pageSize);

        model.addAttribute("boardList",boardList);
        model.addAttribute("pagination",pagination);

        return "/AllPost";
    }

    //업데이트 버튼 눌러서 오는곳
    @PostMapping("/update/{id}/post")
    public String updatePost(@PathVariable("id") Long postId,
                             @SessionAttribute(value = "userName",required =false) String userId,
                             RedirectAttributes redirectAttributes,
                             Model model){

        if(userId ==null){
            redirectAttributes.addFlashAttribute("errorMessage","로그인해");
            return "redirect:/login";
        }
        BoardEntity findPost = boardService.ReadPost(postId);
        model.addAttribute("findPost",findPost);

        logger.info("업데이트");
        //업데이트에서 수정 완료를 누르면 내영 set
        logger.info(postId+"");

        return "/updatePost";
    }

    //업데이트 완료
    @GetMapping("/gogo")
    public String updatePost(WriteDtoForm writeDtoForm,
                             @RequestParam(name = "Id",required = false)Long Id){
        logger.info("업데이트 gogo"+Id);

        boardService.updatePost(writeDtoForm.getTitle(),writeDtoForm.getContent(),Id);
        return "redirect:/allpost";
    }

    //삭제
    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable(name = "id")Long id){
        logger.info("삭제 gogo"+id);
        boardService.removePost(id);
        return "redirect:/allpost";
    }

}
