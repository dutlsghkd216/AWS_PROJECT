package com.example.app.controller;

import com.example.app.domain.dto.BookDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import com.example.app.domain.paging.PageMakerDTO;
import com.example.app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    //    private final UserService userService;
    private final BookService bookService;


    // 관리자 페이지 도서 목록
    @GetMapping("/admin/5-4adminbook")
    public void showListAll(Search search, Criteria criteria, Model model){

//        Criteria criteriaBook = new Criteria(criteria.getPageNum(), 5);

        Criteria criteriaBook;

        if(criteria.getPageNum() == 1){
            criteriaBook = new Criteria(1, 10);
        } else {
            criteriaBook = new Criteria(criteria.getPageNum(), 10);
        }
        List<BookDTO> list = bookService.getList(criteriaBook, search);
        model.addAttribute("listBook", list);
        Long total = bookService.getListCount(search);
        criteriaBook.setPageNum(criteriaBook.getPageNum());
        PageMakerDTO pageMaker = new PageMakerDTO(criteriaBook, total);
        System.out.println(criteria);

        Long totalPostCount = bookService.getListCount(search);
        model.addAttribute("totalPostCount", totalPostCount);
        model.addAttribute("pageMaker", pageMaker);
    }


}
