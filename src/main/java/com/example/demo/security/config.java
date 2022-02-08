package com.example.demo.security;

import com.example.demo.service.BurgerService;
import com.example.demo.service.BurgerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // 웹에서 사용하는 설정 파일
@RequiredArgsConstructor
public class config extends WebSecurityConfigurerAdapter{

    @Autowired
    private final BurgerServiceImp service;

    @Bean
    PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(service).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/burger").permitAll()
                .antMatchers("/list.do").hasRole("USER")
                .antMatchers("/post.do").hasRole("ADMIN");
        http.formLogin()

                .loginPage("/login")
                .defaultSuccessUrl("/loginmain"); //로그인후 기본적으로 나올 페이지
        http.logout()
                        .logoutSuccessUrl("/main")
                                .invalidateHttpSession(true);
            http.csrf().disable()
                    //접근이 실패했을 경우
                    .exceptionHandling().accessDeniedPage("/accessfailed");

    }
}


