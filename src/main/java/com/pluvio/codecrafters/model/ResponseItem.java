package com.pluvio.codecrafters.model;

import lombok.Data;

import java.util.List;

/**
 * Created by salagoz on May, 2022
 */
@Data
public class ResponseItem {
    List<Question> items;
    private boolean has_more;
    private long quota_max;
    private long quota_remaining;
    private long page;
    private long page_size;
    private long total;
}
