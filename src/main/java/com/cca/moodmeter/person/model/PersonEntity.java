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

    @Column(name = "saga", nullable = false)
    private String saga;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "center")
    private String center;

    @Column(name = "grade")
    private String grade;

    @Column(name = "businesscode")
    private String businesscode;

    @Column(name = "pucode")
    private String pucode;

    @Column(name = "startdate")
    private String startDate;

    @Column(name = "jobrole")
    private String jobRole;

    @Column(name = "entity_ldap")
    private boolean entity_Ldap;

    @Column(name = "global_employee_id")
    private String globalEmployeeId;

    @Column(name = "location")
    private String location;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "enddate")
    private String endDate;

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
     * @return saga
     */
    public String getSaga() {

        return this.saga;
    }

    /**
     * @param saga new value of {@link #getsaga}.
     */
    public void setSaga(String saga) {

        this.saga = saga;
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
     * @return center
     */
    public String getCenter() {

        return this.center;
    }

    /**
     * @param centeId new value of {@link #getcenter}.
     */
    public void setCenter(String center) {

        this.center = center;
    }

    /**
     * @return businesscode
     */
    public String getBusinesscode() {

        return this.businesscode;
    }

    /**
     * @param businesscode new value of {@link #getbusinesscode}.
     */
    public void setBusinesscode(String businesscode) {

        this.businesscode = businesscode;
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

    public String getGrade() {

        return grade;
    }

    public void setGrade(String grade) {

        this.grade = grade;
    }

    public String getGlobalEmployeeId() {
        return globalEmployeeId;
    }

    public void setGlobalEmployeeId(String globalEmployeeId) {
        this.globalEmployeeId = globalEmployeeId;
    }

    public String getPucode() {
        return pucode;
    }

    public void setPucode(String pucode) {
        this.pucode = pucode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public boolean isEntity_Ldap() {
        return entity_Ldap;
    }

    public void setEntity_Ldap(boolean entity_Ldap) {
        this.entity_Ldap = entity_Ldap;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
