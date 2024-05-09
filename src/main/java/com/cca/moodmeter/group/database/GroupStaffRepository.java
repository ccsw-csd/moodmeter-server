package com.cca.moodmeter.group.database;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cca.moodmeter.group.model.GroupStaffEntity;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupStaffRepository extends CrudRepository<GroupStaffEntity, Long> {

    @Query("SELECT p FROM PersonEntity p WHERE p.id IN (SELECT gm.person.id FROM GroupStaffEntity gm WHERE gm.group.id = :groupId)")
    List<PersonEntity> findMemberByGroupId(@Param("groupId") Long groupId);

    void deleteAllByGroupId(@Param("groupId") Long groupId);
}
