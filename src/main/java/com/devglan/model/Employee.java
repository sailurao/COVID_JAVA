package com.devglan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int emp_id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String userid;
    @Column
    //@JsonIgnore
    private String email;
    @Column
    private String cell;
    @Column
    private String address;

    public int getId() {
        return emp_id;
    }

    public void setId(int id) {
        this.emp_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String username) {
        this.userid = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
