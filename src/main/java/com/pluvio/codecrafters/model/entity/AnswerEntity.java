package com.pluvio.codecrafters.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by salagoz on May, 2022
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntity {
    @Id
    @GeneratedValue
    private long id;
    private boolean is_accepted;
    private long last_activity_date;
    private long creation_date;
    private long answer_id;
    private long question_id;
    private String content_license;
    @Column(columnDefinition="TEXT", length = 20000)
    private String body;
}
