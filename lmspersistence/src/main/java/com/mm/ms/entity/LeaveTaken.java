package com.mm.ms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LeaveTaken implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Transient
	public final String entityname = "LeaveTaken";

	//START
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long empid;
	
	@Column
	private Date date;//starting date of leave
	
	@Column
	private String leavetype;//type of leave
	
	@Column
	private Long days;//number of days
	
	@Column
	private String reason;//leave management Entry No in table LMM

	//END
	
	
	public LeaveTaken(){
		
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public Long getDays() {
		return days;
	}
	public void setDays(Long days) {
		this.days = days;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Transient
	public String fetchLogDetails(){
		StringBuilder sb = new StringBuilder();
		sb.append("[")		
		.append(", Id = ").append(empid)
		.append(", Date = ").append(date)
		.append(", Type = ").append(leavetype)
		.append(", days = ").append(days)
		.append(", Reason = ").append(reason)
		.append("]");
		return sb.toString();
	}
	
	@Transient
	public LeaveTaken mergeUpdates(LeaveTaken tobemerged) {		
		this.leavetype = (null != tobemerged.getLeavetype()
				&& !("".equals(tobemerged.getLeavetype()))
				&& !(" ".equals(tobemerged.getLeavetype())) ? tobemerged.getLeavetype() : this.getLeavetype());
		
		this.reason = (null != tobemerged.getReason()
				&& !("".equals(tobemerged.getReason()))
				&& !(" ".equals(tobemerged.getReason())) ? tobemerged.getReason() : this.getReason());
		
		
		this.empid = (0.00 != tobemerged.getEmpid() ? tobemerged.getEmpid() 
				: this.getEmpid());
		
		this.days = (0.00 != tobemerged.getDays() ? tobemerged.getDays() 
				: this.getDays());
		
		this.date = (null!= tobemerged.getDate() ? tobemerged.getDate() 
				: this.getDate());
		
		
		return this;
	}
		
}
