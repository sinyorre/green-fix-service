package com.pluvio.codecrafters.model.dto;

import com.pluvio.codecrafters.model.entity.QuestionEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseQuestions {
    long totalElements;
    int totalPages;
    List<QuestionEntity> questions;
}
