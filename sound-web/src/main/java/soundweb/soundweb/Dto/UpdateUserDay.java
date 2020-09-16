package soundweb.soundweb.Dto;

import lombok.Data;

import java.io.Serializable;


//업데이트시 오는 날짜 모음 객체
//객체가 api통신으로 올때는 Serializable을 상속 받아야 한다.
@Data
public class UpdateUserDay implements Serializable {
    private String day_1;
    private String day_2;
    private String day_3;
    private String day_4;
    private String day_5;
    private String day_6;
    private String day_7;
}
