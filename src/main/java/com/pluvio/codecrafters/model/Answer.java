package com.pluvio.codecrafters.model;

import com.pluvio.codecrafters.model.repository.Owner;
import lombok.Data;

/**
 * Created by salagoz on May, 2022
 */
@Data
public class Answer {
    Owner owner;
    private boolean is_accepted;
    private long last_activity_date;
    private long creation_date;
    private long answer_id;
    private long question_id;
    private long down_vote_count;
    private long up_vote_count;
    private long score;
    private long last_edit_date;
    private String content_license;
    private String body;
}
