package com.mm.ms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmpLeave implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Transient
	public final String entityname = "Leave";

	//START
	@Id
	private Long empid;
	
	@Column
	private Long el;//Earned Leave
	
	@Column
	private Long pel;//previous EL Records
	
	@Column
	private Long cl;//causal leave
	
	@Column
	private Long sl;//Sick Leave
	
	@Column
	private Long ml;//matiernity leave

	@Column
	private Long pl;//Patiernary leave
	
	@Column
	private Long cpl;//Compassionate Leave
	
	@Column
	private Long mml;//Marriage leave
	//END
	
	
	public EmpLeave(){
		
	}
	public Long getEmpid() {
		return empid;
	}
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	
	
	
	public Long getEl() {
		return el;
	}
	public void setEl(Long el) {
		this.el = el;
	}
	public Long getPel() {
		return pel;
	}
	public void setPel(Long pel) {
		this.pel = pel;
	}
	public Long getCl() {
		return cl;
	}
	public void setCl(Long cl) {
		this.cl = cl;
	}
	public Long getSl() {
		return sl;
	}
	public void setSl(Long sl) {
		this.sl = sl;
	}
	public Long getMl() {
		return ml;
	}
	public void setMl(Long ml) {
		this.ml = ml;
	}
	public Long getPl() {
		return pl;
	}
	public void setPl(Long pl) {
		this.pl = pl;
	}
	public Long getCpl() {
		return cpl;
	}
	public void setCpl(Long cpl) {
		this.cpl = cpl;
	}
	public Long getMml() {
		return mml;
	}
	public void setMml(Long mml) {
		this.mml = mml;
	}
	@Transient
	public String fetchLogDetails(){
		StringBuilder sb = new StringBuilder();
		sb.append("[")		
		.append(", Id = ").append(empid)
		.append(", Earned Leave = ").append(el)
		.append(", Causal Leave = ").append(cl)
		.append(", Sick Leave = ").append(sl)
		.append(", Matirnity Leave = ").append(ml)
		.append(", Marriage Leave = ").append(mml)
		.append(", Compassionate Leave = ").append(cpl)
		.append(", Patiornity Leave = ").append(pl)
		.append("]");
		return sb.toString();
	}
	
	@Transient
	public EmpLeave mergeUpdates(EmpLeave tobemerged) {		
		
		this.empid = (0.00 != tobemerged.getEmpid() ? tobemerged.getEmpid() 
				: this.getEmpid());
		
		this.el = (0.00 != tobemerged.getEl() ? tobemerged.getEl() 
				: this.getEl());
		
		this.pel = (0.00 != tobemerged.getPel() ? tobemerged.getPel() 
				: this.getPel());
		
		this.cl = (0.00 != tobemerged.getCl() ? tobemerged.getCl() 
				: this.getCl());
		
		this.sl = (0.00 != tobemerged.getSl() ? tobemerged.getSl() 
				: this.getSl());
		
		this.ml = (0.00 != tobemerged.getMl() ? tobemerged.getMl() 
				: this.getMl());
		
		this.pl = (0.00 != tobemerged.getPl() ? tobemerged.getPl() 
				: this.getPl());
		
		this.cpl = (0.00 != tobemerged.getCpl() ? tobemerged.getCpl() 
				: this.getCpl());
		
		this.mml = (0.00 != tobemerged.getMml() ? tobemerged.getMml() 
				: this.getMml());
		
		
		return this;
	}
		
}
