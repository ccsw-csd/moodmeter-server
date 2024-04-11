package com.cca.moodmeter.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.group.model.Group;
import com.cca.moodmeter.group.model.GroupDto;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, GroupDto dto) {

        Group group;

        if (id == null) {
            group = new Group();
        } else {
            group = this.groupRepository.findById(id).orElse(null);
        }

        group.setName(dto.getName());

        this.groupRepository.save(group);
    }
}
