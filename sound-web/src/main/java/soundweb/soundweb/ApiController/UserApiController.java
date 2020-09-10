package soundweb.soundweb.ApiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import soundweb.soundweb.Dto.UserEntity;
import soundweb.soundweb.Service.UserService;
import soundweb.soundweb.controller.UserController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserApiController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    
    // 모든 유저 조회
    @GetMapping("/api/user")
    public Result user(){
        List<UserEntity> findUser = userService.findAllUser();//영속성 엔티티
        List<UserDto> dtoResult = findUser.stream()// 영속성 엔티티를 dto로 변환
                .map(u -> new UserDto(u.getUser_id(),u.getDay_1()))
                .collect(Collectors.toList());

        return new Result(dtoResult);
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data; //data라는 이름의 alluser
    }

    @Data
    @AllArgsConstructor
    static class UserDto{
        //엔티티 노출하기 싫어서 dto로 변환
        
        //노출하고 싶은거만 서술
        private String user_id; //엔티티와 달리 이름을 변경해서 내보내도 괜찮다
        private String day_1;
    }
    
    //findById
    @GetMapping("api/login/{user_id}")
    public Result findById(@PathVariable("user_id")String user_id){



        List<UserEntity> findUser = userService.findById(user_id);//영속성 엔티티이므로 lazy초기화를 해야함
        List<UserDto> result = findUser.stream()
                .map(u -> new UserDto(u.getUser_id(),u.getDay_1()))
                .collect(Collectors.toList());

        return new Result(result);

    }
}
