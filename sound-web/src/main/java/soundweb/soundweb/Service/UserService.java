package soundweb.soundweb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soundweb.soundweb.Dao.UserDao;
import soundweb.soundweb.Dto.UserEntity;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<UserEntity> findAllUser(){
        return userDao.findAll();
    }

    public String join(){
        return userDao.addUser();
    }

    public List<UserEntity> findById(String user_id){
        return userDao.findBYId(user_id);
    }
}
