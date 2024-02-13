package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기전용, 조회 서비스의 성능 최적화
@RequiredArgsConstructor //final이 붙은 필드들로 생성자를 만들어줌
public class MemberService {

    private final MemberRepository memberRepository; //final 붙여 안정성 높임

    //회원 가입
    @Transactional //쓰기 서비스는 따로 설정. readOnly = false가 들어감
    public Long join(Member member){

        validateDuplicateMember(member); //중복 회원  검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
