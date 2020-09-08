package soundweb.soundweb.ApiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import soundweb.soundweb.Dto.UserDto;
import soundweb.soundweb.Service.UserService;

import java.util.List;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/user")
    public Result user(){
        List<UserDto> allUser = userService.findAllUser();

        return new Result(allUser);
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data; //data라는 이름의 alluser
    }
}
