package com.example.app.auth;

import com.example.app.auth.loginHandler.CustomAuthenticationSuccessHandler;
import com.example.app.domain.dto.UserDTO;
import com.example.app.mapper.UserMapper;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username == null || username.isEmpty()){
            throw new UsernameNotFoundException("Username is empty");
        }

        UserDTO userDTO = userService.getUser(username);

        if( userDTO == null){
            throw new UsernameNotFoundException("Could not find user");
        }
        PrincipalDetails principal = new PrincipalDetails();
        principal.setDto(userDTO);

        System.out.println("Loaded user details: " + principal);

        return principal;

    }

}

