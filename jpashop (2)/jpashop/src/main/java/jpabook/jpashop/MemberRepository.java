package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em; //스프링 부트가 EntityManager를 주입해줌


    public Long save(Member member){
        em.persist(member);
        return member.getId(); //Commend와 Query를 분리, 저장 하면 return값을 거의 없앰
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
