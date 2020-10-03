package soundweb.soundweb.Domain.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.DayEmbed;
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.UserRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class JdbcUserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void join() {
        //given
        UserEntity userEntity1=new UserEntity("user3","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");

        //when
        String userName = userService.join(userEntity1);

        //than
        entityManager.flush(); //들어가는 값이 보고 싶어
        Assertions.assertEquals(userEntity1,userRepository.findUserOne(userName));
    }

    @Test
    void nameTest() throws Exception {
        //given
        //이름이 같으면 join에서 throw가 오는 데 밑에서 잡는다
        //근데 예외가 안날라오면 예외가 없으므로 오류가 난다!
        UserEntity userEntity1=new UserEntity("user1","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");
        UserEntity userEntity2=new UserEntity("user1","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");

        //when
        userService.join(userEntity1);

        //than
        
        //아 j유닛4 에서는 @test옆에 추가 기능 있었는데
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> userService.join(userEntity2));
        assertEquals("이미있는 회원", thrown.getMessage());
        //Assertions.fail("예외가 있어야하는데?");


    }
}