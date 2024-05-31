package com.cca.moodmeter.topic.model;

import java.util.Date;

public interface TopicDashboardItem {

    Long getId();

    String getTitle();

    String getUpdateDate();

    String getBackground();

    String getAuthor();

    Integer getVoted();

    Integer getVotes();

    Date getVotedDate();

    Date getCloseDate();

    default boolean getCanVote() {
        return getVoted() == 0 && getCloseDate().after(new Date());
    }

}
