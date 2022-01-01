package com.example.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="tbl_burger")
@AllArgsConstructor
@NoArgsConstructor
@Builder

//UserDetails
//사용자의 정보를 담는 인터페이스
public class Burger implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(unique = true)
    private String id;

    @Column
    private String password;

    @Column
    private String address;

    @Column
    private int phone;

    @Column
    private String auth; // , , 를 기준으로 함

    public Burger(String id, String password, String address, int phone, String auth){
        this.id=id;
        this.password=password;
        this.address=address;
        this.phone=phone;
        this.auth=auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //역할
        Set<GrantedAuthority> roles = new HashSet(); // 계정이 가지는 역할 저장용 SET
        for(String role : auth.split(",")){ // ,를 기준으로 잘라 각각 role에 전달
            //SimpleGrantedAuthority 객체에 초기값을 역할명으로 전달하고
            //Set에 추가
            roles.add(new SimpleGrantedAuthority(role)); // 시큐리티에서 제공하는것인데
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return id;
    }

    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired() { //만료안되었냐
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //안잠겼냐
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //패스워드가 만료안됐냐
        return true;
    }

    @Override
    public boolean isEnabled() { // 사용가능하냐
        return true;
    }


}
