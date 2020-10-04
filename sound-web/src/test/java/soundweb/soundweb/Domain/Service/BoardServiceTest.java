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
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.BoardRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class BoardServiceTest {

    Logger loggerFactory= LoggerFactory.getLogger(BoardServiceTest.class);
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private EntityManager entityManager;

    @Test
    //@Rollback(value = false)
    void writePost() {
        //given
        UserEntity userEntity1=new UserEntity("user3","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");
        BoardEntity boardEntity=new BoardEntity("123",userEntity1,"타이틀","내용");

        //when
        entityManager.persist(userEntity1);
        Long returnId = boardService.WritePost(boardEntity);
        loggerFactory.info("----------------");

        //than
        loggerFactory.info("----------------"+boardEntity.getId());
        Assertions.assertEquals(boardEntity.getId(),boardService.ReadPost(boardEntity.getId()).getId());

    }

    @Test
    void readPost(){
        //given
        UserEntity userEntity1=new UserEntity("user1","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");
        BoardEntity boardEntity=new BoardEntity("123",userEntity1,"타이틀","내용");

        //when
        boardService.WritePost(boardEntity);
        BoardEntity findPost = boardService.ReadPost(boardEntity.getId());
        //than
        Assertions.assertEquals(boardEntity,findPost);
    }
}