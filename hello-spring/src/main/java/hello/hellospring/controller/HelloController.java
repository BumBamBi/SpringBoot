package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {

    @GetMapping(value = "sendSignal")
    public String hello(Model model){
        model.addAttribute("data", "Value");
        return "page1";
    }

    @GetMapping(value="helloMvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "page2";
    }
    
}
