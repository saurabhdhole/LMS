package com.mm.ms.data.repo;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.mm.ms.entity.EmpLeave;


public interface EmpLeaveRepository extends PagingAndSortingRepository<EmpLeave, Long> {
//	public Emp findByName(String name);

}
