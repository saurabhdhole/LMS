package com.mm.ms.rest;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.jooq.exception.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ms.bean.EmpBean;
import com.mm.ms.bean.LeaveBean;
import com.mm.ms.dto.EmployeeDto;
import com.mm.ms.dto.WeekLeaveDto;
import com.mm.ms.entity.Emp;
import com.mm.ms.util.Const;
import com.mm.ms.util.LogUtil;
import com.mm.ms.util.RespMsgUtil;
import com.mm.ms.util.ResponseMsg;

@RestController
@RequestMapping("/leave")
public class LeaveController {
	public static final String MS_CODE = "USER";
	//TODO Change below line based on your Microservice if not correct
	public static final String LOGSTR_MS = Const.LOGSTR_FOMS;
	private String loggedInUser = Const.DEFAULT_USER;
		
	private static final String CREATE_SUCCESS = "Successfully created user.";
	private static final String CREATE_FAILED = "User creation failed.";
		
	private static final String RETRIEVE_SUCCESS = "Successfully retrieved user record/s.";
	private static final String RETRIEVE_FAILED = "User retrieval failed.";
		
	private static final String UPDATE_SUCCESS = "Successfully updated user.";
	private static final String UPDATE_FAILED = "User update failed.";
		
	private static final String DELETE_SUCCESS = "Successfully deleted user.";
	private static final String DELETE_FAILED = "User deletion failed.";
	
	@Autowired
	LogUtil logUtil;
	
	@Autowired
	LeaveBean leaveBean;
	
	@Autowired
	RespMsgUtil respMsgUtil;

	@RequestMapping(value="week", method = RequestMethod.PATCH)
	public ResponseEntity<WeekLeaveDto> createweekentry(@RequestBody WeekLeaveDto inputentity) {
		ResponseEntity<WeekLeaveDto> userResponse;
		HttpHeaders headers = new HttpHeaders();
		
		WeekLeaveDto user = leaveBean.weekAttendense(logUtil.getPreStr(LOGSTR_MS, loggedInUser), inputentity);
		
		if (null != user){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<WeekLeaveDto>(user, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<WeekLeaveDto>( inputentity, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}
	
	@RequestMapping(value="login/{name}/{pass}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeDto> login(@PathVariable(value="name")String name,@PathVariable(value="pass")String pass) {
		ResponseEntity<EmployeeDto> userResponse;
		HttpHeaders headers = new HttpHeaders();
		
		String user = leaveBean.Login(logUtil.getPreStr(LOGSTR_MS, loggedInUser),name,pass);
		EmployeeDto emp=new EmployeeDto();
		emp.setEmpid(1L);
		emp.setEmpname("HR");
		if (null != user){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<EmployeeDto>(emp, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<EmployeeDto>( null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}
	//creating exel sheet
	@SuppressWarnings("unused")
	//@Produces("application/vnd.ms-excel")
	@RequestMapping(value="exel/{mm}/{yyyy}", method = RequestMethod.GET, produces="application/vnd.ms-excel")
	public ResponseEntity<InputStreamResource> createExel(@PathVariable(value="mm")int mm,@PathVariable(value="yyyy")int yyyy) throws java.io.IOException{
		ResponseEntity<File> userResponse;
		HttpHeaders respHeaders = new HttpHeaders();
		
		File filename=leaveBean.createExel(mm,yyyy);
		
		respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//respHeaders.setContentType("application/pdf");
	//	respHeaders.setContentType("application/vnd.ms-excel");
	    respHeaders.setContentLength(12345678);
	    respHeaders.setContentDispositionFormData("attachment", "Fund.csv");

	    InputStreamResource isr = new InputStreamResource(new FileInputStream(filename));
	    return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
//		ClassPathResource pdfFile = new ClassPathResource(filename.toString());
//
//	    return ResponseEntity
//	            .ok()
//	            .contentLength(pdfFile.contentLength())
//	            .contentType(
//	                    MediaType.parseMediaType("application/octet-stream"))
//	            .body(new InputStreamResource(pdfFile.getInputStream()));
		
//		if (true){
//			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
//			userResponse = new ResponseEntity<File>(filename, headers, HttpStatus.CREATED);
//		} else{
//			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
//			userResponse = new ResponseEntity<File>( null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
//		}		
		//return userResponse;
	}

	//Prev Check of date
	@RequestMapping(value="prevCheck/{empid}/{start}/{stop}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeDto> prevCheck(@PathVariable(value="empid")Long empid,@PathVariable(value="start")int start,@PathVariable(value="stop")int stop) {
		ResponseEntity<EmployeeDto> userResponse;
		HttpHeaders headers = new HttpHeaders();
		EmployeeDto dtoresponce=new EmployeeDto();
		String str=leaveBean.checkingThePrevAttend(empid, start,stop);
		dtoresponce.setStatus(str);
		if (str!=null){
			
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<EmployeeDto>(dtoresponce, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<EmployeeDto>( null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}
	
	//Prev Check of date
	@RequestMapping(value="prevchecklastMonth/{empid}/{date}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeDto> prevCheckfrmonthlastday(@PathVariable(value="empid")Long empid,@PathVariable(value="date")Date date) {
			ResponseEntity<EmployeeDto> userResponse;
			HttpHeaders headers = new HttpHeaders();
			EmployeeDto dtoresponce=new EmployeeDto();
			String str=leaveBean.checkingThePrevAttendLastday(empid, date,date.getDate());
			dtoresponce.setStatus(str);
			if (str!=null){
				
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
				userResponse = new ResponseEntity<EmployeeDto>(dtoresponce, headers, HttpStatus.CREATED);
			} else{
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
				userResponse = new ResponseEntity<EmployeeDto>( null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
			}		
			return userResponse;
		}
}
