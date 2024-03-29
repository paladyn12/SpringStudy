package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //final이 붙은 필수값 필드를 parameter로 받는 생성자를 만듦
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

/*
    //@Autowired 생성자가 1개이면 @Autowired 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Autowired //수정자 주입, @Autowired는 주입할 대상이 없으면 오류
    //(required = false)를 붙이면 오류가 안나고 필요할 때만 주입하도록 함
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired //매서드 주입, 한번에 여러 필드 주입받음, 잘 사용하지 않음
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

   @Autowired 키워드를 필드에 붙여 필드 주입, 안좋은 코드
   >>외부 변경 불가로 테스트 힘듦, setter있어야 함


 */