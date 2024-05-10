package com.cca.moodmeter.group.database;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cca.moodmeter.group.model.GroupAdminEntity;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupAdminRepository extends CrudRepository<GroupAdminEntity, Long> {

    @Query("Select ga.person FROM GroupAdminEntity ga WHERE ga.group.id = :groupId")
    List<PersonEntity> findAdminByGroupId(@Param("groupId") Long groupId);

    @Modifying
    void deleteAllByGroupId(@Param("groupId") Long groupId);
}
