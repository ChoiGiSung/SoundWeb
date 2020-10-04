package soundweb.soundweb.Domain.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Repositoty.BoardRepository;

@Service
@Transactional
public class BoardService {

    //생성자 오토와이어
    private BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //글쓰는 함수
    public Long WritePost(BoardEntity boardEntity){
        return boardRepository.addPost(boardEntity);
    }
    
    //글하나 읽어오기
    public BoardEntity ReadPost(Long id){
        BoardEntity findPost = boardRepository.findPost(id);
        return findPost;
    }
}
