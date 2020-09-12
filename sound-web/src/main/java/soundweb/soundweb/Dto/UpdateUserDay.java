package soundweb.soundweb.Dto;

import lombok.Data;

import java.io.Serializable;

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
