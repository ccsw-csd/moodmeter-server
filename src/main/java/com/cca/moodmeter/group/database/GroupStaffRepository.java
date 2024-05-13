package com.cca.moodmeter.group.database;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cca.moodmeter.group.model.GroupStaffEntity;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupStaffRepository extends CrudRepository<GroupStaffEntity, Long> {

    @Query("Select ga.person FROM GroupStaffEntity ga WHERE ga.group.id = :groupId")
    List<PersonEntity> findMemberByGroupId(@Param("groupId") Long groupId);

    @Modifying
    void deleteAllByGroupId(@Param("groupId") Long groupId);
}
