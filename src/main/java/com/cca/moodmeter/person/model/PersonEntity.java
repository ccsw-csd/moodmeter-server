package com.cca.moodmeter.person.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "all_person", catalog = "personal")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "active", nullable = false)
    private Integer active;

    /**
     * @return id
     */
    public Integer getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getid}.
     */
    public void setId(Integer id) {

        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {

        return this.username;
    }

    /**
     * @param username new value of {@link #getusername}.
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * @return email
     */
    public String getEmail() {

        return this.email;
    }

    /**
     * @param email new value of {@link #getemail}.
     */
    public void setEmail(String email) {

        this.email = email;
    }

    /**
     * @return name
     */
    public String getName() {

        return this.name;
    }

    /**
     * @param name new value of {@link #getname}.
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * @return lastname
     */
    public String getLastname() {

        return this.lastname;
    }

    /**
     * @param lastname new value of {@link #getlastname}.
     */
    public void setLastname(String lastname) {

        this.lastname = lastname;
    }

    /**
     * @return active
     */
    public Integer getActive() {

        return this.active;
    }

    /**
     * @param active new value of {@link #getactive}.
     */
    public void setActive(Integer active) {

        this.active = active;
    }

}
