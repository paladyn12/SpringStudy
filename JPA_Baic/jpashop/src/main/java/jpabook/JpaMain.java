package jpabook;

import jakarta.persistence.*;
import jpabook.domain.*;

import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            Member member = new Member();
            member.setName("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select i From Item i where type(i)=Book";

            Query query1 = em.createQuery(query);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}