package soundweb.soundweb.ApiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import soundweb.soundweb.Dto.UpdateUserDay;
import soundweb.soundweb.Service.UserService;
import soundweb.soundweb.controller.UserController;

@RestController
public class UserUpdateApiController {
    @Autowired
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(UserController.class);


    //day 넣기
    @PutMapping("/api/userday/{user_id}")
    public void updateDay(@PathVariable("user_id") String user_id, @RequestBody UpdateUserDay userDay){
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
