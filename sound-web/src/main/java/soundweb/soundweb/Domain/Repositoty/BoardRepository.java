package soundweb.soundweb.Domain.Repositoty;

import org.springframework.stereotype.Repository;
import soundweb.soundweb.Domain.Entity.BoardEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //게시글 만들기
    public Long addPost(BoardEntity boardEntity){
        entityManager.persist(boardEntity);
        return boardEntity.getId();
    }

    //게시글 한개 조회
    public BoardEntity findPost(Long id){
        BoardEntity findPost = entityManager.find(BoardEntity.class, id);
        return findPost;
    }

    //게시글 전부 조회
    public List<BoardEntity> findPostAll(){
        List<BoardEntity> findPostAll = entityManager.createQuery("select b from BoardEntity b", BoardEntity.class)
                .getResultList();
        return findPostAll;
    }

    //페이징
    public List<BoardEntity> findListPaging(int startIndex,int pageSize){
        return entityManager.createQuery("select b from BoardEntity b",BoardEntity.class)
                .setFirstResult(startIndex)
                .setMaxResults(pageSize)
                .getResultList();
    }

    //게시글 수 읽어오기
    public int findAllCnt(){
        return ((Number)entityManager.createQuery("select count(*) from BoardEntity").getSingleResult()).intValue();
    }



}
