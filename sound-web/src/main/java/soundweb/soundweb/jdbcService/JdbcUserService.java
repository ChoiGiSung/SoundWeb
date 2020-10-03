package soundweb.soundweb.jdbcService;

import soundweb.soundweb.Dto.UpdateUserDay;
import soundweb.soundweb.Dto.UserDto;
import java.util.List;

public interface JdbcUserService {
    List<UserDto> findAll();
    String addUser();
    List<UserDto> login(String User_id, String User_pwd);
    List<UserDto> findData(String User_id);
    String updateUserDay(String user_id,UpdateUserDay updateUserDay);
    String updateUserSetting(String user_id,String setting);
    String join(String user_id,String user_pwd);

}
