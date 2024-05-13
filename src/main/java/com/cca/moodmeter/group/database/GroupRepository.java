package com.cca.moodmeter.group.database;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.group.model.GroupEntity;

public interface GroupRepository extends CrudRepository<GroupEntity, Long>, JpaSpecificationExecutor<GroupEntity> {

}
