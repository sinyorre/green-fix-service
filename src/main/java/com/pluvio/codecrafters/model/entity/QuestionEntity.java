package com.pluvio.codecrafters.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by salagoz on May, 2022
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ElementCollection
    List<String> tags = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<AnswerEntity> answers = new ArrayList<>();

    private long close_vote_count;
    private boolean is_answered;
    private long view_count;
    private long favorite_count;
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
    @Column(columnDefinition = "TEXT", length = 20000)
    private String body;
    private String content_license;
}
