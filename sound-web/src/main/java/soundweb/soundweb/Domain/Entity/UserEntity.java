package soundweb.soundweb.Domain.Entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
public class UserEntity {

    @Id
    @Column(name = "user_id")
    private String userName;

    @Column(name = "user_pwd")
    private String userPwd;

    @Embedded
    private DayEmbed dayEmbed;

    @Column(name = "user_seting")
    private String userSetting;

    public UserEntity() {
    }

    public UserEntity(String userName, String userPwd, DayEmbed dayEmbed, String userSetting) {
        this.userName = userName;
        this.userPwd = userPwd;
        this.dayEmbed = dayEmbed;
        this.userSetting = userSetting;
    }
}
