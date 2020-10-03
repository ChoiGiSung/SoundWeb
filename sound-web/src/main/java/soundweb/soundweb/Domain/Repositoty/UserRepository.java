package soundweb.soundweb.Domain.Repositoty;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Repository;
import soundweb.soundweb.Domain.Entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;
    
    //회원 가입
    public void addUser(UserEntity userEntity){
        em.persist(userEntity);
    }
    
    //유저 한명 찾기
    public UserEntity findUserOne(String userName){
        return em.find(UserEntity.class,userName);
    }

    //유저 여러명 찾기
    public List<UserEntity> findUsers(String userName){
        List<UserEntity> findUsers = em.createQuery("select m from UserEntity m where user_id =:userName", UserEntity.class)
                .setParameter("userName",userName)
                .getResultList();
        return findUsers;
    }


    
    
}
