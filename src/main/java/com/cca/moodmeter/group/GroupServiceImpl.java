package com.cca.moodmeter.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.group.model.GroupEntity;

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
    public List<GroupEntity> findAll() {

        return (List<GroupEntity>) this.groupRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, GroupDto dto) {

        GroupEntity group;

        if (id == null) {
            group = new GroupEntity();
        } else {
            group = this.groupRepository.findById(id).orElse(null);
        }

        group.setName(dto.getName());
        group.setCreationDate(dto.getCreationDate());
        group.setCreationUsername(dto.getCreationUsername());
        group.setUpdateDate(dto.getUpdateDate());
        group.setUpdateUsername(dto.getUpdateUsername());

        this.groupRepository.save(group);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.groupRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.groupRepository.deleteById(id);
    }
}
