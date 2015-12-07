package com.mm.ms.data.repo;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mm.ms.entity.Holiday;
import com.mm.ms.entity.LMM;


public interface HolidayRepository extends PagingAndSortingRepository<Holiday, Long> {
//	public Emp findByName(String name);
	@Query("SELECT h from Holiday h where h.date between :fdate and :ldate")
	List<Holiday> fetchByDate(@Param("fdate")Date fdate,@Param("ldate")Date ldate);

}
