package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    
    @Test
    @Rollback(false)//@Transactional 애노테이션이 붙으면 commit을 자동으로 Rollback 해버림
    //@Rollback을 false로 두면 transaction의 commit이 넘어가 insert문을 볼 수 있음
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Kim");
        
        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(memberRepository.findOne(savedId), member);
        //같은 transaction 내에서 같은 pk를 가지면 하나로 관리됨

    }
    
    @Test(expected = IllegalStateException.class) //기대되는 예외를 try-catch로 잡아야하는 것을 대신해줌
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("Kim1");

        Member member2 = new Member();
        member2.setName("Kim1");

        //when
        memberService.join(member1);
        memberService.join(member2); //같은 이름, 예외가 발생해야 함

        //then
        Assertions.fail("예외가 발생해야 함");
    }
}
//test 폴더 내에 resources 디렉터리를 추가하고 yml 파일의 경로를 메모리로 세팅하면
//h2 데이터베이스를 열지 않고도 메모리모드로 테스트 가능
//yml 파일의 로깅을 뺀 나머지를 없애도 스프링부트가 알아서 메모리 메모리모드로 세팅해줌