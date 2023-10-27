package com.example.app.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_ANNOUNCEMENT") // 데이터베이스 테이블 이름 지정
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AN_ID")
    private Long anId; // 게시물 ID (Primary Key)

    @Column(name = "AN_TITLE")
    private String anTitle; // 게시물 제목

    @Column(name = "USER_ID")
    private String userId; // 작성자 ID

    @Column(name = "AN_CONTENT")
    private String anContent; // 게시물 내용

    @Column(name = "AN_REGISTER_DATE")
    private String anRegisterDate; // 게시물 등록일

    @Column(name = "AN_UPDATE_DATE")
    private String anUpdateDate; // 게시물 수정일

    @Column(name = "AN_VIEW_CNT")
    private Long anViewCnt; // 조회수
}
