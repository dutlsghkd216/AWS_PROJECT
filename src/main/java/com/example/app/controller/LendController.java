package com.example.app.controller;

import com.example.app.domain.dto.LendDTO;
import com.example.app.service.BookService;
import com.example.app.service.LendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LendController {
    private final LendService lendService;
    private final BookService bookService;



    @PostMapping("/books/lend")
    public String postLend(LendDTO lendDTO,Principal principal){
        String username = principal.getName();
        lendDTO.setUserId(username);
        lendService.write(lendDTO);
        return "books/1-1search";
    }

    @GetMapping("/mypage/mylist")
    public String getMyList(){
        return "mypage/5-3mylist";
    }

    @GetMapping("/mypage/mylendlist")
    public @ResponseBody List<LendDTO> getLendList(String userId, Principal principal){
        String username = principal.getName();
        System.out.println(username);
        List<LendDTO> lendDTO = lendService.getListUserId(username);
        System.out.println(lendDTO);
        return lendDTO;
    }

    @PostMapping("/mypage/lendextend")
    public @ResponseBody ResponseEntity lendExtend(@RequestParam("isbn") String isbn, String userId, Principal principal){
        String username = principal.getName();
        lendService.extend(isbn,username);
        if (isbn != null && !isbn.isEmpty()) {
            // 연장 작업 수행 로직을 구현하세요. 이 예시에서는 항목이 있는 경우에만 성공으로 처리합니다.
            return new ResponseEntity("연장 완료", HttpStatus.OK);
        } else {
            return new ResponseEntity("연장 실패: 선택된 항목이 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }


}
