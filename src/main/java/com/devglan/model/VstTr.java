//*************************************************************
//******* EMPLOYEE TRANSACTION (COVID QUESTIONNAIRE) **********
//*************************************************************

package com.devglan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "visitortr")
public class VstTr {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id; //transaction ID
    @Column
    private int visid; //visitor ID
    @Column
    private int empid; //PDI Employee ID
    @Column
    private String date;
    @Column
    private String time;
    @Column
    //@JsonIgnore
    private String Q1;
    @Column
    private String Q2;
    @Column
    private String Q3;
    @Column
    private String Q4;
    @Column
    private String Q5;
    @Column
    private float temp; //temperature

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int id) {
        this.empid = id;
    }

    public int getVisid() {
        return visid;
    }

    public void setVisid(int id) {
        this.visid = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dt) {
        this.date = dt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String tm) {
        this.time = tm;
    }

    public String getQ1() {
        return Q1;
    }

    public void setQ1(String qq) {
        this.Q1 = qq;
    }

    public String getQ2() {
        return Q2;
    }

    public void setQ2(String qq) {
        this.Q2 = qq;
    }

    public String getQ3() {
        return Q3;
    }

    public void setQ3(String qq) {
        this.Q3 = qq;
    }

    public String getQ4() {
        return Q4;
    }

    public void setQ4(String qq) {
        this.Q4 = qq;
    }

    public String getQ5() {
        return Q5;
    }

    public void setQ5(String qq) {
        this.Q5 = qq;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float tmp) {
        this.temp = tmp;
    }

}
