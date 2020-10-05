package soundweb.soundweb.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Entity.DTO.WriteDtoForm;
import soundweb.soundweb.Domain.Repositoty.BoardRepository;
import soundweb.soundweb.Domain.Repositoty.UserRepository;
import soundweb.soundweb.Domain.Service.BoardService;
import soundweb.soundweb.Domain.Service.UserService;

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

    //글쓰는곳으로
    @GetMapping("/write")
    public String writePostPage(){
        return "/writePost";
    }

    //글쓰기 완료시
    @PostMapping("/write")
    public String writePost(WriteDtoForm writeDtoForm, Model model){
        logger.info(writeDtoForm.getTitle()+"내용"+writeDtoForm.getContent());
        
        //세션을 이용한 로그인 아이디 넘겨주는 작업 필요
        boardService.WritePost("user1",null,writeDtoForm.getTitle(),writeDtoForm.getContent());
        return "/Home/main";
    }

    @GetMapping("/allpost")
    public String AllPost(Model model){

        List<BoardEntity> findPostALl = boardService.ReadPostAll();
        model.addAttribute("AllPost",findPostALl);

        return "/AllPost";
    }
}
