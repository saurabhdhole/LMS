package com.mm.ms.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.mm.ms.BaseAbstractBean;
import com.mm.ms.data.repo.LeaveTakenRepository;
import com.mm.ms.entity.LeaveTaken;

public class LeaveTakenBean extends BaseAbstractBean<LeaveTaken, Long> {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	LeaveTakenRepository leaveTakenRepository;

	@Override
	public LeaveTaken create(String logstr, LeaveTaken entity) {
		logger.debug(logstr + "create LeaveTaken record " + entity.fetchLogDetails());
		LeaveTaken emp = leaveTakenRepository.save(entity);
		logger.debug(logstr + "created LeaveTaken record " + entity.fetchLogDetails());
		return emp;
	}

	@Override
	public LeaveTaken read(String logstr, Long id) {
		logger.debug(logstr + "get LeaveTaken record at id " + id);
		LeaveTaken emp = leaveTakenRepository.findOne(id);
		logger.debug(logstr + "got LeaveTaken record " + emp.fetchLogDetails());
		return emp;
	}

	@Override
	public Iterable<LeaveTaken> readAll(String logstr) {
		logger.debug(logstr + "get all LeaveTaken records");
		Iterable<LeaveTaken> empRecords = leaveTakenRepository.findAll();
		return empRecords;
	}

	@Override
	public Iterable<LeaveTaken> readAll(String logstr, Pageable pageable) {
		logger.debug(logstr + "get all LeaveTaken records pageable "
				+ logdetails(pageable));
		Iterable<LeaveTaken> empRecords = leaveTakenRepository.findAll(pageable);
		return empRecords;
	}

	@Override
	public LeaveTaken update(String logstr, LeaveTaken tobemerged) {
		logger.debug(logstr + "update LeaveTaken record " + tobemerged.fetchLogDetails());
		LeaveTaken empFind = leaveTakenRepository.findOne(tobemerged.getEmpid());
		LeaveTaken mergeEmp = leaveTakenRepository.save(empFind
				.mergeUpdates(tobemerged));
		logger.debug(logstr + "updated LeaveTaken record " + mergeEmp.fetchLogDetails());
		return mergeEmp;
	}

	@Override
	public Boolean delete(String logstr, Long id) {
		logger.debug(logstr + "delete LeaveTaken record at id " + id);
		leaveTakenRepository.delete(id);
		logger.debug(logstr + "deleted LeaveTaken record at id " + id);
		return true;
	}
}
