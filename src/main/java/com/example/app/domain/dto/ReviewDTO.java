package com.example.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReviewDTO {
    private Long revId;
    private String userId;
    private String revTitle;
    private String revContent;
    private String revRegisterDate;
    private String revUpdateDate;

    // 조회수
    private int revViewCnt;
}
