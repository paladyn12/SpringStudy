package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ //JPA 저장 전까지 id값이 없음, 완전 새로 생성한 객체, 이를 persist하여 등록
            em.persist(item);
        } else{
            em.merge(item); //이미 DB에 등록된 것을 가져온 경우
        }
    }

    public Item fineOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
