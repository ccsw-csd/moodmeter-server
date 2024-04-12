package com.cca.moodmeter.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.group.model.Group;

/**
 * @author mguaitav
 *
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Group> findAll() {

        return (List<Group>) this.groupRepository.findAll();
    }

}
