package soundweb.soundweb.Domain.Repositoty;

import org.springframework.stereotype.Repository;
import soundweb.soundweb.Domain.Entity.ReplyEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReplyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //댓글쓰기
    public Long addReply(ReplyEntity replyEntity){
        entityManager.persist(replyEntity);
        return replyEntity.getId();
    }

    //댓글 하나 읽기

    public ReplyEntity findReply(Long id){
        return entityManager.find(ReplyEntity.class,id);
    }

    //댓글 전부 읽기
    public List<ReplyEntity> findReplyAll(){
        return entityManager.createQuery("select r from ReplyEntity r",ReplyEntity.class).getResultList();
    }
}
