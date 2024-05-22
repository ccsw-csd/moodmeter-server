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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        TopicEntity topic = new TopicEntity();
        topic.setTitle(data.getTopic().getTitle());
        topic.setStatus(data.getTopic().getStatus());
        topic.setUpdateDate(data.getTopic().getUpdateDate());
        topic.setUpdateUsername(UserUtils.getUserDetails().getUsername());
        topic.setCreationDate(data.getTopic().getCreationDate());

        if (data.getTopic().getId() != null) {
            topic.setId(data.getTopic().getId());
            topic.setCreationUsername(data.getTopic().getCreationUsername());
            topic.setVisits(data.getTopic().getVisits());
        } else {
            topic.setCreationUsername(UserUtils.getUserDetails().getUsername());
            long visits = 0;
            topic.setVisits(visits);
        }

        this.topicGroupRepository.deleteByTopicId(data.getTopic().getId());

        if (data.getGroups() != null) {
            List<GroupDto> groups = data.getGroups();
            for (GroupDto group : groups) {
                TopicGroupEntity topicGroup = new TopicGroupEntity();
                topicGroup.setTopic(topic);

                topicGroup.setGroup(mapper.map(group, GroupEntity.class));

                this.topicGroupRepository.save(topicGroup);
            }
        }

        if (data.getTopic().getQuestions() != null) {
            List<TopicSetEntity> setList = data.getTopic().getQuestions().stream().map(e -> mapper.map(e, TopicSetEntity.class)).collect(Collectors.toList());
            for (TopicSetEntity set : setList) {
                set.setTopic(topic);

                for (TopicOptionEntity option : set.getOptions()) {
                    option.setSet(set);
                }
            }
            topic.setQuestions(setList);
        }

        this.topicAdminRepository.deleteByTopicId(data.getTopic().getId());

        if (data.getAdmins() != null) {
            List<TopicAdminDto> admins = data.getAdmins();
            for (TopicAdminDto admin : admins) {
                TopicAdminEntity topicAdmin = new TopicAdminEntity();
                topicAdmin.setTopic(topic);
                topicAdmin.setPerson(mapper.map(admin.getPerson(), PersonEntity.class));

                this.topicAdminRepository.save(topicAdmin);
            }
        }

        return this.topicRepository.save(topic);
    }

    @Override
    public TopicEntity addVisit(Long id) {
        Optional<TopicEntity> topicOptional = this.topicRepository.findById(id);

        if (topicOptional.isPresent()) {
            TopicEntity topic = topicOptional.get();
            Long visits = topic.getVisits() + 1;
            topic.setVisits(visits);
            try {
                return this.topicRepository.save(topic);
            } catch (Exception e) {
                throw new RuntimeException("Failed to save topic", e);
            }
        } else {
            throw new EntityNotFoundException("Topic with ID " + id + " not found");
        }
    }

    @Override
    public void saveVote(TopicDto data) {

        if (data.getStatus() == 1) {
            String user = UserUtils.getUserDetails().getUsername();
            PersonEntity person = personRepository.findByUsernameAndActiveTrue(user);
            Long personId = person.getId();

            boolean hasVoted = topicVotedByRepository.existsByTopicIdAndPersonId(data.getId(), personId);
            if (!hasVoted) {
                // Recorro todas las preguntas de la encuesta
                for (TopicSetSimpleDto question : data.getQuestions()) {
                    // Recorro las opciones votadas para cada pregunta
                    for (Long optionId : question.getAnswers()) {
                        // Recorro las opciones para cada pregunta y sumo uno a los votos que
                        // correspondan
                        for (TopicOptionSimpleDto option : question.getOptions()) {
                            if (option.getId() == optionId) {
                                // option.setVotes(option.getVotes() + 1);
                                Optional<TopicOptionEntity> optionOptional = this.topicOptionRepository.findById(optionId);
                                if (optionOptional.isPresent()) {
                                    TopicOptionEntity topicOption = optionOptional.get();
                                    topicOption.setVotes(topicOption.getVotes() + 1);
                                    this.topicOptionRepository.save(topicOption);
                                }
                            }
                        }
                    }
                }

                TopicEntity topic = new TopicEntity();

                topic.setId(data.getId());

                TopicVotedByEntity voted = new TopicVotedByEntity();
                voted.setPerson(person);
                voted.setTopic(topic);
                this.topicVotedByRepository.save(voted);

            }

        }

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

}
