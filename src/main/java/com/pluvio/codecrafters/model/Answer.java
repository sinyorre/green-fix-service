package com.pluvio.codecrafters.model;

import lombok.Data;

/**
 * Created by salagoz on May, 2022
 */
@Data
public class Answer {
    private boolean is_accepted;
    private long last_activity_date;
    private long creation_date;
    private long answer_id;
    private long question_id;
    private String content_license;
    private String body;
}
