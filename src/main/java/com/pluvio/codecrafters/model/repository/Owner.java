package com.pluvio.codecrafters.model.repository;

import lombok.Data;

@Data
public class Owner {
    private long account_id;
    private long reputation;
    private long user_id;
    private String user_type;
    private String profile_image;
    private String display_name;
    private String link;
}
