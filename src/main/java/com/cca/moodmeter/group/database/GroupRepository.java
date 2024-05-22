package com.cca.moodmeter.group.database;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.group.model.GroupEntity;

public interface GroupRepository extends CrudRepository<GroupEntity, Long>, JpaSpecificationExecutor<GroupEntity> {

    @Query("SELECT g FROM GroupEntity g, GroupAdminEntity ga WHERE g.id = ga.group.id AND ga.person.id = :adminId")
    List<GroupEntity> getGroupsByAdminId(Long adminId);

}
