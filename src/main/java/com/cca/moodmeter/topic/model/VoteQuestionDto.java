package com.cca.moodmeter.topic.model;

public class VoteQuestionDto {

    private Long id;

    private Object answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getAnswers() {
        return answers;
    }

    public void setAnswers(Object answers) {
        this.answers = answers;
    }
}
