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
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Transient
	public final String entityname = "Holiday";

	//START
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Date date;
	
	@Column
	private String description;
	
	//END
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Holiday(){
		
	}
	
	@Transient
	public String fetchLogDetails(){
		StringBuilder sb = new StringBuilder();
		sb.append("[")		
		.append(", Id = ").append(id)
		.append(", Date = ").append(date)
		.append(", Description = ").append(description)
		.append("]");
		return sb.toString();
	}
	
	@Transient
	public Holiday mergeUpdates(Holiday tobemerged) {		
		this.description = (null != tobemerged.getDescription()
				&& !("".equals(tobemerged.getDescription()))
				&& !(" ".equals(tobemerged.getDescription())) ? tobemerged.getDescription() : this.getDescription());
		
		this.id = (0.00 != tobemerged.getId() ? tobemerged.getId() 
				: this.getId());
		
		this.date = (null != tobemerged.getDate() ? tobemerged.getDate() 
				: this.getDate());
	
		
		return this;
	}
		
}
