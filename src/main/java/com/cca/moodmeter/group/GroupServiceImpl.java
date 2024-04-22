package com.cca.moodmeter.group;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.config.security.UserUtils;
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

            group.setCreationDate(LocalDate.now());
            group.setUpdateDate(LocalDate.now());

            group.setCreationUsername(UserUtils.getUserDetails().getUsername());
            group.setUpdateUsername(UserUtils.getUserDetails().getUsername());
        } else {
            group = this.groupRepository.findById(id).orElse(null);

            group.setUpdateDate(LocalDate.now());
            group.setUpdateUsername(UserUtils.getUserDetails().getUsername());
        }

        group.setName(dto.getName());

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
