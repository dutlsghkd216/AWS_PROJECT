package com.example.app.auth;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    public MyAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자 인증 로직 수행
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        // 여기서 userDetails로부터 필요한 사용자 정보를 가져올 수 있음
        String userId = userDetails.getUsername();
        String userPw = userDetails.getPassword();
        String userEmail = ((PrincipalDetails) userDetails).getUserEmail();
        String userName = ((PrincipalDetails) userDetails).getActualUserName();
        // 로그인한 사용자 정보를 이용하여 추가 작업 수행

        // 인증에 성공했다고 가정하고 PrincipalDetails를 생성하여 MyAuthenticationToken에 설정
        MyAuthenticationToken myAuthenticationToken = new MyAuthenticationToken((PrincipalDetails) userDetails, userDetails.getPassword());

        return myAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(MyAuthenticationToken.class);
    }
}
