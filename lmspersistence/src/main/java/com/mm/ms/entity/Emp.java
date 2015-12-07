package com.mm.ms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Transient
	public final String entityname = "Employee";

	//START
	@Id
	private Long empid;
	
	@Column
	private String empname;
	
	@Column
	private String email;
	
	@Column
	private Long mobile;
	
	@Column
	private String bloodgp;
	
	@Column
	private Long lmmno;//leave management Entry No in table LMonth Management

	@Column
	private String location;
	
	@Column
	private String department;
	
	@Column
	private String gender;
	//END
	
	
	public Emp(){
		
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getLmmno() {
		return lmmno;
	}

	public void setLmmno(Long lmmno) {
		this.lmmno = lmmno;
	}

	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getBloodgp() {
		return bloodgp;
	}
	public void setBloodgp(String bloodgp) {
		this.bloodgp = bloodgp;
	}
	
	@Transient
	public String fetchLogDetails(){
		StringBuilder sb = new StringBuilder();
		sb.append("[")		
		.append(", Id = ").append(empid)
		.append(", Name = ").append(empname)
		.append(", Email = ").append(email)
		.append(", Mobile = ").append(mobile)
		.append(", Blood Group = ").append(bloodgp)
		.append(", LMMNO = ").append(lmmno)
		.append("]");
		return sb.toString();
	}
	
	@Transient
	public Emp mergeUpdates(Emp tobemerged) {		
		this.empname = (null != tobemerged.getEmpname()
				&& !("".equals(tobemerged.getEmpname()))
				&& !(" ".equals(tobemerged.getEmpname())) ? tobemerged.getEmpname() : this.getEmpname());
		
		this.email = (null != tobemerged.getEmail()
				&& !("".equals(tobemerged.getEmail()))
				&& !(" ".equals(tobemerged.getEmail())) ? tobemerged.getEmail() : this.getEmail());
		
		this.bloodgp = (null != tobemerged.getBloodgp()
				&& !("".equals(tobemerged.getBloodgp()))
				&& !(" ".equals(tobemerged.getBloodgp())) ? tobemerged.getBloodgp() : this.getBloodgp());

		this.empid = (0.00 != tobemerged.getEmpid() ? tobemerged.getEmpid() 
				: this.getEmpid());
		
		this.mobile = (0.00 != tobemerged.getMobile() ? tobemerged.getMobile() 
				: this.getMobile());
		
		this.mobile = (0.00 != tobemerged.getMobile() ? tobemerged.getMobile() 
				: this.getMobile());
		
		this.lmmno = (0.00 != tobemerged.getLmmno() ? tobemerged.getLmmno() 
				: this.getLmmno());
		
		return this;
	}
		
}
