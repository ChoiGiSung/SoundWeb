package soundweb.soundweb.Repository;

import soundweb.soundweb.Dto.UserEntity;
import java.util.List;

public interface UserRepository {
    List<UserEntity> findAll();
    String addUser();
    List<UserEntity> login(String User_id,String User_pwd);
    List<UserEntity> findSeting(String User_id);

}
