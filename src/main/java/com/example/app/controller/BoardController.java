package com.example.app.controller;

import com.example.app.domain.dto.BoardDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.paging.Criteria;
import com.example.app.domain.paging.PageMakerDTO;
import com.example.app.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    //    // 게시글 조회
//    @GetMapping(value={"/3-4post","/3-5modify"})
//    public void getBoard(Search search, Criteria criteria, Long anId, Model model) {
//        model.addAttribute(boardService.getBoard(anId));
//    }
    // 게시글 조회
    @GetMapping("/3-4post")
    public String getBoard(Search search, Criteria criteria, Long anId, Model model) {
        // 조회수 증가를 포함한 게시물 조회
        BoardDTO boardDTO = boardService.getBoard(anId);
        model.addAttribute("board", boardDTO);

        return "boards/3-4post";
    }

    // 게시글 수정 페이지
    @GetMapping("/3-5modify")
    public String getModifyPage(Search search, Criteria criteria, Long anId, Model model) {
        // 게시물 정보를 가져옴
        BoardDTO boardDTO = boardService.getBoard(anId);
        model.addAttribute("board", boardDTO);

        return "boards/3-5modify";
    }

    // 게시글 목록
    @GetMapping("/notice")
    public void showList(Search search,  Criteria criteria, Model model){
        System.out.println("GET /boards/notice..."+search);
        List<BoardDTO> list = boardService.getList(criteria, search);
        model.addAttribute("list", list);
        Long total = boardService.getTotal(search);
        System.out.println("Count : " + total);

        PageMakerDTO pageMaker = new PageMakerDTO(criteria, total);

        Long totalPostCount = boardService.getTotal(search);
        model.addAttribute("totalPostCount", totalPostCount);
        model.addAttribute("pageMaker", pageMaker);
    }
    // 게시글 추가
    @GetMapping("/write")
    public String showwrite(Model model, Principal principal){
        model.addAttribute(new BoardDTO());
        return "boards/3-3write";
    }

    @PostMapping("/write")
    public RedirectView write(BoardDTO boardDTO, RedirectAttributes redirectAttributes,Principal principal){
        String username = principal.getName();
        boardDTO.setUserId(username);
        boardService.write(boardDTO);
        redirectAttributes.addFlashAttribute("anId", boardDTO.getAnId());
        return new RedirectView("/boards/notice");
    }

    // 게시글 삭제

    @GetMapping("/remove")
    public RedirectView remove(Long anId){
        boardService.delete(anId);
        return new RedirectView("notice");
    }
    // 게시글 수정
    @PostMapping("/3-5modify")
    public RedirectView modify(Criteria criteria, Search search, BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
        boardService.modify(boardDTO);
        redirectAttributes.addAttribute("anId", boardDTO.getAnId());
        redirectAttributes.addFlashAttribute(criteria);
        redirectAttributes.addFlashAttribute(search);
        return  new RedirectView("3-4post");
    }
}
