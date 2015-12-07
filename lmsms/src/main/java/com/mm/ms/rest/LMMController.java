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
import com.mm.ms.bean.LMMBean;
import com.mm.ms.entity.LMM;
import com.mm.ms.util.Const;
import com.mm.ms.util.LogUtil;
import com.mm.ms.util.RespMsgUtil;
import com.mm.ms.util.ResponseMsg;

@RestController
@RequestMapping("/lmm")
public class LMMController extends BaseAbstractController<LMM, Long>{	
	
	public static final String MS_CODE = "LMM";
	//TODO Change below line based on your Microservice if not correct
	public static final String LOGSTR_MS = Const.LOGSTR_FOMS;
	private String loggedInUser = Const.DEFAULT_USER;
		
	private static final String CREATE_SUCCESS = "Successfully created LMM.";
	private static final String CREATE_FAILED = "LMM creation failed.";
		
	private static final String RETRIEVE_SUCCESS = "Successfully retrieved LMM record/s.";
	private static final String RETRIEVE_FAILED = "LMM retrieval failed.";
		
	private static final String UPDATE_SUCCESS = "Successfully updated LMM.";
	private static final String UPDATE_FAILED = "LMM update failed.";
		
	private static final String DELETE_SUCCESS = "Successfully deleted LMM.";
	private static final String DELETE_FAILED = "LMM deletion failed.";
	
	@Autowired
	LogUtil logUtil;
	
	@Autowired
	LMMBean lMMBean;
	
	@Autowired
	RespMsgUtil respMsgUtil;

	@Override
	public ResponseEntity<LMM> create(@RequestBody LMM inputentity) {
		ResponseEntity<LMM> userResponse;
		HttpHeaders headers = new HttpHeaders();
		
		LMM user = lMMBean.create(logUtil.getPreStr(LOGSTR_MS, loggedInUser), inputentity);
		
		if (null != user){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<LMM>(user, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<LMM>( inputentity, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}

	@Override
	@ExceptionHandler(Exception.class)
	public ResponseEntity<LMM> read(@PathVariable(value="id")Long id) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<LMM> appUserResponse = null;
		HttpHeaders headers = new HttpHeaders();
		LMM appUser = null;
		try {
			appUser = lMMBean.read(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);

			if (null != appUser) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS));
				appUserResponse = new ResponseEntity<LMM>(appUser, headers, HttpStatus.OK);
			} 
			} catch (Exception e) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED));
				appUserResponse = new ResponseEntity<LMM>(appUser, headers, HttpStatus.NOT_FOUND);
			}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<LMM>> readAll() {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<LMM>> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Iterable<LMM> appUserRecords = null;
		try {
			appUserRecords = lMMBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser));
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<LMM>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<LMM>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<LMM>> readAllPageable(
			@PathParam("firstresult") Integer firstresult,
			@PathParam("maxresult") Integer maxresult,
			@PathParam("sortdir") String sortdir,
			@PathParam("sortfield") String sortfield) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<LMM>> appUserResponse;
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
		
		Iterable<LMM> appUserRecords = null;
		try {
			appUserRecords = lMMBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser),pageable);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<LMM>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<LMM>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<LMM> update(@RequestBody LMM tobemerged) {
		ResponseEntity<LMM> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		LMM appUser;
		try {
			appUser = lMMBean.update(logUtil.getPreStr(LOGSTR_MS, loggedInUser), tobemerged);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, UPDATE_SUCCESS) );
			appUserResponse = new ResponseEntity<LMM>(appUser, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, UPDATE_FAILED) );
			appUserResponse = new ResponseEntity<LMM>( tobemerged, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<LMM> delete(@PathVariable(value="id") Long id) {
		ResponseEntity<LMM> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		try{
			lMMBean.delete(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, DELETE_SUCCESS) );
			appUserResponse = new ResponseEntity<LMM>(null, headers, HttpStatus.OK);
		}catch(Exception e){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, DELETE_FAILED) );
			appUserResponse = new ResponseEntity<LMM>(null, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}	
}