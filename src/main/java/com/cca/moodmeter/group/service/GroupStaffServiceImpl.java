package com.cca.moodmeter.group.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.group.database.GroupStaffRepository;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.group.model.GroupStaffEntity;
import com.cca.moodmeter.person.model.PersonDto;
import com.cca.moodmeter.person.model.PersonEntity;

@Service
@Transactional
public class GroupStaffServiceImpl implements GroupStaffService {

    @Autowired
    GroupStaffRepository groupStaffRepository;

    @Autowired
    GroupService groupService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonEntity> findAllMembersInGroup(Long groupId) {

        List<PersonEntity> staffInGroup = groupStaffRepository.findMemberByGroupId(groupId);

        return staffInGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllMembersInGroup(GroupEntity group) {
        this.groupStaffRepository.deleteAllByGroupId(group.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAllMembersInGroup(Long id, List<PersonDto> staff) {
        GroupEntity group = groupService.findGroupById(id);
        for (PersonDto staffMember : staff) {
            GroupStaffEntity groupStaffEntity = new GroupStaffEntity();
            groupStaffEntity.setGroup(group);

            PersonEntity person = new PersonEntity();
            BeanUtils.copyProperties(staffMember, person);

            groupStaffEntity.setPerson(person);

            this.groupStaffRepository.save(groupStaffEntity);
        }
    }

}
