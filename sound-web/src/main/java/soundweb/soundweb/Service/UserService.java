package soundweb.soundweb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soundweb.soundweb.Dao.UserDao;
import soundweb.soundweb.Dto.UpdateUserDay;
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
    public List<UserEntity> findData(String user_id){
        return userDao.findData(user_id);
    }
    public String updateDay(String user_id,UpdateUserDay updateUserDay){return userDao.updateUserDay( user_id,updateUserDay);}
    public String updateSetting(String user_id,String setting){return userDao.updateUserSetting(user_id,setting);}
    public String joinUser(String user_id,String user_pwd){return userDao.join(user_id,user_pwd);}


}
