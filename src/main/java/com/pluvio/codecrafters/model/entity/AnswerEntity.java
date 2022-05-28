package com.pluvio.codecrafters.model.entity;

import com.pluvio.codecrafters.model.repository.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private long answer_id;
    private long id;
    private long last_activity_date;
    private long creation_date;
    private long question_id;
    private String content_license;
    @Column(columnDefinition = "TEXT", length = 20000)
    private String body;
    private String ownerName;
    private String ownerLink;
    private boolean is_accepted;
}
