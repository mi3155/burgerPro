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
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("$2a$10$vy81zDvf1InasWNOSiOYbeY6JLE3zSGz2jC.hgh6wNEwj9B/sgtl6")
//                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/burger").permitAll()
                .antMatchers("/list.do").hasRole("USER")
                .antMatchers("/post.do").hasRole("ADMIN");
                //.anyRequest().authenticated(); // 어떤주소를 입력해도 login으로 넘어감
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/loginburger"); //로그인후 기본적으로 나올 페이지
        http.logout()
                        .logoutSuccessUrl("/burger")
                                .invalidateHttpSession(true);
            http.csrf().disable();
    }
}
