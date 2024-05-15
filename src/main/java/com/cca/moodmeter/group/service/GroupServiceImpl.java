package com.cca.moodmeter.group.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.config.security.UserUtils;
import com.cca.moodmeter.group.database.GroupRepository;
import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.group.model.GroupEditDto;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.person.PersonService;
import com.cca.moodmeter.person.model.PersonDto;
import com.cca.moodmeter.person.model.PersonEntity;

/**
 * @author mguaitav
 *
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    PersonService personService;

    @Autowired
    GroupAdminService groupAdminService;

    @Autowired
    GroupStaffService groupStaffService;

    @Autowired
    ModelMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupEntity> findAll() {
        System.out.println("Ejecutando findAll");
        return (List<GroupEntity>) this.groupRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public List<GroupEntity> findGroupsByAdminUsername(String username, boolean isAdmin) {
        System.out.println("Ejecutando findGroupsByAdminUsername. isAdmin= " + isAdmin);
        List<GroupEntity> groups;
        if (isAdmin == true) {
            groups = (List<GroupEntity>) groupRepository.findAll();
        } else {
            PersonEntity userInSession = personService.getPersonByUsername(UserUtils.getUserDetails().getUsername());
            groups = groupRepository.getGroupsByAdminId(userInSession.getId());
        }
        return groups;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.groupRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.groupRepository.deleteById(id);
    }

    public GroupEditDto findGroupEdit(Long id) {
        GroupEditDto groupEdit = new GroupEditDto();

        GroupEntity group;
        List<PersonEntity> members;
        List<PersonEntity> admins;

        GroupDto groupDto = new GroupDto();
        List<PersonDto> membersDto = new ArrayList<>();
        List<PersonDto> adminsDto = new ArrayList<>();

        group = this.groupRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(group, groupDto);

        members = groupStaffService.findAllMembersInGroup(group.getId());
        membersDto = members.stream().map(member -> mapper.map(member, PersonDto.class)).collect(Collectors.toList());

        admins = groupAdminService.findAllAdminsInGroup(id);
        adminsDto = admins.stream().map(admin -> mapper.map(admin, PersonDto.class)).collect(Collectors.toList());

        groupEdit.setGroup(groupDto);
        groupEdit.setCollaborators(membersDto);
        groupEdit.setAdmin(adminsDto);

        return groupEdit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, GroupEditDto groupEditDto) {
        GroupEntity group;
        boolean isGroupNew = (id == null) ? true : false;

        if (isGroupNew) {
            group = new GroupEntity();

            group.setCreationDate(LocalDate.now());
            group.setUpdateDate(LocalDate.now());

            group.setCreationUsername(UserUtils.getUserDetails().getUsername());
            group.setUpdateUsername(UserUtils.getUserDetails().getUsername());

        } else {
            group = this.groupRepository.findById(id).orElse(null);

            group.setUpdateDate(LocalDate.now());
            group.setUpdateUsername(UserUtils.getUserDetails().getUsername());
        }
        group.setName(groupEditDto.getGroup().getName());
        this.groupRepository.save(group);

        if (!isGroupNew) {
            groupAdminService.deleteAllAdminsInGroup(group);
            groupStaffService.deleteAllMembersInGroup(group);
        }
        groupAdminService.addAllAdminsInGroup(group.getId(), groupEditDto.getAdmins());
        groupStaffService.addAllMembersInGroup(group.getId(), groupEditDto.getCollaborators());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupEntity findGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

}
