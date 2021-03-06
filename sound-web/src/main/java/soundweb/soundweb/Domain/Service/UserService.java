package soundweb.soundweb.Domain.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.UserRepository;

import java.util.List;
import java.util.ListIterator;

@Service
@Transactional
public class UserService {

    Logger loggerFactory=LoggerFactory.getLogger(UserService.class);
    
    private UserRepository userRepository;
    //생성자로 오토와이어
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //비즈니스 로직
    public String join(UserEntity userEntity){
        //이름이 같은게 있는지 먼저 검사
        nameTest(userEntity.getUserName());

        userRepository.addUser(userEntity);

        return userEntity.getUserName();
    }
    //이름 같은게 있는지 검사
    public void nameTest(String userName){

        List<UserEntity> findUsers = userRepository.findUsers(userName);
        loggerFactory.info("회원검사 select");
        if(!findUsers.isEmpty()){
            for(UserEntity a:findUsers){
                loggerFactory.info(a.getUserName());
            }
            throw new IllegalStateException("이미있는 회원");
        }
    }

    //유저 한명 조회
    @Transactional(readOnly = true)
    public UserEntity findUserOne(String userName){
        return userRepository.findUserOne(userName);
    }

}
