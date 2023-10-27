package com.example.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BoardDTO {
    private Long anId;
    private String anTitle;
    private String userId;
    private String anContent;
    private String anRegisterDate;
    private String anUpdateDate;

    // 조회수
    private int anViewCnt;
}
