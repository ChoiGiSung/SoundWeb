package soundweb.soundweb.Repository;

import soundweb.soundweb.Dto.UserEntity;
import java.util.List;

public interface UserRepository {
    List<UserEntity> findAll();
    String addUser();
    List<UserEntity> findBYId(String User_id);
}
