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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    /*
     * Task			URL					Method			Parameter			Form            URL 이동
     *
     * 전체 목록		/book/list			GET
     * 등록 처리		/book/write		    POST			모든 항목				입력화면 필요			이동
     * 조회			/book/read		    GET				isbn
     * 삭제 처리		/book/remove		GET				isbn				입력화면 필요			이동
     * 수정 처리		/book/modify		POST			모든 항목				입력화면 필요			이동
     */



    //    도서 목록
    @GetMapping("/1-1search")
    public void showList(Search search, Criteria criteria, Model model){

//        Criteria criteriaBook = new Criteria(criteria.getPageNum(), 5);

        Criteria criteriaBook;

        if(criteria.getPageNum() == 1){
            criteriaBook = new Criteria(1, 5);
        } else {
            criteriaBook = new Criteria(criteria.getPageNum(), 5);
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



    //    도서 조회
    @GetMapping(value={"read","modify"})
    public void getBook(String isbn, Model model){
        model.addAttribute(bookService.getBook(isbn));
    }

    //    도서 추가
    @PostMapping("write")
    public RedirectView write(BookDTO bookDTO, RedirectAttributes redirectAttributes){
        bookService.write(bookDTO);
        redirectAttributes.addFlashAttribute("isbn", bookDTO.getIsbn());
//        추가후 새로고침을해도 redirect로 인해 list로 가더라도 계속 추가되지않는다.
        return new RedirectView("book/list");
    }
    //    도서 삭제
    @GetMapping("remove")
    public RedirectView remove(String isbn){
        bookService.remove(isbn);
        return new RedirectView("book/list");
    }
    //    도서 수정
    @PostMapping("modify")
    public RedirectView modify(BookDTO bookDTO, RedirectAttributes redirectAttributes){
        bookService.modify(bookDTO);
        redirectAttributes.addAttribute("isbn", bookDTO.getIsbn());
        return new RedirectView("book/read");
    }

}
