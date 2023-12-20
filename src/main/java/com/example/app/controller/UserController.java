package com.example.app.controller;

import com.example.app.auth.PrincipalDetails;
import com.example.app.domain.dto.Search;
import com.example.app.domain.dto.UserDTO;
import com.example.app.domain.paging.Criteria;
import com.example.app.domain.paging.PageMakerDTO;
import com.example.app.service.RegisterMail;
import com.example.app.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RegisterMail registerMail;

    //    @GetMapping("/all")
    //    public List<UserDTO> getAllUsers(){
    //        return userService.getAllUser();
    //    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<List<UserDTO>> getUserInfo(@RequestParam("userId") String userId) {
        List<UserDTO> userDTO = userService.getUserDetail(userId);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admin/adminsetting")
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

    //    회원가입 이메일 인증
    @PostMapping("/security/mailconfirm")
    @ResponseBody
    public String mailConfirm(@RequestBody Map<String, String> body, HttpSession session) throws Exception {
        String email = body.get("email");
        String code = registerMail.sendSimpleMessage(email);
        session.setAttribute("code", code); //세션에 코드값 저장
        System.out.println("인증코드 : " + code);
        return code;
    }

    @PostMapping("/security/checkCode")
    @ResponseBody
    boolean checkCode(@RequestBody Map<String, String> body, HttpSession session) {
        String inputCode = body.get("code");
        String sessionCode = (String) session.getAttribute("code"); // session에서 code 가져오기

        if (inputCode.equals(sessionCode)) { // 사용자가 입력한 코드와 session의 코드가 같은지 확인
            return true;
        } else {
            return false;
        }
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

    //아이디중복체크
    @PostMapping("/idCheck")
    @ResponseBody
    public Map<String, String> checkUserId(@RequestBody Map<String, String> userIdMap) {
        Map<String, String> result = new HashMap<>();
        String userId = userIdMap.get("userId");
        if (userService.isUserIdExist(userId)) {
            result.put("message", "이미 사용중인 아이디입니다.");
            result.put("status", "fail");
        } else {
            result.put("message", "사용 가능한 아이디입니다.");
            result.put("status", "success");
        }
        return result;
    }

    // ㄱ관리자페이지에서 회원 삭제
    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        try {
            userService.delete(userId);
            return ResponseEntity.ok("회원이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }

    // 관리자 페이지에서 회원 수정
    @PostMapping("/admin/saveUserData")
    public ResponseEntity<String> saveUserData(@RequestBody UserDTO userData) throws JsonProcessingException {
        try {
            userService.updateEmailAndRole(userData);
            String jsonMessage = new ObjectMapper().writeValueAsString(Collections.singletonMap("message", "User data saved successfully"));
            return ResponseEntity.ok(jsonMessage);
        } catch (Exception e) {
            String jsonError = new ObjectMapper().writeValueAsString(Collections.singletonMap("error", "Error saving user data"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonError);
        }
    }
//        @PostMapping("/admin/saveUserData")
//        public RedirectView saveUserData(UserDTO userData) {
//                userService.updateEmailAndRole(userData);
//                return new RedirectView("admin/adminsetting");
//        }


}