package com.mm.ms.bean;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.mm.ms.BaseAbstractBean;
import com.mm.ms.data.repo.HolidayRepository;
import com.mm.ms.entity.Holiday;

public class HolidayBean extends BaseAbstractBean<Holiday, Long> {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	HolidayRepository holidayRepository;

	@Override
	public Holiday create(String logstr, Holiday entity) {
		logger.debug(logstr + "create Holiday record " + entity.fetchLogDetails());
		Holiday holiday = holidayRepository.save(entity);
		logger.debug(logstr + "created Holiday record " + entity.fetchLogDetails());
		return holiday;
	}

	@Override
	public Holiday read(String logstr, Long id) {
		logger.debug(logstr + "get Holiday record at id " + id);
		Holiday holiday = holidayRepository.findOne(id);
		logger.debug(logstr + "got Holiday record " + holiday.fetchLogDetails());
		return holiday;
	}

	@Override
	public Iterable<Holiday> readAll(String logstr) {
		logger.debug(logstr + "get all Holiday records");
		Iterable<Holiday> holidayRecords = holidayRepository.findAll();
		return holidayRecords;
	}

	@Override
	public Iterable<Holiday> readAll(String logstr, Pageable pageable) {
		logger.debug(logstr + "get all Holiday records pageable "
				+ logdetails(pageable));
		Iterable<Holiday> holidayRecords = holidayRepository.findAll(pageable);
		return holidayRecords;
	}

	@Override
	public Holiday update(String logstr, Holiday tobemerged) {
		logger.debug(logstr + "update Holiday record " + tobemerged.fetchLogDetails());
		Holiday holidayFind = holidayRepository.findOne(tobemerged.getId());
		Holiday mergeHoliday = holidayRepository.save(holidayFind
				.mergeUpdates(tobemerged));
		logger.debug(logstr + "updated Holiday record " + mergeHoliday.fetchLogDetails());
		return mergeHoliday;
	}

	@Override
	public Boolean delete(String logstr, Long id) {
		logger.debug(logstr + "delete Holiday record at id " + id);
		holidayRepository.delete(id);
		logger.debug(logstr + "deleted Holiday record at id " + id);
		return true;
	}
	//readall by year
	public List<Holiday> readAllbyYYYY(String logstr) {
		logger.debug(logstr + "get all Holiday records YYYY ");
		Date fdate=new Date();
		fdate.setDate(1);
		fdate.setMonth(1);
		Date ldate=new Date();
		ldate.setDate(31);
		ldate.setMonth(12);
		List<Holiday> holidayRecords = holidayRepository.fetchByDate(fdate, ldate);
		
		return holidayRecords;
	}
}
