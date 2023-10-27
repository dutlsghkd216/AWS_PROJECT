package com.example.app.configuration;


//import com.example.app.auth.PrincipalDetailsService;
import com.example.app.auth.PrincipalDetailsService;
import com.example.app.auth.exceptionhandler.CustomAccessDeniedHandler;
import com.example.app.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.app.auth.loginHandler.CustomAuthenticationSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.security.Principal;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception{


        http
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/image/**").permitAll()
//                .antMatchers("/**").permitAll()
//                .antMatchers("/", "/member/**", "/main", "/member/join","/member/login").permitAll()
//                .antMatchers("/admintest/**","/mainImage/**").permitAll()
//                .antMatchers("/movielist/**").permitAll()
//                .antMatchers("/reservation/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/boards/notice","/boards/3-4post").permitAll()
                .antMatchers("/security/register").permitAll()
                .antMatchers("/boards/3-3post").hasRole("ADMIN")
                .antMatchers("/books/hopeadd","/books/lend").hasAnyRole("ADMIN","USER")
                .antMatchers("/reviews/write", "/reviews/3-8modify").hasAnyRole("ADMIN","USER")
                .antMatchers("/mypage/**","/security/**").hasAnyRole("ADMIN","USER")

//                .antMatchers("/admin/**").permitAll()
//                .anyRequest().authenticated()
                .and()

                .formLogin()
                    .loginPage("/security/login")
//                    .loginProcessingUrl("/security/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .usernameParameter("userId")
                .passwordParameter("userPw")
                
                .permitAll()
                .and()

//                .exceptionHandling()
//                .accessDeniedHandler(accessDeniedHandler)  // AccessDeniedHandler 등록
//                .and()

                .logout()
                .logoutUrl("/security/logout")
                .logoutSuccessUrl("/main")
                .permitAll();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}