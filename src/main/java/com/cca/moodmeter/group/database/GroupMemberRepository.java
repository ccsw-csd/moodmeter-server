package com.cca.moodmeter.group.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.group.model.GroupMemberEntity;

public interface GroupMemberRepository extends CrudRepository<GroupMemberEntity, Long> {

    List<GroupMemberEntity> findAllByPersonId(Long personId);

}
