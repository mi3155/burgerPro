package com.example.demo.service;

import com.example.demo.Entity.Burger;
import com.example.demo.dto.SignupDTO;
import com.example.demo.repository.BurgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BurgerServiceImp implements BurgerService, UserDetailsService {

    @Autowired
    private final BurgerRepository Burgerrepo;

    @Override
    public Burger burgerSignup(SignupDTO dto) {
        Burger burger = dtoToEntity(dto); // DTO -> Entity
        //System.out.println(burger);
        Burgerrepo.save(burger); // DB에 Insert

        return burger; // 컨트롤러에 전달
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Optional<Burger> result = Burgerrepo.findById(id);
        if(result.isPresent())
            return result.get();
        else
            throw new UsernameNotFoundException("Check Email");

    }

    public Burger save(SignupDTO dto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dto.setPassword(encoder.encode(dto.getPassword()));

        //DTO -> Entity
//        Burger burger = Burger.builder()
//                        .id(dto.getId())
//                                .auth(dto.getAuth())
//                                        .password(dto.getPassword())
//                                                .address(dto.getAddress())
//                                                        .phone(dto.getPhone())
//                                                                .build();
        Burger burger = dtoToEntity(dto);
        Burgerrepo.save(burger);

        return burger;
    }

}
