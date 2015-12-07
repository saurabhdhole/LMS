package com.mm.ms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LMM implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Transient
	public final String entityname = "LeaveMonthManagement";

	//START
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Long empid;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column
	private String day1;
	
	@Column
	private String day2;
	
	@Column
	private String day3;
	
	@Column
	private String day4;
	
	@Column
	private String day5;

	@Column
	private String day6;
	
	@Column
	private String day7;
	
	@Column
	private String day8;
	
	@Column
	private String day9;
	
	@Column
	private String day10;

	@Column
	private String day11;
	
	@Column
	private String day12;
	
	@Column
	private String day13;
	
	@Column
	private String day14;
	
	@Column
	private String day15;

	@Column
	private String day16;
	
	@Column
	private String day17;
	
	@Column
	private String day18;
	
	@Column
	private String day19;
	
	@Column
	private String day20;

	@Column
	private String day21;
	
	@Column
	private String day22;
	
	@Column
	private String day23;
	
	@Column
	private String day24;
	
	@Column
	private String day25;

	@Column
	private String day26;
	
	@Column
	private String day27;
	
	@Column
	private String day28;
	
	@Column
	private String day29;
	
	@Column
	private String day30;
	
	@Column
	private String day31;

	//END
	
	
	public LMM(){
		
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
	public String getDay1() {
		return day1;
	}
	public void setDay1(String day1) {
		this.day1 = day1;
	}
	public String getDay2() {
		return day2;
	}
	public void setDay2(String day2) {
		this.day2 = day2;
	}
	public String getDay3() {
		return day3;
	}
	public void setDay3(String day3) {
		this.day3 = day3;
	}
	public String getDay4() {
		return day4;
	}
	public void setDay4(String day4) {
		this.day4 = day4;
	}
	public String getDay5() {
		return day5;
	}
	public void setDay5(String day5) {
		this.day5 = day5;
	}
	public String getDay6() {
		return day6;
	}
	public void setDay6(String day6) {
		this.day6 = day6;
	}
	public String getDay7() {
		return day7;
	}
	public void setDay7(String day7) {
		this.day7 = day7;
	}
	public String getDay8() {
		return day8;
	}
	public void setDay8(String day8) {
		this.day8 = day8;
	}
	public String getDay9() {
		return day9;
	}
	public void setDay9(String day9) {
		this.day9 = day9;
	}
	public String getDay10() {
		return day10;
	}
	public void setDay10(String day10) {
		this.day10 = day10;
	}
	public String getDay11() {
		return day11;
	}
	public void setDay11(String day11) {
		this.day11 = day11;
	}
	public String getDay12() {
		return day12;
	}
	public void setDay12(String day12) {
		this.day12 = day12;
	}
	public String getDay13() {
		return day13;
	}
	public void setDay13(String day13) {
		this.day13 = day13;
	}
	public String getDay14() {
		return day14;
	}
	public void setDay14(String day14) {
		this.day14 = day14;
	}
	public String getDay15() {
		return day15;
	}
	public void setDay15(String day15) {
		this.day15 = day15;
	}
	public String getDay16() {
		return day16;
	}
	public void setDay16(String day16) {
		this.day16 = day16;
	}
	public String getDay17() {
		return day17;
	}
	public void setDay17(String day17) {
		this.day17 = day17;
	}
	public String getDay18() {
		return day18;
	}
	public void setDay18(String day18) {
		this.day18 = day18;
	}
	public String getDay19() {
		return day19;
	}
	public void setDay19(String day19) {
		this.day19 = day19;
	}
	public String getDay20() {
		return day20;
	}
	public void setDay20(String day20) {
		this.day20 = day20;
	}
	public String getDay21() {
		return day21;
	}
	public void setDay21(String day21) {
		this.day21 = day21;
	}
	public String getDay22() {
		return day22;
	}
	public void setDay22(String day22) {
		this.day22 = day22;
	}
	public String getDay23() {
		return day23;
	}
	public void setDay23(String day23) {
		this.day23 = day23;
	}
	public String getDay24() {
		return day24;
	}
	public void setDay24(String day24) {
		this.day24 = day24;
	}
	public String getDay25() {
		return day25;
	}
	public void setDay25(String day25) {
		this.day25 = day25;
	}
	public String getDay26() {
		return day26;
	}
	public void setDay26(String day26) {
		this.day26 = day26;
	}
	public String getDay27() {
		return day27;
	}
	public void setDay27(String day27) {
		this.day27 = day27;
	}
	public String getDay28() {
		return day28;
	}
	public void setDay28(String day28) {
		this.day28 = day28;
	}
	public String getDay29() {
		return day29;
	}
	public void setDay29(String day29) {
		this.day29 = day29;
	}
	public String getDay30() {
		return day30;
	}
	public void setDay30(String day30) {
		this.day30 = day30;
	}
	public String getDay31() {
		return day31;
	}
	public void setDay31(String day31) {
		this.day31 = day31;
	}
	public String getEntityname() {
		return entityname;
	}
	@Transient
	public String fetchLogDetails(){
		StringBuilder sb = new StringBuilder();
		sb.append("[")		
		.append(", Id = ").append(empid)
		.append(", date = ").append(date)
		.append(", day = ").append(""+day1+day2+day3+day4+day6+day7+day8+day9+day10+day11+day12+day13+day14+day15+day16+day17+day18+day19+day20+day21+day22+day23+day24+day26+day27+day29+day30+day31)
		.append("]");
		return sb.toString();
	}
	
	@Transient
	public LMM mergeUpdates(LMM tobemerged) {		
		this.day1 = (null != tobemerged.getDay1()
				&& !("".equals(tobemerged.getDay1()))
				&& !(" ".equals(tobemerged.getDay1())) ? tobemerged.getDay1() : this.getDay1());
		
		this.day2 = (null != tobemerged.getDay2()
				&& !("".equals(tobemerged.getDay2()))
				&& !(" ".equals(tobemerged.getDay2())) ? tobemerged.getDay2() : this.getDay2());
		
		this.day3 = (null != tobemerged.getDay3()
				&& !("".equals(tobemerged.getDay3()))
				&& !(" ".equals(tobemerged.getDay3())) ? tobemerged.getDay3() : this.getDay3());
		
		this.day4 = (null != tobemerged.getDay4()
				&& !("".equals(tobemerged.getDay4()))
				&& !(" ".equals(tobemerged.getDay4())) ? tobemerged.getDay4() : this.getDay4());
		
		this.day5 = (null != tobemerged.getDay3()
				&& !("".equals(tobemerged.getDay3()))
				&& !(" ".equals(tobemerged.getDay3())) ? tobemerged.getDay3() : this.getDay3());
		
		this.day6 = (null != tobemerged.getDay6()
				&& !("".equals(tobemerged.getDay6()))
				&& !(" ".equals(tobemerged.getDay6())) ? tobemerged.getDay6() : this.getDay6());
		
		this.day7 = (null != tobemerged.getDay7()
				&& !("".equals(tobemerged.getDay7()))
				&& !(" ".equals(tobemerged.getDay7())) ? tobemerged.getDay7() : this.getDay7());
		
		this.day8 = (null != tobemerged.getDay8()
				&& !("".equals(tobemerged.getDay8()))
				&& !(" ".equals(tobemerged.getDay8())) ? tobemerged.getDay8() : this.getDay8());
		
		this.day9 = (null != tobemerged.getDay9()
				&& !("".equals(tobemerged.getDay9()))
				&& !(" ".equals(tobemerged.getDay9())) ? tobemerged.getDay9() : this.getDay9());
		
		this.day10 = (null != tobemerged.getDay10()
				&& !("".equals(tobemerged.getDay10()))
				&& !(" ".equals(tobemerged.getDay10())) ? tobemerged.getDay10() : this.getDay10());
		
		this.day11 = (null != tobemerged.getDay11()
				&& !("".equals(tobemerged.getDay11()))
				&& !(" ".equals(tobemerged.getDay11())) ? tobemerged.getDay11() : this.getDay11());
		
		this.day12 = (null != tobemerged.getDay12()
				&& !("".equals(tobemerged.getDay12()))
				&& !(" ".equals(tobemerged.getDay12())) ? tobemerged.getDay12() : this.getDay12());
		
		this.day13 = (null != tobemerged.getDay13()
				&& !("".equals(tobemerged.getDay13()))
				&& !(" ".equals(tobemerged.getDay13())) ? tobemerged.getDay13() : this.getDay13());
		
		this.day14 = (null != tobemerged.getDay14()
				&& !("".equals(tobemerged.getDay14()))
				&& !(" ".equals(tobemerged.getDay14())) ? tobemerged.getDay14() : this.getDay14());
		
		this.day15 = (null != tobemerged.getDay15()
				&& !("".equals(tobemerged.getDay15()))
				&& !(" ".equals(tobemerged.getDay15())) ? tobemerged.getDay15() : this.getDay15());
		
		this.day16 = (null != tobemerged.getDay16()
				&& !("".equals(tobemerged.getDay16()))
				&& !(" ".equals(tobemerged.getDay16())) ? tobemerged.getDay16() : this.getDay16());
		
		this.day17 = (null != tobemerged.getDay17()
				&& !("".equals(tobemerged.getDay17()))
				&& !(" ".equals(tobemerged.getDay17())) ? tobemerged.getDay17() : this.getDay17());
		
		this.day18 = (null != tobemerged.getDay18()
				&& !("".equals(tobemerged.getDay18()))
				&& !(" ".equals(tobemerged.getDay18())) ? tobemerged.getDay18() : this.getDay18());
		
		this.day19 = (null != tobemerged.getDay19()
				&& !("".equals(tobemerged.getDay19()))
				&& !(" ".equals(tobemerged.getDay19())) ? tobemerged.getDay19() : this.getDay19());
		
		this.day20 = (null != tobemerged.getDay20()
				&& !("".equals(tobemerged.getDay20()))
				&& !(" ".equals(tobemerged.getDay20())) ? tobemerged.getDay20() : this.getDay20());
		
		this.day21 = (null != tobemerged.getDay21()
				&& !("".equals(tobemerged.getDay21()))
				&& !(" ".equals(tobemerged.getDay21())) ? tobemerged.getDay21() : this.getDay21());
		
		this.day22 = (null != tobemerged.getDay22()
				&& !("".equals(tobemerged.getDay22()))
				&& !(" ".equals(tobemerged.getDay22())) ? tobemerged.getDay22() : this.getDay22());
		
		this.day23 = (null != tobemerged.getDay23()
				&& !("".equals(tobemerged.getDay23()))
				&& !(" ".equals(tobemerged.getDay23())) ? tobemerged.getDay23() : this.getDay23());
		
		this.day24 = (null != tobemerged.getDay24()
				&& !("".equals(tobemerged.getDay24()))
				&& !(" ".equals(tobemerged.getDay24())) ? tobemerged.getDay24() : this.getDay24());
		
		this.day25 = (null != tobemerged.getDay25()
				&& !("".equals(tobemerged.getDay25()))
				&& !(" ".equals(tobemerged.getDay25())) ? tobemerged.getDay25() : this.getDay25());
		
		this.day26 = (null != tobemerged.getDay26()
				&& !("".equals(tobemerged.getDay26()))
				&& !(" ".equals(tobemerged.getDay26())) ? tobemerged.getDay26() : this.getDay26());
		
		this.day27 = (null != tobemerged.getDay27()
				&& !("".equals(tobemerged.getDay27()))
				&& !(" ".equals(tobemerged.getDay27())) ? tobemerged.getDay27() : this.getDay27());
		
		this.day28 = (null != tobemerged.getDay28()
				&& !("".equals(tobemerged.getDay28()))
				&& !(" ".equals(tobemerged.getDay28())) ? tobemerged.getDay28() : this.getDay28());
		
		this.day29 = (null != tobemerged.getDay29()
				&& !("".equals(tobemerged.getDay29()))
				&& !(" ".equals(tobemerged.getDay29())) ? tobemerged.getDay29() : this.getDay29());
		
		this.day30 = (null != tobemerged.getDay30()
				&& !("".equals(tobemerged.getDay30()))
				&& !(" ".equals(tobemerged.getDay30())) ? tobemerged.getDay30() : this.getDay30());
		
		this.day31 = (null != tobemerged.getDay31()
				&& !("".equals(tobemerged.getDay31()))
				&& !(" ".equals(tobemerged.getDay31())) ? tobemerged.getDay31() : this.getDay31());
		
		
		this.empid = (0.00 != tobemerged.getEmpid() ? tobemerged.getEmpid() 
				: this.getEmpid());
		
		this.date = (null != tobemerged.getDate() ? tobemerged.getDate() 
				: this.getDate());
		
		return this;
	}
		
}
