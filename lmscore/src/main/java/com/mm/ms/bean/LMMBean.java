package com.mm.ms.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.mm.ms.BaseAbstractBean;
import com.mm.ms.data.repo.LMMRepository;
import com.mm.ms.entity.LMM;

public class LMMBean extends BaseAbstractBean<LMM, Long> {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	LMMRepository lMMRepository;

	@Override
	public LMM create(String logstr, LMM entity) {
		logger.debug(logstr + "create LMM record " + entity.fetchLogDetails());
		LMM emp = lMMRepository.save(entity);
		logger.debug(logstr + "created LMM record " + entity.fetchLogDetails());
		return emp;
	}

	@Override
	public LMM read(String logstr, Long id) {
		logger.debug(logstr + "get LMM record at id " + id);
		LMM emp = lMMRepository.findOne(id);
		logger.debug(logstr + "got LMM record " + emp.fetchLogDetails());
		return emp;
	}

	@Override
	public Iterable<LMM> readAll(String logstr) {
		logger.debug(logstr + "get all LMM records");
		Iterable<LMM> empRecords = lMMRepository.findAll();
		return empRecords;
	}

	@Override
	public Iterable<LMM> readAll(String logstr, Pageable pageable) {
		logger.debug(logstr + "get all LMM records pageable "
				+ logdetails(pageable));
		Iterable<LMM> empRecords = lMMRepository.findAll(pageable);
		return empRecords;
	}

	@Override
	public LMM update(String logstr, LMM tobemerged) {
		logger.debug(logstr + "update LMM record " + tobemerged.fetchLogDetails());
		LMM empFind = lMMRepository.findOne(tobemerged.getId());
		LMM mergeEmp = lMMRepository.save(empFind
				.mergeUpdates(tobemerged));
		logger.debug(logstr + "updated LMM record " + mergeEmp.fetchLogDetails());
		return mergeEmp;
	}

	@Override
	public Boolean delete(String logstr, Long id) {
		logger.debug(logstr + "delete LMM record at id " + id);
		lMMRepository.delete(id);
		logger.debug(logstr + "deleted LMM record at id " + id);
		return true;
	}
}
