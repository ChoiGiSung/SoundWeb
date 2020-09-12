package soundweb.soundweb.Repository;

import soundweb.soundweb.Dto.UpdateUserDay;
import soundweb.soundweb.Dto.UserEntity;
import java.util.List;

public interface UserRepository {
    List<UserEntity> findAll();
    String addUser();
    List<UserEntity> login(String User_id,String User_pwd);
    List<UserEntity> findData(String User_id);
    String updateUserDay(String user_id,UpdateUserDay updateUserDay);
    String updateUserSetting(String user_id,String setting);

}
