package soundweb.soundweb.Domain.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Entity.LockStatus;
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.BoardRepository;
import soundweb.soundweb.Domain.Repositoty.UserRepository;
import java.util.List;

import java.time.LocalDateTime;

@Service
@Transactional
public class BoardService {

    //생성자 오토와이어
    private BoardRepository boardRepository;
    private UserRepository userRepository;
    public BoardService(BoardRepository boardRepository,UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository=userRepository;
    }

    //글쓰는 함수
    public Long WritePost(String userId,String pwd,String title,String content){
        //유저 아이디랑 글 내용 받아와서 넘기기
        UserEntity findUser = userRepository.findUserOne(userId);
        BoardEntity boardEntity = new BoardEntity(pwd,findUser,title,content);

        return boardRepository.addPost(boardEntity);
    }


    //글하나 읽어오기
    @Transactional(readOnly = true)
    public BoardEntity ReadPost(Long id){
        BoardEntity findPost = boardRepository.findPost(id);
        return findPost;
    }

    //글 전체 읽어오기
    public List<BoardEntity> ReadPostAll(){
        List<BoardEntity> findPostAll = boardRepository.findPostAll();
        return findPostAll;
    }
}
