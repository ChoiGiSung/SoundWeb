package soundweb.soundweb.Domain.Entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //다대일 관계 양방향은 안만들었다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userName;

    private String pwd;

    private String title;

    private String content;

    private LocalDateTime date;



    private int hit;

    @Enumerated(EnumType.STRING)
    private LockStatus lock_post;

    protected BoardEntity() {
    }

    public BoardEntity(String pwd,UserEntity userEntity,String title, String content) {
        this.pwd = pwd;
        this.title = title;
        this.content = content;
        this.userName=userEntity;
        this.date = LocalDateTime.now();
        this.hit = 0;
        this.lock_post = LockStatus.UNLOCK;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
