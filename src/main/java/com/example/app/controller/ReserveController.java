package com.example.app.controller;

import com.example.app.domain.dto.ReserveDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.dto.WishDTO;
import com.example.app.domain.paging.Criteria;
import com.example.app.domain.paging.PageMakerDTO;
import com.example.app.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;

    private static DateTimeFormatter localDateFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // getTodayPlus(int) 파라미터로 	0 넣으면 오늘, 1 넣으면 내일
    public static String getTodayPlus(int plus) {
        LocalDate now = LocalDate.now(); 	// 현재시간
        now = now.plusDays(plus);		// 현재시간 + "plus"일
        return now.format(localDateFormat);	// yyyyMMdd 포매팅
    }


    //도서 예약 조회
    public ReserveDTO selectByReserveSeq(Long reserveSeq){
        return reserveService.selectByReserveSeq(reserveSeq);
    }

    //도서 예약 전체 조회
    @GetMapping("/admin/5-6booksetting")
    public void selectAll(Criteria criteria, Search search, String reserveDate, Model model){
        System.out.println("reserveDate:" + reserveDate);
        if (reserveDate == null) {
            reserveDate = getTodayPlus(1);
        }
        List<ReserveDTO> reserveList = reserveService.selectAll(criteria, search, reserveDate);
        model.addAttribute("reserveList", reserveList);
        long total = reserveService.selectCountAll(search, reserveDate);
        System.out.println("Count : " + total);

        PageMakerDTO pageMaker = new PageMakerDTO(criteria, total);

        model.addAttribute("totalPostCount", total);
        model.addAttribute("pageMaker", pageMaker);
        System.out.println("reserveList:" + reserveList);
    }

    //도서 예약(insert)
    @PostMapping("/books/reserve")
    public String insert(ReserveDTO reserveDTO, RedirectAttributes redirectAttributes, Principal principal){
        String username = principal.getName();
        reserveDTO.setUserId(username);
        // 1. 중복 예약 확인
        if (reserveService.isDuplicateReservation(reserveDTO)) {
            redirectAttributes.addFlashAttribute("message", "이미 예약된 도서입니다.");
            return "redirect:1-1search";
        }

        // 2. 최대 5개 예약 확인
        if (reserveService.getReservationCountByUsername(username) >= 5) {
            redirectAttributes.addFlashAttribute("message", "최대 5개까지 예약 가능합니다.");
            return "redirect:1-1search";
        }
        // 로그 추가
        System.out.println("Received reservation request: " + reserveDTO);
        reserveService.insert(reserveDTO);
        return "redirect:1-1search";
    }

    //도서 예약 취소(delete)
    public void delete(Long reserveSeq){
        reserveService.delete(reserveSeq);
    }

    @GetMapping("/mypage/myreservelist")/*조회랑 수정*/
    public @ResponseBody List<ReserveDTO> getReserveList(String userId, Principal principal){
        String username = principal.getName();
        System.out.println(username);
        List<ReserveDTO> reserveDTO = reserveService.getListUserId(username);
        System.out.println(reserveDTO);
        return reserveDTO;
    }


}
