package com.cca.moodmeter.topic;

import com.cca.moodmeter.config.security.UserUtils;
import com.cca.moodmeter.group.database.GroupStaffRepository;
import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.group.model.GroupStaffEntity;
import com.cca.moodmeter.person.PersonRepository;
import com.cca.moodmeter.person.model.PersonEntity;
import com.cca.moodmeter.topic.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = false)
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicGroupRepository topicGroupRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TopicVotedByRepository topicVotedByRepository;

    @Autowired
    TopicSetRepository topicSetRepository;

    @Autowired
    TopicOptionRepository topicOptionRepository;

    @Autowired
    TopicAdminRepository topicAdminRepository;

    @Autowired
    GroupStaffRepository groupStaffRepository;

    @Autowired
    ModelMapper mapper;

    @Value("${app.code}")
    private String appCode;

    public TopicEntity get(Long id) {
        Optional<TopicEntity> topicOptional = this.topicRepository.findById(id);

        if (topicOptional.isPresent()) {
            return topicOptional.get();
        } else {
            throw new EntityNotFoundException("Topic with ID " + id + " not found");
        }
    }

    @Override
    public List<TopicEntity> findAll(boolean adminView) {
        if (adminView && UserUtils.isGranted(appCode, "ADMIN")) {
            return (List<TopicEntity>) this.topicRepository.findAll();
        } else {
            String user = UserUtils.getUserDetails().getUsername();
            PersonEntity person = personRepository.findByUsernameAndActiveTrue(user);
            List<TopicAdminEntity> topicAdminList = topicAdminRepository.findAllByPerson(person);
            List<TopicEntity> topics = new ArrayList<>();
            for (TopicAdminEntity topicAdmin : topicAdminList) {
                topics.add(topicAdmin.getTopic());
            }

            return topics;
        }
    }

    @Override
    public TopicEntity save(TopicDetail data) {

        TopicEntity topic;

        if (data.getTopic().getId() != null) {
            topic = get(data.getTopic().getId());

            this.topicGroupRepository.deleteByTopicId(topic.getId());
            this.topicAdminRepository.deleteByTopicId(topic.getId());

        } else {
            topic = new TopicEntity();
            topic.setStatus(0);
            topic.setCreationDate(new Date());
            topic.setCreationUsername(UserUtils.getUserDetails().getUsername());
            topic.setVisits(0L);
            Random rand = new Random();
            topic.setBackground(rand.nextInt(9) + 1);
        }

        topic.setTitle(data.getTopic().getTitle());
        topic.setStatus(data.getTopic().getStatus());
        topic.setUpdateDate(new Date());
        topic.setUpdateUsername(UserUtils.getUserDetails().getUsername());
        topic.getQuestions().clear();

        if (data.getTopic().getQuestions() != null) {
            int orderQuestion = 1;
            for (TopicSetSimpleDto set : data.getTopic().getQuestions()) {

                TopicSetEntity topicSet = mapper.map(set, TopicSetEntity.class);

                topicSet.setTopic(topic);
                topicSet.setOrder(orderQuestion++);

                int orderOption = 1;

                for (TopicOptionEntity option : topicSet.getOptions()) {
                    option.setSet(topicSet);
                    option.setOrder(orderOption++);
                }

                topic.getQuestions().add(topicSet);
            }
        }

        this.topicRepository.save(topic);

        if (data.getGroups() != null) {
            List<GroupDto> groups = data.getGroups();
            for (GroupDto group : groups) {
                TopicGroupEntity topicGroup = new TopicGroupEntity();

                topicGroup.setTopic(topic);
                topicGroup.setGroup(mapper.map(group, GroupEntity.class));

                this.topicGroupRepository.save(topicGroup);
            }
        }

        if (data.getAdmins() != null) {
            List<TopicAdminDto> admins = data.getAdmins();
            for (TopicAdminDto admin : admins) {
                TopicAdminEntity topicAdmin = new TopicAdminEntity();

                topicAdmin.setTopic(topic);
                topicAdmin.setPerson(mapper.map(admin.getPerson(), PersonEntity.class));

                this.topicAdminRepository.save(topicAdmin);
            }
        }

        return topic;
    }

    @Override
    public void launch(Long topicId, Date closeDate) {
        TopicEntity topic = get(topicId);
        topic.setStatus(1);
        topic.setCloseDate(closeDate);
        this.topicRepository.save(topic);
    }

    @Override
    public TopicEntity addVisit(Long id) {
        TopicEntity topic = get(id);

        Long visits = topic.getVisits() + 1;
        topic.setVisits(visits);
        try {
            return this.topicRepository.save(topic);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save topic", e);
        }

    }

    @Override
    public void vote(Long idTopic, VoteRequestDto data) {

        Long personId = UserUtils.getUserDetails().getGlobalId();
        boolean hasVoted = topicVotedByRepository.existsByTopicIdAndPersonId(idTopic, personId);

        if (hasVoted)
            return;

        TopicVotedByEntity voted = new TopicVotedByEntity();
        TopicEntity topic = get(idTopic);
        PersonEntity person = personRepository.findById(personId).orElseThrow();

        voted.setTopic(topic);
        voted.setPerson(person);
        voted.setVotingDate(new Date());

        this.topicVotedByRepository.save(voted);

        List<Long> optionsVoted = extractOptionsVoted(data);
        optionsVoted.forEach(optionId -> {
            TopicOptionEntity option = this.topicOptionRepository.findById(optionId).orElseThrow();
            option.setVotes(option.getVotes() + 1);
            this.topicOptionRepository.save(option);
        });

    }

    private static List<Long> extractOptionsVoted(VoteRequestDto data) {
        List<Long> optionsVoted = new ArrayList<>();

        if (data == null || data.getQuestions() == null || data.getQuestions().isEmpty())
            return optionsVoted;

        data.getQuestions().forEach(question -> {

            Object answers = question.getAnswers();
            if (answers != null) {

                if (answers instanceof Integer) {
                    Integer answer = (Integer) answers;
                    optionsVoted.add(answer.longValue());
                }

                if (answers instanceof Long) {
                    optionsVoted.add((Long) answers);
                }

                if (answers instanceof List) {
                    if (((List) answers).size() > 0) {

                        if (((List) answers).get(0) instanceof Integer) {
                            List<Integer> answersList = (List<Integer>) answers;
                            optionsVoted.addAll(answersList.stream().map(Long::valueOf).toList());
                        }

                        if (((List) answers).get(0) instanceof Long) {
                            List<Long> answersList = (List<Long>) answers;
                            optionsVoted.addAll(answersList);
                        }
                    }
                }
            }
        });
        return optionsVoted;
    }

    @Override
    public List<TopicEntity> findByGroups() {
        String user = UserUtils.getUserDetails().getUsername();
        PersonEntity person = personRepository.findByUsernameAndActiveTrue(user);
        Long personId = person.getId();

        // Busco todos los grupos a los que pertenece el usuario
        List<GroupStaffEntity> groupMemberList = this.groupStaffRepository.findAllByPersonId(personId);

        List<GroupEntity> groups = new ArrayList<>();
        for (GroupStaffEntity groupMember : groupMemberList) {
            groups.add(groupMember.getGroup());
        }

        // Busco todas las encuestas que tienen asociado algún grupo del usuario
        List<TopicGroupEntity> topicsGroup = this.topicGroupRepository.findAllByGroupIn(groups);

        List<TopicEntity> topics = new ArrayList<>();

        for (TopicGroupEntity topicGroup : topicsGroup) {
            if (!topicAdminRepository.existsByPersonIdAndTopicId(personId, topicGroup.getTopic().getId())) {
                if (!topics.contains(topicGroup.getTopic())) {
                    topics.add(topicGroup.getTopic());
                }
            }
        }

        return topics;
    }

    @Override
    public List<TopicSetEntity> findQuestions(Long topicId) {
        List<TopicSetEntity> questions = this.topicSetRepository.findByTopicId(topicId);
        return questions;
    }

    @Override
    public List<TopicDashboardItem> findAllByUser() {

        Long personId = UserUtils.getUserDetails().getGlobalId();

        return this.topicRepository.findTopicsByPersonId(personId);

    }

    @Override
    public TopicDashboardItem getOneItem(Long id) {

        Long personId = UserUtils.getUserDetails().getGlobalId();

        return this.topicRepository.getOneItem(personId, id);

    }

}
