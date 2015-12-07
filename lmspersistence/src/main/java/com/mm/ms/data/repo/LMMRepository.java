package com.mm.ms.data.repo;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mm.ms.entity.Holiday;
import com.mm.ms.entity.LMM;


public interface LMMRepository extends PagingAndSortingRepository<LMM, Long> {
//	public Emp findByName(String name);

//	@Query("SELECT l from LMM l where l.date>=:date and l.empid=:empid")
//	LMM fetchByDateAndEmpid(@Param("date")Date date,@Param("empid")Long empid);
//	
	@Query("SELECT l from LMM l where l.empid=:empid and l.date between :fdate and :ldate")
	LMM fetchbyDateandid(@Param("fdate")Date fdate,@Param("ldate")Date ldate,@Param("empid")Long empid);

	@Query("SELECT l from LMM l where l.date>=:fdate AND l.date<=:ldate AND l.empid=:empid")
	LMM fetchbydate(@Param("fdate")Date fdate,@Param("ldate")Date ldate,@Param("empid")Long empid);
	
	@Query("SELECT l from LMM l where l.date>=:fdate AND l.date<=:ldate")
	List<LMM> fetchbydate2(@Param("fdate")Date fdate,@Param("ldate")Date ldate);
	
	

}
