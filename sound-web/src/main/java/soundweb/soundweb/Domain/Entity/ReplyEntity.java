package soundweb.soundweb.Domain.Entity;

import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "con_num")
    private BoardEntity conNum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userName;

    private String pwd;
    private String content;
    private LocalDateTime date;

}
