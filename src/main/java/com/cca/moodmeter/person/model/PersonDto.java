package com.cca.moodmeter.person.model;

public class PersonDto {

    private Long id;
    private String username;
    private String name;
    private String lastname;
    private Boolean active;

    /**
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id new value of {@link #getId()}.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username new value of {@link #getUsername()}.
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @param name new value of {@link #getName()}.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * 
     * @param lastname new value of {@link #getLastname()}.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * 
     * @return active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * 
     * @param active new value of {@link #getActive()}.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

}
