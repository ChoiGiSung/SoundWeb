package soundweb.soundweb.ApiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soundweb.soundweb.Dto.UpdateUserDay;
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
        List<UserDataDto> dtoResult = findUser.stream()// 영속성 엔티티를 dto로 변환
                .map(u -> new UserDataDto(u.getUser_id(),u.getDay_1(),u.getDay_2(),u.getDay_3(),u.getDay_4(),u.getDay_5(),u.getDay_6(),u.getDay_7(),u.getUser_seting()))
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
    static class UserDataDto{
        //엔티티 노출하기 싫어서 dto로 변환
        
        //노출하고 싶은거만 서술
        private String user_id; //엔티티와 달리 이름을 변경해서 내보내도 괜찮다
        private String day_1;
        private String day_2;
        private String day_3;
        private String day_4;
        private String day_5;
        private String day_6;
        private String day_7;
        private String user_seting;
    }
    
    //findById 아이디를 가지고 찾기 login기능 //틀릴경우 줄 error메세지도 있어야함
    @GetMapping("/api/login/{user_id}/{user_pwd}")
    public Result findById(@PathVariable("user_id")String user_id,
                           @PathVariable("user_pwd")String user_pwd){

        List<UserEntity> findUser = userService.login(user_id,user_pwd);//영속성 엔티티이므로 lazy초기화를 해야함
        List<UserDto> result = findUser.stream() //lazy초기화
                .map(u -> new UserDto(u.getUser_id()))
                .collect(Collectors.toList());
        if (result.isEmpty())//해당 유저가 없으면 널 반환
            return new Result(null);

        return new Result(result);
        //돌려주는건 객체result data라는 이름으로 감싸진 userDtoList

    }
    @Data
    @AllArgsConstructor
    static class UserDto{
        //엔티티 노출하기 싫어서 dto로 변환

        //노출하고 싶은거만 서술
        private String user_id; //엔티티와 달리 이름을 변경해서 내보내도 괜찮다
    }

    // 아이디를 가지고 찾기 데이타 찾기 //틀릴경우 줄 error메세지도 있어야함
    @GetMapping("/api/data/{user_id}")
    public Result findData(@PathVariable("user_id")String user_id){
        List<UserEntity> findData =userService.findData(user_id);
        List<UserDataDto> userSetings=findData.stream().map(u ->new UserDataDto(u.getUser_id(),u.getDay_1(),u.getDay_2(),u.getDay_3(),u.getDay_4(),u.getDay_5(),u.getDay_6(),u.getDay_7(),u.getUser_seting())).collect(Collectors.toList());
        if (userSetings.isEmpty())//해당 유저가 없으면 널 반환
            return new Result(null);

        return new Result(userSetings);
    }

    //day 넣기
    @PutMapping("/api/userday/{user_id}")
    public void updateDay(@PathVariable ("user_id") String user_id,@RequestBody UpdateUserDay userDay){
        //업데이트 함수호춯
        logger.info("update"+user_id+userDay.getDay_7());
        userService.updateDay(user_id,userDay);
    }

    //setting 넣기
    @PutMapping("/api/usersetting/{user_id}")
    public void updateSetting(@PathVariable ("user_id") String user_id,@RequestBody Setting setting){
        //업데이트 함수호춯
        logger.info("update"+user_id+setting);
        userService.updateSetting(user_id,setting.getSetting());
    }

    @Data
    static class Setting{
        private String setting;
    }






}
