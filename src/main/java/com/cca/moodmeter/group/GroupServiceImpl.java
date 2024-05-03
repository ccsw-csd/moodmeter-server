package com.cca.moodmeter.group;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.config.security.UserUtils;
import com.cca.moodmeter.group.model.GroupEditDto;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.groupAdmin.GroupAdminService;
import com.cca.moodmeter.groupStaff.GroupStaffService;
import com.cca.moodmeter.person.PersonService;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupEntity> findAll() {

        return (List<GroupEntity>) this.groupRepository.findAll();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonEntity> searchByNameLastnameUsername(String nameLastnameUsername) throws Exception {

        return (List<PersonEntity>) personService.findByFilter(nameLastnameUsername);
    }

    public GroupEditDto findGroupEdit(Long id) {
        GroupEditDto groupEdit = new GroupEditDto();

        GroupEntity group;
        List<PersonEntity> collaborators;
        List<PersonEntity> admins;

        group = this.groupRepository.findById(id).orElse(null);

        admins = groupAdminService.findAllAdminsInGroup(id);
        collaborators = groupStaffService.findAllMembersInGroup(id);

        groupEdit.setGroup(group);
        groupEdit.setCollaborators(collaborators);
        groupEdit.setAdmin(admins);

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
            groupAdminService.deleteAllAdminsInGroup(group.getId());
            groupStaffService.deleteAllMembersInGroup(group.getId());
        }
        groupAdminService.addAllAdminsInGroup(group.getId(), groupEditDto.getAdmins());
        groupStaffService.addAllMembersInGroup(group.getId(), groupEditDto.getCollaborators());
    }

}
