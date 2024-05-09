package com.cca.moodmeter.group.database;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cca.moodmeter.group.model.GroupAdminEntity;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupAdminRepository extends CrudRepository<GroupAdminEntity, Long> {

    @Query("SELECT p FROM PersonEntity p WHERE p.id IN (SELECT ga.person.id FROM GroupAdminEntity ga WHERE ga.group.id = :groupId)")
    List<PersonEntity> findAdminByGroupId(@Param("groupId") Long groupId);

    void deleteAllByGroupId(@Param("groupId") Long groupId);
}
