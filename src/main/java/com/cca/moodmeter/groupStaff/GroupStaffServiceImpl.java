package com.cca.moodmeter.groupStaff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.groupStaff.model.GroupStaffEntity;
import com.cca.moodmeter.person.model.PersonEntity;

@Service
@Transactional
public class GroupStaffServiceImpl implements GroupStaffService {

    @Autowired
    GroupStaffRepository groupStaffRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonEntity> findAllMembersInGroup(Long groupId) {
        List<PersonEntity> staffInGroup;

        staffInGroup = groupStaffRepository.findMemberByGroupId(groupId);

        return staffInGroup;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllMembersInGroup(Long id) {
        this.groupStaffRepository.deleteByGroupId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAllMembersInGroup(Long id, List<PersonEntity> staff) {
        for (PersonEntity staffMember : staff) {
            GroupStaffEntity groupStaffEntity = new GroupStaffEntity();
            groupStaffEntity.setGroupId(id);
            groupStaffEntity.setPersonId(staffMember.getId());

            this.groupStaffRepository.save(groupStaffEntity);
        }
    }

}
