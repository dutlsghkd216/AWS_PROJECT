package com.example.app.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Data
public class Search {
    private String keyword;
    private String type;

    public List<String> getTypes() {
        return new ArrayList<>(Arrays.asList(type.split("")));
    }
}
