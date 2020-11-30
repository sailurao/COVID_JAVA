
//*************************************************************
//******* EMPLOYEE TRANSACTION DTO (DATA TRANSFER OBJECT)******
//*************************************************************

package com.devglan.model;

public class VstTrDto {
    private int id;
    private int visid;
    private int empid;
    private String date;
    private String time;
    private String Q1;
    private String Q2;
    private String Q3;
    private String Q4;
    private String Q5;
    private float temp;

    public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
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

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getQ1() {
		return Q1;
	}

	public void setQ1(String q1) {
		Q1 = q1;
	}

	public String getQ2() {
		return Q2;
	}

	public void setQ2(String q2) {
		Q2 = q2;
	}

	public String getQ3() {
		return Q3;
	}

	public void setQ3(String q3) {
		Q3 = q3;
	}

	public String getQ4() {
		return Q4;
	}

	public void setQ4(String q4) {
		Q4 = q4;
	}

	public String getQ5() {
		return Q5;
	}

	public void setQ5(String q5) {
		Q5 = q5;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    


}
