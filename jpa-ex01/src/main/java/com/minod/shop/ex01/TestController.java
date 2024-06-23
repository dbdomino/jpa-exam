package com.minod.shop.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    //  resources/templates 가 thymeleaf의 기본경로.
    //  resources/templates/hello.html
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "data1입니다.");
        return "hello"; // resources/templates/hello.html 으로 연결됨.
    }
}
