package com.cca.moodmeter.groupStaff;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cca.moodmeter.groupStaff.model.GroupStaffEntity;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupStaffRepository extends CrudRepository<GroupStaffEntity, Long> {
    @Query("SELECT p FROM PersonEntity p WHERE p.id IN (SELECT gm.personId FROM GroupStaffEntity gm WHERE gm.groupId = :groupId)")
    List<PersonEntity> findMemberByGroupId(@Param("groupId") Long groupId);

    @Modifying
    @Query("DELETE FROM GroupStaffEntity g WHERE g.groupId = :groupId")
    void deleteByGroupId(@Param("groupId") Long groupId);

}
