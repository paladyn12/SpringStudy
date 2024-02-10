package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") //hello라는 url로 오면 이 Controller가 호출 됨
    public String hello(Model model){ //model에 data를 실어 view로 넘길 수 있음

        model.addAttribute("data", "hello!!!");
        //model의 data라는 키의 값을 hello!!!로 바꿈

        return "hello";
        //화면 이름, 관례상 resources의 templates에 view name을 작성
    }
}
