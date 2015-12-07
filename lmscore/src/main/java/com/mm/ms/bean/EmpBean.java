package com.mm.ms.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.mm.ms.BaseAbstractBean;
import com.mm.ms.data.repo.EmpRepository;
import com.mm.ms.entity.Emp;

public class EmpBean extends BaseAbstractBean<Emp, Long> {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	EmpRepository empRepository;

	@Override
	public Emp create(String logstr, Emp entity) {
		logger.debug(logstr + "create Employee record " + entity.fetchLogDetails());
		Emp emp = empRepository.save(entity);
		logger.debug(logstr + "created Employee record " + entity.fetchLogDetails());
		return emp;
	}

	@Override
	public Emp read(String logstr, Long id) {
		logger.debug(logstr + "get Employee record at id " + id);
		Emp emp = empRepository.findOne(id);
		logger.debug(logstr + "got Employee record " + emp.fetchLogDetails());
		return emp;
	}

	@Override
	public Iterable<Emp> readAll(String logstr) {
		logger.debug(logstr + "get all Employee records");
		Iterable<Emp> empRecords = empRepository.findAll();
		return empRecords;
	}

	@Override
	public Iterable<Emp> readAll(String logstr, Pageable pageable) {
		logger.debug(logstr + "get all Employee records pageable "
				+ logdetails(pageable));
		Iterable<Emp> empRecords = empRepository.findAll(pageable);
		return empRecords;
	}

	@Override
	public Emp update(String logstr, Emp tobemerged) {
		logger.debug(logstr + "update Employee record " + tobemerged.fetchLogDetails());
		Emp empFind = empRepository.findOne(tobemerged.getEmpid());
		Emp mergeEmp = empRepository.save(empFind
				.mergeUpdates(tobemerged));
		logger.debug(logstr + "updated Employee record " + mergeEmp.fetchLogDetails());
		return mergeEmp;
	}

	@Override
	public Boolean delete(String logstr, Long id) {
		logger.debug(logstr + "delete Employee record at id " + id);
		empRepository.delete(id);
		logger.debug(logstr + "deleted Employee record at id " + id);
		return true;
	}
}
