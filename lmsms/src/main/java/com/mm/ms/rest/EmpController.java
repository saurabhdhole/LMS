package com.mm.ms.rest;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ms.BaseAbstractController;
import com.mm.ms.bean.EmpBean;
import com.mm.ms.bean.EmpLeaveBean;
import com.mm.ms.entity.Emp;
import com.mm.ms.entity.EmpLeave;
import com.mm.ms.util.Const;
import com.mm.ms.util.LogUtil;
import com.mm.ms.util.RespMsgUtil;
import com.mm.ms.util.ResponseMsg;

@RestController
@RequestMapping("/emp")
public class EmpController extends BaseAbstractController<Emp, Long>{	
	
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
	EmpBean empBean;
	
	@Autowired
	EmpLeaveBean empLeaveBean;
	
	@Autowired
	RespMsgUtil respMsgUtil;

	@Override
	public ResponseEntity<Emp> create(@RequestBody Emp inputentity) {
		ResponseEntity<Emp> userResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Emp user = empBean.create(logUtil.getPreStr(LOGSTR_MS, loggedInUser), inputentity);
		
		if (null != user){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<Emp>(user, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<Emp>( inputentity, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}

	@Override
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Emp> read(@PathVariable(value="id")Long id) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Emp> appUserResponse = null;
		HttpHeaders headers = new HttpHeaders();
		Emp appUser = null;
		try {
			appUser = empBean.read(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);

			if (null != appUser) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS));
				appUserResponse = new ResponseEntity<Emp>(appUser, headers, HttpStatus.OK);
			} 
			} catch (Exception e) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED));
				appUserResponse = new ResponseEntity<Emp>(appUser, headers, HttpStatus.NOT_FOUND);
			}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<Emp>> readAll() {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<Emp>> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Iterable<Emp> appUserRecords = null;
		try {
			appUserRecords = empBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser));
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<Emp>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<Emp>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<Emp>> readAllPageable(
			@PathParam("firstresult") Integer firstresult,
			@PathParam("maxresult") Integer maxresult,
			@PathParam("sortdir") String sortdir,
			@PathParam("sortfield") String sortfield) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<Emp>> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		// Default sort Direction and Field
		if (sortdir == null){
			sortdir = "DESC";
		}
		if (sortfield == null){
			//TODO Change it as per your requirement
			sortfield = "name";
		}
		Pageable pageable = new PageRequest(firstresult, maxresult,
				Sort.Direction.fromString(sortdir), sortfield);
		
		Iterable<Emp> appUserRecords = null;
		try {
			appUserRecords = empBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser),pageable);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<Emp>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<Emp>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Emp> update(@RequestBody Emp tobemerged) {
		ResponseEntity<Emp> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Emp appUser;
		try {
			appUser = empBean.update(logUtil.getPreStr(LOGSTR_MS, loggedInUser), tobemerged);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, UPDATE_SUCCESS) );
			appUserResponse = new ResponseEntity<Emp>(appUser, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, UPDATE_FAILED) );
			appUserResponse = new ResponseEntity<Emp>( tobemerged, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Emp> delete(@PathVariable(value="id") Long id) {
		ResponseEntity<Emp> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		try{
			empBean.delete(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);
			empLeaveBean.delete(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);
			Emp emp=new Emp();
			emp.setEmpid(id);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, DELETE_SUCCESS) );
			appUserResponse = new ResponseEntity<Emp>(emp, headers, HttpStatus.OK);
		}catch(Exception e){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, DELETE_FAILED) );
			appUserResponse = new ResponseEntity<Emp>(null, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}	
}