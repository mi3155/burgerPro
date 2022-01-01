package com.example.demo.service;


import com.example.demo.Entity.Burger;
import com.example.demo.dto.SignupDTO;

public interface BurgerService {

    Burger burgerSignup(SignupDTO dto);


    default Burger dtoToEntity(SignupDTO dto){
        Burger Entity = new Burger();
        Entity.setId(dto.getId());
        Entity.setPassword(dto.getPassword());
        Entity.setAddress(dto.getAddress());
        Entity.setPhone(dto.getPhone());
        Entity.setAuth(dto.getAuth());

        return Entity;
    }
}
