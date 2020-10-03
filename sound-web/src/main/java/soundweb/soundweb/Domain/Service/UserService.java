package soundweb.soundweb.Domain.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    
    private UserRepository userRepository;
    //생성자로 오토와이어
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //비즈니스 로직
    public String join(UserEntity userEntity){
        userRepository.addUser(userEntity);
        //이름이 같은지 검사
        nameTest(userEntity.getUserName());

        return userEntity.getUserName();
    }

    public void nameTest(String userName){

        List<UserEntity> findUsers = userRepository.findUsers(userName);

        if(!findUsers.isEmpty()){
            throw new IllegalStateException("이미있는 회원");
        }
    }
}
