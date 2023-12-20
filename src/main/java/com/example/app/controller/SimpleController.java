package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    @GetMapping("/main")
    public String goMain(){
        return "main";
    }

    @GetMapping("books/recommendBook")
    public String goRecommend(){
        return "books/1-2recommend";
    }

    @GetMapping("books/wishBook")
    public String gowishBook(){
        return "books/1-3hope";
    }

    @GetMapping("booksPolicy/lendPolicy")
    public String golendPolicy(){
        return "bookPolicy/2-1lend";
    }

    @GetMapping("booksPolicy/returnPolicy")
    public String goreturnPolicy(){
        return "bookPolicy/2-2return";
    }

    @GetMapping("booksPolicy/reservationPolicy")
    public String goreservationPolicy(){
        return "bookPolicy/2-3reserve";
    }

    @GetMapping("introduction/intro")
    public String gointro(){
        return "introduction/4-1greetings";
    }

    @GetMapping("introduction/OPtime")
    public String goOPtime(){
        return "introduction/4-2time";
    }

    @GetMapping("introduction/map")
    public String goMap(){
        return "introduction/4-3map";
    }

    @GetMapping("mypage/mySchedule")
    public String goMySchedule(){
        return "mypage/5-2myBook";
    }

    @GetMapping("admin/booksetting")
    public String goBookSetting(){
        return "admin/5-6booksetting";
    }



}