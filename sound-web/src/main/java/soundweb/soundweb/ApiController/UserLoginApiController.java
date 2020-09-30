package soundweb.soundweb.ApiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import soundweb.soundweb.Dto.UserEntity;
import soundweb.soundweb.Service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserLoginApiController {

    @Autowired
    private UserService userService;

    //findById 아이디를 가지고 찾기 login기능 //틀릴경우 줄 error메세지도 있어야함
    @PostMapping("/api/login/{user_id}/{user_pwd}")
    public Result findById(@PathVariable("user_id")String user_id,
                                             @PathVariable("user_pwd")String user_pwd){

        List<UserEntity> findUser = userService.login(user_id,user_pwd);//영속성 엔티티이므로 lazy초기화를 해야함
        List<UserDto> result = findUser.stream() //lazy초기화
                .map(u -> new UserDto(u.getUser_id(),u.getUser_seting()))
                .collect(Collectors.toList());
        if (result.isEmpty())//해당 유저가 없으면 널 반환
            return new Result(null);

        return new Result(result);
        //돌려주는건 객체result data라는 이름으로 감싸진 userDtoList

    }

    //회원가입
    @PostMapping("/api/join/{user_id}/{user_pwd}")
    public JoinResult joinUser(@PathVariable("user_id")String user_id,@PathVariable("user_pwd")String user_pwd){
        String result = userService.joinUser(user_id, user_pwd);

        return new JoinResult(result);

    }

    @Data
    @AllArgsConstructor
    static class JoinResult<T>{
        //객체로 감싸서 보내기
        private String result;
    }







    @Data
    @AllArgsConstructor
    static class UserDto{
        //엔티티 노출하기 싫어서 dto로 변환

        //노출하고 싶은거만 서술
        private String user_id; //엔티티와 달리 이름을 변경해서 내보내도 괜찮다
        private String user_seting;
    }
    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data; //data라는 이름의 alluser
    }
}
