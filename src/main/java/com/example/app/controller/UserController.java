package com.example.app.controller;

import com.example.app.auth.PrincipalDetails;
import com.example.app.domain.dto.BookDTO;
import com.example.app.domain.dto.Search;
import com.example.app.domain.dto.UserDTO;
import com.example.app.domain.paging.Criteria;
import com.example.app.domain.paging.PageMakerDTO;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

//    @GetMapping("/all")
//    public List<UserDTO> getAllUsers(){
//        return userService.getAllUser();
//    }

    @GetMapping("admin/adminsetting")
    public String goAdminSetting(Search search, Criteria criteria, Model model){
        List<UserDTO> list = userService.getAllUser(criteria, search);
        model.addAttribute("listUser",list);
        Long total = userService.getTotal(search);

        PageMakerDTO pageMaker = new PageMakerDTO(criteria, total);

        Long totalPostCount = userService.getTotal(search);
        model.addAttribute("totalPostCount", totalPostCount);
        model.addAttribute("pageMaker", pageMaker);
        System.out.println("listUser:" + list);
        return "admin/5-3adminsetting";
    }

    @GetMapping(value={"/mypage/read","/mypage/5-1myInfo"})
    public UserDTO getUser(Authentication authentication,Principal principal, Model model){
        System.out.println(principal);
        String username = principal.getName();
        UserDTO userDTO = userService.getUser(username);
        model.addAttribute("userDTO", userDTO);
        return userService.getUser(username);
    }

    //    회원가입 창으로 이동
    @GetMapping("/security/register")
    public String registerForm(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "security/signup";
    }

    @PostMapping("/security/register")
    public RedirectView register(UserDTO userDTO, RedirectAttributes redirectAttributes){
        String prefix = userDTO.getEmailPrefix();
        String dns = userDTO.getEmailDns();
        String email = prefix + "@" + dns;
        userDTO.setUserEmail(email);
        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
        userDTO.setUserPw(encodedPassword);
        userService.write(userDTO);
        System.out.println(userDTO);
        redirectAttributes.addFlashAttribute("uId", userDTO.getUserId());
//        추가후 새로고침을해도 redirect로 인해 list로 가더라도 계속 추가되지않는다.
        return new RedirectView("security/login");
    }

    @PostMapping("/mypage/modify")
    public RedirectView modify(Authentication authentication, RedirectAttributes redirectAttributes, UserDTO userDTO){
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        String prefix = userDTO.getEmailPrefix();
        String dns = userDTO.getEmailDns();
        String email = prefix + "@" + dns;
        userDTO.setUserEmail(email);
        userDTO.setUserRole(principalDetails.getDto().getUserRole());
        userDTO.setUserRegisterDate(principalDetails.getDto().getUserRegisterDate());
        userService.modify(userDTO);
        principalDetails.setDto(userDTO);
        redirectAttributes.addAttribute("userId", userDTO.getUserId());
        return new RedirectView("5-1myInfo");
    }

    @GetMapping("/mypage/changepw")
    public String goChangePW(Model model){
        return "mypage/5-1-2passwordChange";
    }



    @PostMapping("/mypage/changepw")
    public RedirectView changePW(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 Principal principal,
                                 Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = principal.getName();
        System.out.println("USERNAME : " + username);
        String storedPassword = userService.getUserPW(username);
        if (passwordEncoder.matches(currentPassword,storedPassword)) {
            if (newPassword.equals(confirmPassword)) {
                String encodedPassword = passwordEncoder.encode(newPassword);
                userService.updatePW(username, encodedPassword);
                model.addAttribute("successMessage", "비밀번호 변경이 성공적으로 완료되었습니다.");
            } else {
                model.addAttribute("errorMessage", "새 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            }
        } else {
            model.addAttribute("errorMessage", "현재 비밀번호가 올바르지 않습니다.");
        }
        return new RedirectView("/main"); // 성공 또는 에러 메시지와 함께 동일한 템플릿으로 돌아갑니다.
    }

    @GetMapping("/security/checkpw")
    public UserDTO goCheckPw(Model model, Principal principal) {
        model.addAttribute("principal",principal);
        return userService.getUser(principal.getName())
;    }
    @PostMapping("/security/checkpw")
    public String checkPW(@RequestParam("currentPassword") String currentPassword, Principal principal,Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = principal.getName();
        System.out.println("USERNAME : " + username);
        UserDTO userDTO = userService.getUser(username);
        String storedPassword = userService.getUserPW(username);
        if (passwordEncoder.matches(currentPassword,storedPassword)) {
            model.addAttribute("userDTO",userDTO);
            model.addAttribute("successMessage", "비밀번호 인증에 성공하셨습니다.");
            return "mypage/5-1myInfo";
        } else {
            model.addAttribute("errorMessage", "현재 비밀번호가 올바르지 않습니다.");
            return "checkpw";

        }
    }



    @GetMapping("/mypage/remove")
    public RedirectView remove(Principal principal){
        String userId = principal.getName();
        userService.delete(userId);
        return new RedirectView("/main");
    }

    //    로그인 창으로 이동
    @GetMapping("/security/login")
    public void loginForm(){
    }

}