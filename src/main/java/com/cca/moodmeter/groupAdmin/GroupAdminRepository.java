package com.cca.moodmeter.groupAdmin;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cca.moodmeter.groupAdmin.model.GroupAdminEntity;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupAdminRepository extends CrudRepository<GroupAdminEntity, Long> {

    @Query("SELECT p FROM PersonEntity p WHERE p.id IN (SELECT ga.personId FROM GroupAdminEntity ga WHERE ga.groupId = :groupId)")
    List<PersonEntity> findAdminByGroupId(@Param("groupId") Long groupId);

    @Modifying
    @Query("DELETE FROM GroupAdminEntity g WHERE g.groupId = :groupId")
    void deleteByGroupId(@Param("groupId") Long groupId);
}
