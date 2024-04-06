package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {

    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/userA
     */

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}",data);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume(){
        log.info("mappingConsume");
        return "ok";
    }


}
