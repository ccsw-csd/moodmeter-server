package com.cca.moodmeter.groupAdmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.groupAdmin.model.GroupAdminEntity;
import com.cca.moodmeter.person.model.PersonEntity;

@Service
@Transactional
public class GroupAdminServiceImpl implements GroupAdminService {

    @Autowired
    GroupAdminRepository groupAdminRepository;

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
    public void deleteAllAdminsInGroup(Long id) {
        this.groupAdminRepository.deleteByGroupId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAllAdminsInGroup(Long id, List<PersonEntity> admins) {
        for (PersonEntity admin : admins) {
            GroupAdminEntity groupAdminEntity = new GroupAdminEntity();
            groupAdminEntity.setGroupId(id);
            groupAdminEntity.setPersonId(admin.getId());

            this.groupAdminRepository.save(groupAdminEntity);
        }
    }

}
