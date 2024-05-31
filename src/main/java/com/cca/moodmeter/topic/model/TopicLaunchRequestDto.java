package com.cca.moodmeter.topic.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TopicLaunchRequestDto {

    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
