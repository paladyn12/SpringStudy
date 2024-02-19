package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired EntityManager em;

    @Test
    public void updateTest() throws Exception{
        Book book = em.find(Book.class,1L);

        //TX, 트랜잭션 내
        book.setName("asdfasdf");

        //Dirty Checking == 변경 감지 : 변경된 부분을 찾아 알아서 quary를 짜줌
        //TX commit


    }
}
