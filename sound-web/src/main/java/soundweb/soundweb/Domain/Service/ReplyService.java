package soundweb.soundweb.Domain.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.ReplyEntity;
import soundweb.soundweb.Domain.Repositoty.ReplyRepository;
import java.util.List;

@Service
@Transactional
public class ReplyService {

    Logger logger= LoggerFactory.getLogger(ReplyService.class);

    //생성자로 오토와이어
    private ReplyRepository replyRepository;
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    //댓글쓰기
    public Long WriteReply(ReplyEntity replyEntity){
        replyRepository.addReply(replyEntity);
        return replyEntity.getId();
    }

    //댓글 하나 읽기
    public ReplyEntity ReadReply(Long id){
        ReplyEntity findReply = replyRepository.findReply(id);
        logger.info(findReply.getConNum().getId()+":dd"+findReply.getUserName().getUserName());
        return findReply;
    }

    //댓글 전체 읽기
    public List<ReplyEntity> ReadReplyAll(){
        List<ReplyEntity> findReplyAll = replyRepository.findReplyAll();

        for (ReplyEntity a:findReplyAll){
            logger.info(a.getId().toString());
        }

        return findReplyAll;
    }
}
