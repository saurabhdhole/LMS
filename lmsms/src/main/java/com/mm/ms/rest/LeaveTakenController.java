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
import com.mm.ms.bean.LeaveTakenBean;
import com.mm.ms.entity.LeaveTaken;
import com.mm.ms.util.Const;
import com.mm.ms.util.LogUtil;
import com.mm.ms.util.RespMsgUtil;
import com.mm.ms.util.ResponseMsg;

@RestController
@RequestMapping("/leavetaken")
public class LeaveTakenController extends BaseAbstractController<LeaveTaken, Long>{	
	
	public static final String MS_CODE = "LEAVETAKEN";
	//TODO Change below line based on your Microservice if not correct
	public static final String LOGSTR_MS = Const.LOGSTR_FOMS;
	private String loggedInUser = Const.DEFAULT_USER;
		
	private static final String CREATE_SUCCESS = "Successfully created leavetaken.";
	private static final String CREATE_FAILED = "leavetaken creation failed.";
		
	private static final String RETRIEVE_SUCCESS = "Successfully retrieved leavetaken record/s.";
	private static final String RETRIEVE_FAILED = "leavetaken retrieval failed.";
		
	private static final String UPDATE_SUCCESS = "Successfully updated leavetaken.";
	private static final String UPDATE_FAILED = "leavetaken update failed.";
		
	private static final String DELETE_SUCCESS = "Successfully deleted leavetaken.";
	private static final String DELETE_FAILED = "leavetaken deletion failed.";
	
	@Autowired
	LogUtil logUtil;
	
	@Autowired
	LeaveTakenBean leaveTakenBean;
	
	@Autowired
	RespMsgUtil respMsgUtil;

	@Override
	public ResponseEntity<LeaveTaken> create(@RequestBody LeaveTaken inputentity) {
		ResponseEntity<LeaveTaken> userResponse;
		HttpHeaders headers = new HttpHeaders();
		
		LeaveTaken user = leaveTakenBean.create(logUtil.getPreStr(LOGSTR_MS, loggedInUser), inputentity);
		
		if (null != user){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<LeaveTaken>(user, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<LeaveTaken>( inputentity, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}

	@Override
	@ExceptionHandler(Exception.class)
	public ResponseEntity<LeaveTaken> read(@PathVariable(value="id")Long id) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<LeaveTaken> appUserResponse = null;
		HttpHeaders headers = new HttpHeaders();
		LeaveTaken appUser = null;
		try {
			appUser = leaveTakenBean.read(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);

			if (null != appUser) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS));
				appUserResponse = new ResponseEntity<LeaveTaken>(appUser, headers, HttpStatus.OK);
			} 
			} catch (Exception e) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED));
				appUserResponse = new ResponseEntity<LeaveTaken>(appUser, headers, HttpStatus.NOT_FOUND);
			}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<LeaveTaken>> readAll() {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<LeaveTaken>> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Iterable<LeaveTaken> appUserRecords = null;
		try {
			appUserRecords = leaveTakenBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser));
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<LeaveTaken>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<LeaveTaken>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<LeaveTaken>> readAllPageable(
			@PathParam("firstresult") Integer firstresult,
			@PathParam("maxresult") Integer maxresult,
			@PathParam("sortdir") String sortdir,
			@PathParam("sortfield") String sortfield) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<LeaveTaken>> appUserResponse;
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
		
		Iterable<LeaveTaken> appUserRecords = null;
		try {
			appUserRecords = leaveTakenBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser),pageable);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<LeaveTaken>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<LeaveTaken>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<LeaveTaken> update(@RequestBody LeaveTaken tobemerged) {
		ResponseEntity<LeaveTaken> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		LeaveTaken appUser;
		try {
			appUser = leaveTakenBean.update(logUtil.getPreStr(LOGSTR_MS, loggedInUser), tobemerged);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, UPDATE_SUCCESS) );
			appUserResponse = new ResponseEntity<LeaveTaken>(appUser, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, UPDATE_FAILED) );
			appUserResponse = new ResponseEntity<LeaveTaken>( tobemerged, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<LeaveTaken> delete(@PathVariable(value="id") Long id) {
		ResponseEntity<LeaveTaken> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		try{
			leaveTakenBean.delete(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, DELETE_SUCCESS) );
			appUserResponse = new ResponseEntity<LeaveTaken>(null, headers, HttpStatus.OK);
		}catch(Exception e){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, DELETE_FAILED) );
			appUserResponse = new ResponseEntity<LeaveTaken>(null, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}	
}