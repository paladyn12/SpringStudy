package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            Member member1 = new Member();
            member1.setUsername("KIM");
            Member member2 = new Member();
            member2.setUsername("KIM");
            em.persist(member1);
            em.persist(member2);

            Team team = new Team();
            team.setName("Team1");
            team.getMembers().add(member1);
            team.getMembers().add(member2);

            em.persist(team);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}