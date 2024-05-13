package com.cca.moodmeter.group.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.group.database.GroupAdminRepository;
import com.cca.moodmeter.group.model.GroupAdminEntity;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.person.PersonService;
import com.cca.moodmeter.person.model.PersonDto;
import com.cca.moodmeter.person.model.PersonEntity;

@Service
@Transactional
public class GroupAdminServiceImpl implements GroupAdminService {

    @Autowired
    GroupAdminRepository groupAdminRepository;

    @Autowired
    GroupService groupService;

    @Autowired
    PersonService personService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonEntity> findAllAdminsInGroup(Long groupId) {
        List<PersonEntity> adminsInGroup;

        adminsInGroup = groupAdminRepository.findAdminByGroupId(groupId);

        return adminsInGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllAdminsInGroup(GroupEntity group) {
        this.groupAdminRepository.deleteAllByGroupId(group.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAllAdminsInGroup(Long id, List<PersonDto> admins) {
        GroupEntity group = groupService.findGroupById(id);
        for (PersonDto admin : admins) {
            GroupAdminEntity groupAdminEntity = new GroupAdminEntity();
            groupAdminEntity.setGroup(group);
            PersonEntity person = new PersonEntity();
            if (admin.getId() == null) {
                person = personService.getPersonByUsername(admin.getUsername());
            } else {
                BeanUtils.copyProperties(admin, person);
            }
            groupAdminEntity.setPerson(person);
            this.groupAdminRepository.save(groupAdminEntity);
        }
    }

}
