package com.example.demo.repository;

import com.example.demo.Entity.Burger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BurgerRepository extends JpaRepository<Burger,Long> {
    //id을 조건절로 계정찾기
    Optional<Burger> findById(String id);
}
