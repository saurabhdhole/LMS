package com.mm.ms.data.repo;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.mm.ms.entity.LeaveTaken;


public interface LeaveTakenRepository extends PagingAndSortingRepository<LeaveTaken, Long> {
//	public Emp findByName(String name);

}
