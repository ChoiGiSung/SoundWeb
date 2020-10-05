package soundweb.soundweb.Domain.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soundweb.soundweb.Domain.Entity.BoardEntity;
import soundweb.soundweb.Domain.Entity.ReplyEntity;
import soundweb.soundweb.Domain.Entity.UserEntity;
import soundweb.soundweb.Domain.Repositoty.BoardRepository;
import soundweb.soundweb.Domain.Repositoty.ReplyRepository;
import soundweb.soundweb.Domain.Repositoty.UserRepository;

import java.util.List;

@Service
@Transactional
public class ReplyService {

    Logger logger= LoggerFactory.getLogger(ReplyService.class);

    //생성자로 오토와이어
    private ReplyRepository replyRepository;
    private UserRepository userRepository;
    private BoardRepository boardRepository;
    public ReplyService(ReplyRepository replyRepository,UserRepository userRepository,BoardRepository boardRepository) {
        this.replyRepository = replyRepository;
        this.userRepository=userRepository;
        this.boardRepository=boardRepository;
    }

    //댓글쓰기
    public Long WriteReply(String user_id,Long boardId){
        //예제의 방법은 재료를 주고 여기서 만들어서 ReplyEntity 넘기는 거고
        //나는 완제품을 받아서 넘겨주는 역할만하게 만든거고
        UserEntity userEntity=userRepository.findUserOne(user_id);
        BoardEntity boardEntity=boardRepository.findPost(boardId);

        ReplyEntity replyEntity=new ReplyEntity(boardEntity,userEntity,"123","내용");

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
