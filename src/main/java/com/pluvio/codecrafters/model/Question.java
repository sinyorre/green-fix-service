package com.pluvio.codecrafters.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by salagoz on May, 2022
 */
@Data
public class Question {
    List<String> tags = new ArrayList<>();
    List<Answer> answers = new ArrayList<>();
    private long close_vote_count;
    private boolean is_answered;
    private long view_count;
    private long down_vote_count;
    private long up_vote_count;
    private long accepted_answer_id;
    private long answer_count;
    private long score;
    private long last_activity_date;
    private long creation_date;
    private long question_id;
    private String link;
    private String title;
    private String body;
    private String content_license;
}
