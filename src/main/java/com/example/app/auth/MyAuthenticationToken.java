package com.example.app.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class MyAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final Object principal;
    private Object credentials;

    public MyAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
        this.principal = principal;
        this.credentials = credentials;
    }

    // 추가적인 필요한 메소드나 속성을 정의할 수 있습니다.

    // 예를 들어, UserDetails 객체를 저장할 수 있습니다.
    public UserDetails getUserDetails() {
        return (UserDetails) this.getPrincipal();
    }

}
