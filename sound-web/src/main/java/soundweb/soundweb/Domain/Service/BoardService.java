package soundweb.soundweb.Domain.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Entity.LockStatus;
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.BoardRepository;
import soundweb.soundweb.Domain.Repositoty.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import java.time.LocalDateTime;

@Service
@Transactional
public class BoardService {

    @PersistenceContext
    private EntityManager entityManager;


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

    //페이징 읽어오기
    public List<BoardEntity> ReadPageList(int start,int pageSize){
        return boardRepository.findListPaging(start,pageSize);
    }

    //총 게시글 수 읽어오기
    public int getAllPostCnt(){
        return boardRepository.findAllCnt();
    }

    //게시글 업데이트
    public void updatePost(String title,String content,Long Id){
        BoardEntity findPost = ReadPost(Id);
        findPost.setTitle(title);
        findPost.setContent(content);
    }

    //게시글 삭제
    public void removePost(Long id){
        BoardEntity findPost = ReadPost(id);
        entityManager.remove(findPost);
    }
}
