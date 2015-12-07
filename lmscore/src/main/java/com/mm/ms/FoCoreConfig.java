package com.mm.ms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mm.ms.bean.EmpBean;
import com.mm.ms.bean.EmpLeaveBean;
import com.mm.ms.bean.HolidayBean;
import com.mm.ms.bean.LMMBean;
import com.mm.ms.bean.LeaveBean;
import com.mm.ms.bean.LeaveTakenBean;
import com.mm.ms.util.LogUtil;
import com.mm.ms.util.RespMsgUtil;

@Configuration
public class FoCoreConfig {

	@Bean
	public EmpBean empBean() {
		EmpBean empBean = new EmpBean();
		return empBean;
	}

	@Bean
	public EmpLeaveBean empLeaveBean() {
		EmpLeaveBean empLeaveBean = new EmpLeaveBean();
		return empLeaveBean;
	}
	
	@Bean
	public HolidayBean holidayBean() {
		HolidayBean holidayBean = new HolidayBean();
		return holidayBean;
	}
	
	@Bean
	public LeaveTakenBean leaveTakenBean() {
		LeaveTakenBean leaveTakenBean = new LeaveTakenBean();
		return leaveTakenBean;
	}
	
	@Bean
	public LMMBean lMMBean() {
		LMMBean lMMBean = new LMMBean();
		return lMMBean;
	}
	
	@Bean
	public LeaveBean leaveBean() {
		LeaveBean leaveBean = new LeaveBean();
		return leaveBean;
	}
	
	@Bean
	public LogUtil logUtil() {
		LogUtil logUtil = new LogUtil();
		return logUtil;
	}

	@Bean
	public RespMsgUtil respMsgUtil() {
		RespMsgUtil respMsgUtil = new RespMsgUtil();
		return respMsgUtil;
	}

	
}
