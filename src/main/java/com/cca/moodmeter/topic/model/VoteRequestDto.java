package com.cca.moodmeter.topic.model;

import java.util.List;

public class VoteRequestDto {

    List<VoteQuestionDto> questions;

    public List<VoteQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<VoteQuestionDto> questions) {
        this.questions = questions;
    }
}
