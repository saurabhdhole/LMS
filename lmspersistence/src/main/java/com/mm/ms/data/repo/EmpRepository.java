package com.mm.ms.data.repo;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.mm.ms.entity.Emp;


public interface EmpRepository extends PagingAndSortingRepository<Emp, Long> {
//	public Emp findByName(String name);

}
