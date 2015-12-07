package com.mm.ms.rest;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ms.BaseAbstractController;
import com.mm.ms.bean.EmpLeaveBean;
import com.mm.ms.entity.EmpLeave;
import com.mm.ms.util.Const;
import com.mm.ms.util.LogUtil;
import com.mm.ms.util.RespMsgUtil;
import com.mm.ms.util.ResponseMsg;

@RestController
@RequestMapping("/empleave")
public class EmpLeaveController extends BaseAbstractController<EmpLeave, Long>{	
	
	public static final String MS_CODE = "EMPLEAVE";
	//TODO Change below line based on your Microservice if not correct
	public static final String LOGSTR_MS = Const.LOGSTR_FOMS;
	private String loggedInUser = Const.DEFAULT_USER;
		
	private static final String CREATE_SUCCESS = "Successfully created Empleave.";
	private static final String CREATE_FAILED = "Empleave creation failed.";
		
	private static final String RETRIEVE_SUCCESS = "Successfully retrieved Empleave record/s.";
	private static final String RETRIEVE_FAILED = "Empleave retrieval failed.";
		
	private static final String UPDATE_SUCCESS = "Successfully updated Empleave.";
	private static final String UPDATE_FAILED = "Empleave update failed.";
		
	private static final String DELETE_SUCCESS = "Successfully deleted Empleave.";
	private static final String DELETE_FAILED = "Empleave deletion failed.";
	
	@Autowired
	LogUtil logUtil;
	
	@Autowired
	EmpLeaveBean empLeaveBean;
	
	@Autowired
	RespMsgUtil respMsgUtil;

	@Override
	public ResponseEntity<EmpLeave> create(@RequestBody EmpLeave inputentity) {
		ResponseEntity<EmpLeave> userResponse;
		HttpHeaders headers = new HttpHeaders();
		
		EmpLeave user = empLeaveBean.create(logUtil.getPreStr(LOGSTR_MS, loggedInUser), inputentity);
		
		if (null != user){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<EmpLeave>(user, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<EmpLeave>( inputentity, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}

	@Override
	public ResponseEntity<EmpLeave> read(@PathVariable(value="id")Long id) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<EmpLeave> appUserResponse = null;
		HttpHeaders headers = new HttpHeaders();
		EmpLeave appUser = null;
		try {
			appUser = empLeaveBean.read(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);

			if (null != appUser) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS));
				appUserResponse = new ResponseEntity<EmpLeave>(appUser, headers, HttpStatus.OK);
			} 
			} catch (Exception e) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED));
				appUserResponse = new ResponseEntity<EmpLeave>(appUser, headers, HttpStatus.NOT_FOUND);
			}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<EmpLeave>> readAll() {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<EmpLeave>> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Iterable<EmpLeave> appUserRecords = null;
		try {
			appUserRecords = empLeaveBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser));
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<EmpLeave>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<EmpLeave>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<EmpLeave>> readAllPageable(
			@PathParam("firstresult") Integer firstresult,
			@PathParam("maxresult") Integer maxresult,
			@PathParam("sortdir") String sortdir,
			@PathParam("sortfield") String sortfield) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<EmpLeave>> appUserResponse;
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
		
		Iterable<EmpLeave> appUserRecords = null;
		try {
			appUserRecords = empLeaveBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser),pageable);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<EmpLeave>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<EmpLeave>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<EmpLeave> update(@RequestBody EmpLeave tobemerged) {
		ResponseEntity<EmpLeave> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		EmpLeave appUser;
		try {
			appUser = empLeaveBean.update(logUtil.getPreStr(LOGSTR_MS, loggedInUser), tobemerged);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, UPDATE_SUCCESS) );
			appUserResponse = new ResponseEntity<EmpLeave>(appUser, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, UPDATE_FAILED) );
			appUserResponse = new ResponseEntity<EmpLeave>( tobemerged, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<EmpLeave> delete(@PathVariable(value="id") Long id) {
		ResponseEntity<EmpLeave> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		try{
			empLeaveBean.delete(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, DELETE_SUCCESS) );
			appUserResponse = new ResponseEntity<EmpLeave>(null, headers, HttpStatus.OK);
		}catch(Exception e){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, DELETE_FAILED) );
			appUserResponse = new ResponseEntity<EmpLeave>(null, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}	
}