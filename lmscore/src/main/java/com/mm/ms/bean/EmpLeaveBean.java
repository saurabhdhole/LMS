package com.mm.ms.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.mm.ms.BaseAbstractBean;
import com.mm.ms.data.repo.EmpLeaveRepository;
import com.mm.ms.entity.EmpLeave;

public class EmpLeaveBean extends BaseAbstractBean<EmpLeave, Long> {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	EmpLeaveRepository empLeaveRepository;

	@Override
	public EmpLeave create(String logstr, EmpLeave entity) {
		logger.debug(logstr + "create EmployeeLeave record " + entity.fetchLogDetails());
		EmpLeave emp = empLeaveRepository.save(entity);
		logger.debug(logstr + "created EmployeeLeave record " + entity.fetchLogDetails());
		return emp;
	}

	@Override
	public EmpLeave read(String logstr, Long id) {
		logger.debug(logstr + "get EmployeeLeave record at id " + id);
		EmpLeave emp = empLeaveRepository.findOne(id);
		logger.debug(logstr + "got EmployeeLeave record " + emp.fetchLogDetails());
		return emp;
	}

	@Override
	public Iterable<EmpLeave> readAll(String logstr) {
		logger.debug(logstr + "get all EmployeeLeave records");
		Iterable<EmpLeave> empRecords = empLeaveRepository.findAll();
		return empRecords;
	}

	@Override
	public Iterable<EmpLeave> readAll(String logstr, Pageable pageable) {
		logger.debug(logstr + "get all EmployeeLeave records pageable "
				+ logdetails(pageable));
		Iterable<EmpLeave> empRecords = empLeaveRepository.findAll(pageable);
		return empRecords;
	}

	@Override
	public EmpLeave update(String logstr, EmpLeave tobemerged) {
		logger.debug(logstr + "update EmployeeLeave record " + tobemerged.fetchLogDetails());
		EmpLeave empFind = empLeaveRepository.findOne(tobemerged.getEmpid());
		EmpLeave mergeEmp = empLeaveRepository.save(empFind
				.mergeUpdates(tobemerged));
		logger.debug(logstr + "updated EmployeeLeave record " + mergeEmp.fetchLogDetails());
		return mergeEmp;
	}

	@Override
	public Boolean delete(String logstr, Long id) {
		logger.debug(logstr + "delete EmployeeLeave record at id " + id);
		empLeaveRepository.delete(id);
		logger.debug(logstr + "deleted EmployeeLeave record at id " + id);
		return true;
	}
}
