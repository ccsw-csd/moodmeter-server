package com.cca.moodmeter.group.model;

import java.util.List;

import com.cca.moodmeter.person.model.PersonDto;

/**
 * @author mguaitav
 */
public class GroupEditDto {
    private GroupDto group;
    private List<PersonDto> collaborators;
    private List<PersonDto> admins;

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public List<PersonDto> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<PersonDto> collaborators) {
        this.collaborators = collaborators;
    }

    public List<PersonDto> getAdmins() {
        return admins;
    }

    public void setAdmin(List<PersonDto> admins) {
        this.admins = admins;
    }

}
