package com.cca.moodmeter.group.model;

import com.cca.moodmeter.person.model.PersonEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author mguaitav
 */
@Entity
@Table(name = "group_member")
public class GroupStaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return groupId
     */
    public GroupEntity getGroup() {
        return group;
    }

    /**
     * 
     * @param group new value of {@link #getGroup}.
     */
    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    /**
     * 
     * @return person
     */
    public PersonEntity getPerson() {
        return person;
    }

    /**
     * 
     * @param person new value of {@link #getPerson}.
     */
    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
