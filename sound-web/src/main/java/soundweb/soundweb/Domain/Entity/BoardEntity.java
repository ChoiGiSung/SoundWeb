package soundweb.soundweb.Domain.Entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue
    private Long id;

    //다대일 관계 양방향은 안만들었다
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userName;

    private String pwd;

    private String title;

    private String content;

    private LocalDateTime date;

    private int hit;

    private int lock_post;
    
    
}
