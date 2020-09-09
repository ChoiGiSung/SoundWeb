package soundweb.soundweb.Repository;

import soundweb.soundweb.Dto.UserDto;
import java.util.List;

public interface UserRepository {
    List<UserDto> findAll();
    String addUser();
}
