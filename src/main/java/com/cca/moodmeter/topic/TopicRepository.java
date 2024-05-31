package com.cca.moodmeter.topic;

import com.cca.moodmeter.topic.model.TopicDashboardItem;
import com.cca.moodmeter.topic.model.TopicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<TopicEntity, Long> {

    @Query("select distinct tgp.topic from TopicGroupEntity tgp where tgp.topic.status = 1 and tgp.group.id in (select gse.group.id from GroupStaffEntity gse where gse.person.id = :personId)")
    List<TopicEntity> findTopicsToVoteByPersonId(Long personId);

    @Query("select distinct tgp.topic from TopicGroupEntity tgp where tgp.topic.status = 1 and tgp.group.id in (select gse.group.id from GroupStaffEntity gse where gse.person.id = :personId)")
    List<TopicEntity> findTopicsVotedByPersonId(Long personId);

    // @formatter:off
    @Query(value =
            "select t.id as id, t.title as title, t.update_date as updateDate, t.background as background, t.close_date as closeDate,"
            + "(select min(concat(p.name, ' ', p.lastname)) from personal.all_person p where p.username = t.creation_username and p.active=1) as author, "
            + "(select count(1) from topic_voted_by tvb where tvb.topic_id = t.id and tvb.person_id = :personId) as voted, "
            + "(select min(tvb.voting_date) from topic_voted_by tvb where tvb.topic_id = t.id and tvb.person_id = :personId) as votedDate, "
            + "(select count(1) from topic_voted_by tvb where tvb.topic_id = t.id) as votes "
            + "from topic t where t.id in ("
            + "    select t.id from topic t join topic_group tg on tg.topic_id = t.id join group_member gm on tg.group_id = gm.group_id and person_id = :personId where t.status = 1 "
            + ")"
            , nativeQuery = true)
    // @formatter:on
    List<TopicDashboardItem> findTopicsByPersonId(Long personId);

    // @formatter:off
    @Query(value =
            "select t.id as id, t.title as title, t.update_date as updateDate, t.background as background, t.close_date as closeDate,"
                    + "(select min(concat(p.name, ' ', p.lastname)) from personal.all_person p where p.username = t.creation_username and p.active=1) as author, "
                    + "(select count(1) from topic_voted_by tvb where tvb.topic_id = t.id and tvb.person_id = :personId) as voted, "
                    + "(select min(tvb.voting_date) from topic_voted_by tvb where tvb.topic_id = t.id and tvb.person_id = :personId) as votedDate, "
                    + "(select count(1) from topic_voted_by tvb where tvb.topic_id = t.id) as votes "
                    + "from topic t where t.id = :topicId"
            , nativeQuery = true)
    // @formatter:on
    TopicDashboardItem getOneItem(Long personId, Long topicId);
}
