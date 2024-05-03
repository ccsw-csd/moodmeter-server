package com.cca.moodmeter.group.model;

import java.util.List;

import com.cca.moodmeter.person.model.PersonEntity;

/**
 * @author mguaitav
 */
public class GroupEditDto {
    private GroupEntity group;
    private List<PersonEntity> collaborators;
    private List<PersonEntity> admins;

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public List<PersonEntity> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<PersonEntity> collaborators) {
        this.collaborators = collaborators;
    }

    public List<PersonEntity> getAdmins() {
        return admins;
    }

    public void setAdmin(List<PersonEntity> admins) {
        this.admins = admins;
    }

}
