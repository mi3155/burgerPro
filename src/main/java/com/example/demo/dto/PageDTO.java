package com.example.demo.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO {
    private int nowPage;
    private int nowBlock;
    private int pageStart;
    private int pageEnd;
}
