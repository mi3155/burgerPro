package com.example.demo.service;

import com.example.demo.Entity.Notice;
import com.example.demo.dto.noticeDTO;
import com.example.demo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImp implements NoticeService{

    @Autowired
    private final NoticeRepository noticerepo;

    @Override //글쓰기
    public noticeDTO postfunc(noticeDTO dto) {
        LocalDateTime now = LocalDateTime.now();
        Notice notice = dtoToEntity(dto);
        notice.setRegdate(now.toString());
        noticerepo.save(notice);
        return dto;

    }

    @Override //게시물전달(size:10)
    public Page<Notice> getBoardList(int page, int size) {
        //num열을 기준으로 내림차순 정렬
        Sort sort1 = Sort.by("num").descending();
        //페이지당 게시물의 개수
        Pageable pageable = PageRequest.of(page,size,sort1);
        //게시물 가져오기
        Page<Notice> list = noticerepo.findAll(pageable);
        return list;
    }

    @Override //게시물 하나 받아오기
    public Notice getBoard(Long num) {
        Optional<Notice> notice = noticerepo.findById(num);
        return notice.get();
    }

    @Override // 조회수 증가 메서드
    public void Upcount(Long id) {
        Optional<Notice> notice = noticerepo.findById(id);
        notice.get().setCount(notice.get().getCount()+1);
        noticerepo.save(notice.get());
    }

    @Override // 게시물 수정
    public void Updateboard(noticeDTO dto) {
        LocalDateTime now = LocalDateTime.now();
        Optional<Notice> notice = noticerepo.findById(dto.getNum());
        notice.get().setId(dto.getId());
        notice.get().setContent(dto.getContent());
        notice.get().setSubject(dto.getSubject());
        notice.get().setRegdate(now.toString());
        noticerepo.save(notice.get());
    }

    @Override //게시물 삭제
    public void deleteboard(Long num) {
        noticerepo.deleteById(num);
    }
}
