package com.cca.moodmeter.group.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author mguaitav
 *
 */
@Entity
@Table(name = "`group`")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "creation_username", nullable = false)
    private String creationUsername;

    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Column(name = "update_username", nullable = false)
    private String updateUsername;

    /**
     * 
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name new value of {@link #getName}.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate new value of {@link #getCreationDate}.
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return creation username
     */
    public String getCreationUsername() {
        return creationUsername;
    }

    /**
     * 
     * @param creationUsername new value of {@link #getCreationUsername}.
     */
    public void setCreationUsername(String creationUsername) {
        this.creationUsername = creationUsername;
    }

    /**
     * 
     * @return update date
     */
    public LocalDate getUpdateDate() {
        return updateDate;
    }

    /**
     * 
     * @param updateDate new value of {@link #getUpdateDate}.
     */
    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 
     * @return update username
     */
    public String getUpdateUsername() {
        return updateUsername;
    }

    /**
     * 
     * @param updateUsername new value of {@link #getUpdateUsername}.
     */
    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }

}
