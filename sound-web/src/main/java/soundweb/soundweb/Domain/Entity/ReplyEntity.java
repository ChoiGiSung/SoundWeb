package soundweb.soundweb.Domain.Entity;

import lombok.Getter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
@Getter
public class ReplyEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_num") //게시판 번호
    private BoardEntity conNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")//유저 아이디
    private UserEntity userName;

    private String pwd;
    private String content;
    private LocalDateTime date;

    protected ReplyEntity() {
    }

    public ReplyEntity(BoardEntity conNum, UserEntity userName, String pwd, String content) {
        this.conNum = conNum;
        this.userName = userName;
        this.pwd = pwd;
        this.content = content;
        this.date = LocalDateTime.now();
    }
}
