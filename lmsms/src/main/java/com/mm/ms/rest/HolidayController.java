package com.mm.ms.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mm.ms.BaseAbstractController;
import com.mm.ms.bean.HolidayBean;
import com.mm.ms.entity.Holiday;
import com.mm.ms.util.Const;
import com.mm.ms.util.LogUtil;
import com.mm.ms.util.RespMsgUtil;
import com.mm.ms.util.ResponseMsg;

@RestController
@RequestMapping("/holiday")
public class HolidayController extends BaseAbstractController<Holiday, Long>{	
	
	public static final String MS_CODE = "HOLIDAY";
	//TODO Change below line based on your Microservice if not correct
	public static final String LOGSTR_MS = Const.LOGSTR_FOMS;
	private String loggedInUser = Const.DEFAULT_USER;
		
	private static final String CREATE_SUCCESS = "Successfully created Holiday.";
	private static final String CREATE_FAILED = "Holiday creation failed.";
		
	private static final String RETRIEVE_SUCCESS = "Successfully retrieved Holiday record/s.";
	private static final String RETRIEVE_FAILED = "Holiday retrieval failed.";
		
	private static final String UPDATE_SUCCESS = "Successfully updated Holiday.";
	private static final String UPDATE_FAILED = "Holiday update failed.";
		
	private static final String DELETE_SUCCESS = "Successfully deleted Holiday.";
	private static final String DELETE_FAILED = "Holiday deletion failed.";
	
	@Autowired
	LogUtil logUtil;
	
	@Autowired
	HolidayBean holidayBean;
	
	@Autowired
	RespMsgUtil respMsgUtil;

	@Override
	public ResponseEntity<Holiday> create(@RequestBody Holiday inputentity) {
		ResponseEntity<Holiday> userResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Holiday user = holidayBean.create(logUtil.getPreStr(LOGSTR_MS, loggedInUser), inputentity);
		
		if (null != user){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, CREATE_SUCCESS) );
			userResponse = new ResponseEntity<Holiday>(user, headers, HttpStatus.CREATED);
		} else{
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, CREATE_FAILED) );
			userResponse = new ResponseEntity<Holiday>( inputentity, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return userResponse;
	}

	@Override
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Holiday> read(@PathVariable(value="id")Long id) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Holiday> appUserResponse = null;
		HttpHeaders headers = new HttpHeaders();
		Holiday appUser = null;
		try {
			appUser = holidayBean.read(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);

			if (null != appUser) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS));
				appUserResponse = new ResponseEntity<Holiday>(appUser, headers, HttpStatus.OK);
			} 
			} catch (Exception e) {
				headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED));
				appUserResponse = new ResponseEntity<Holiday>(appUser, headers, HttpStatus.NOT_FOUND);
			}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<Holiday>> readAll() {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<Holiday>> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Iterable<Holiday> appUserRecords = null;
		try {
			appUserRecords = holidayBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser));
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<Holiday>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<Holiday>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Iterable<Holiday>> readAllPageable(
			@PathParam("firstresult") Integer firstresult,
			@PathParam("maxresult") Integer maxresult,
			@PathParam("sortdir") String sortdir,
			@PathParam("sortfield") String sortfield) {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<Iterable<Holiday>> appUserResponse;
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
		
		Iterable<Holiday> appUserRecords = null;
		try {
			appUserRecords = holidayBean.readAll(logUtil.getPreStr(LOGSTR_MS, loggedInUser),pageable);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<Iterable<Holiday>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<Iterable<Holiday>>(appUserRecords, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Holiday> update(@RequestBody Holiday tobemerged) {
		ResponseEntity<Holiday> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		Holiday appUser;
		try {
			appUser = holidayBean.update(logUtil.getPreStr(LOGSTR_MS, loggedInUser), tobemerged);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_201, UPDATE_SUCCESS) );
			appUserResponse = new ResponseEntity<Holiday>(appUser, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_500, UPDATE_FAILED) );
			appUserResponse = new ResponseEntity<Holiday>( tobemerged, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return appUserResponse;
	}

	@Override
	public ResponseEntity<Holiday> delete(@PathVariable(value="id") Long id) {
		ResponseEntity<Holiday> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		try{
			holidayBean.delete(logUtil.getPreStr(LOGSTR_MS, loggedInUser), id);
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, DELETE_SUCCESS) );
			appUserResponse = new ResponseEntity<Holiday>(null, headers, HttpStatus.OK);
		}catch(Exception e){
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, DELETE_FAILED) );
			appUserResponse = new ResponseEntity<Holiday>(null, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}
	
	@RequestMapping(value="YYYY", method = RequestMethod.GET)
	public ResponseEntity<List<Holiday>> readAllbyYYYY() {
		//TODO Comment/Uncomment below line based on your requirement
		ResponseEntity<List<Holiday>> appUserResponse;
		HttpHeaders headers = new HttpHeaders();
		
		List<Holiday> appUserRecords = null;
		try {
			appUserRecords = holidayBean.readAllbyYYYY(logUtil.getPreStr(LOGSTR_MS, loggedInUser));
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_200, RETRIEVE_SUCCESS) );
			appUserResponse = new ResponseEntity<List<Holiday>>(appUserRecords, headers, HttpStatus.OK);
		} catch (Exception e) {
			headers.add(ResponseMsg.HTTP_HEADER_NAME, respMsgUtil.getStr(MS_CODE, ResponseMsg.HTTP_404, RETRIEVE_FAILED) );
			appUserResponse = new ResponseEntity<List<Holiday>>(null, headers, HttpStatus.NOT_FOUND);
		}
		return appUserResponse;
	}
}