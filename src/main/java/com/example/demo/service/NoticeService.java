package com.example.demo.service;

import com.example.demo.Entity.Notice;
import com.example.demo.dto.noticeDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface NoticeService {

    //글쓰기
    noticeDTO postfunc(noticeDTO dto);

    //게시물전달(Size:10)
    Page<Notice> getBoardList(int page, int size);

    //게시물하나 받아오기
    Notice getBoard(Long num);

    //조회수 증가 메서드
    void Upcount(Long id);

    //게시물 수정
    void Updateboard(noticeDTO dto);

    //게시물 삭제
    void deleteboard(Long num);


    //DTO->Entity
    default Notice dtoToEntity(noticeDTO dto){
        LocalDate now = LocalDate.now();
        //빌더패턴
        Notice notice = Notice.builder()
                .content(dto.getContent())
                .subject(dto.getSubject())
                .id(dto.getId())
                .password(dto.getPassword())
                .regdate(now.toString())
                .count(0)
                .build();

        return notice;

    }

}