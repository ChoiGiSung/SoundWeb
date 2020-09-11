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

    public List<UserEntity> login(String user_id,String user_pwd){
        return userDao.login(user_id,user_pwd);
    }
    public List<UserEntity> findSeting(String user_id){
        return userDao.findSeting(user_id);
    }


}
