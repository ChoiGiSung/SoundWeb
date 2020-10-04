package soundweb.soundweb.Domain.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Entity.DayEmbed;
import soundweb.soundweb.Domain.Entity.ReplyEntity;
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.ReplyRepository;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class ReplyServiceTest {

    Logger logger= LoggerFactory.getLogger(ReplyServiceTest.class);

    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;
    @Autowired
    private EntityManager entityManager;



    @Test
    void writeReply() {
        //given
        UserEntity userEntity1=new UserEntity("user1","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");
        BoardEntity boardEntity=new BoardEntity("123",userEntity1,"타이틀","내용");
        ReplyEntity replyEntity=new ReplyEntity(boardEntity,userEntity1,"123","내용");

        //when
        replyService.WriteReply(replyEntity);

        //than
        Assertions.assertEquals(replyEntity,replyService.ReadReply(replyEntity.getId()));
    }

    @Test
    void readReply() {
        //user는 처음부터 id값을 줘서 persist하고 board는 처음에 id값 없이 persist한다
        //그러서 flush하기전까지는 id가 없다

        UserEntity userEntity1=new UserEntity("user5","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");
        BoardEntity boardEntity=new BoardEntity("123",userEntity1,"타이틀","내용");
        ReplyEntity replyEntity=new ReplyEntity(boardEntity,userEntity1,"123","내용");


//        userService.join(userEntity1);
//        boardService.WritePost(boardEntity);
        // entityManager.flush();
       // logger.info(boardService.ReadPost(findBoardId).getUserName().getUserName());
        replyService.WriteReply(replyEntity);
        replyService.ReadReply(replyEntity.getId());
//
        Assertions.assertEquals(replyEntity,replyService.ReadReply(replyEntity.getId()));

    }


    @Test
    void readReplyAll() {
        UserEntity userEntity1=new UserEntity("user2","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");
        BoardEntity boardEntity=new BoardEntity("123",userEntity1,"타이틀","내용");
        ReplyEntity replyEntity=new ReplyEntity(boardEntity,userEntity1,"123","내용");


        userService.join(userEntity1);
        boardService.WritePost(boardEntity);
        replyService.WriteReply(replyEntity);

        List<ReplyEntity> replyAll = replyService.ReadReplyAll();
        for(ReplyEntity a:replyAll){
            logger.info(a.getId()+":"+a.getConNum().getId());
        }

    }
}