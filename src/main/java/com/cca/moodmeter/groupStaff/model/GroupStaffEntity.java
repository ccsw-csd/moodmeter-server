package com.cca.moodmeter.groupStaff.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author mguaitav
 */
@Entity
@Table(name= "group_member")
public class GroupStaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "person_id", nullable = false)
    private Long personId;

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
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 
     * @param groupId new value of {@link #getGroupId}.
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * 
     * @return personId
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 
     * @param personId new value of {@link #getPersonId}.
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
