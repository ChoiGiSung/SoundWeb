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
        entityManager.flush();
        Assertions.assertEquals(userEntity1,userRepository.findUserOne(userName));
    }

    @Test
    void nameTest() {
        UserEntity userEntity1=new UserEntity("user1","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");

        UserEntity userEntity2=new UserEntity("user2","123",new DayEmbed("0","0","0","0","0","0","0"),"sd");

    }
}