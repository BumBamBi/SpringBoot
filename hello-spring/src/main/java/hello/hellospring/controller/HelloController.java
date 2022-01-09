package hello.hellospring.controller;

import com.fasterxml.jackson.annotation.JsonSetter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    /* MVC 예시 */
    @GetMapping(value = "sendSignal")
    public String hello(Model model){
        model.addAttribute("data", "Value");
        return "page1";
    }

    @GetMapping(value="helloMvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {    // Get방식으로 파라미터 받기 -> 주소/GETvalue?변수명=값
        model.addAttribute("name", name);
        return "page2";
    }
    


    /* API 예시 */
    @GetMapping(value="helloString")
    @ResponseBody       // Body 부분에 바로 응답하겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 파라미터로 입력 받은 값 그대로 출력
    }
    


    // 내부클래스 생성 - API로 객체를 주고받기위해 대충 하나 만들어서쓰려고 만듬
    static class apiTestClass{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
    }

    @GetMapping(value="helloApi")
    @ResponseBody
    // 리턴이 객체인 메서드 생성
    // 이러면 JSON 방식{키 : 값}으로 값이 내보내짐
    public apiTestClass apiTest(@RequestParam("name") String name){
        apiTestClass test1 = new apiTestClass();
        test1.setName(name);    // GET 파라미터 입력 방식으로 받아온 값으로 set함.
        return test1;
    }
    

}
