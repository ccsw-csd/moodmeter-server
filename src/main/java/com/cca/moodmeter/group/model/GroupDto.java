package com.cca.moodmeter.group.model;

import java.time.LocalDate;

/**
 * @author mguaitav
 */
public class GroupDto {

    Long id;
    String name;
    LocalDate creationDate;
    String creationUsername;
    LocalDate updateDate;
    String updateUsername;

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
