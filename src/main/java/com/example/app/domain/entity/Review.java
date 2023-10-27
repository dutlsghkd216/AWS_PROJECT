package com.example.app.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_REVIEW") // 데이터베이스 테이블 이름 지정
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REV_ID")
    private Long revId; // 게시물 ID (Primary Key)

    @Column(name = "REV_TITLE")
    private String revTitle; // 게시물 제목

    @Column(name = "USER_ID")
    private String userId; // 작성자 ID

    @Column(name = "REV_CONTENT")
    private String revContent; // 게시물 내용

    @Column(name = "REV_REGISTER_DATE")
    private String revRegisterDate; // 게시물 등록일

    @Column(name = "REV_UPDATE_DATE")
    private String revUpdateDate; // 게시물 수정일

    @Column(name = "REV_VIEW_CNT")
    private Long revViewCnt; // 조회수
}
