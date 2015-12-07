package com.mm.ms.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;

import com.mm.ms.data.repo.EmpRepository;
import com.mm.ms.data.repo.LMMRepository;
import com.mm.ms.dto.WeekLeaveDto;
import com.mm.ms.entity.Emp;
import com.mm.ms.entity.LMM;

public class LeaveBean {

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	LMMBean  lMMBean;
	
	@Autowired
	LMMRepository lMMRepository;
	
	@Autowired
	EmpRepository empRepository;
	
	@SuppressWarnings("deprecation")
	public WeekLeaveDto weekAttendense(String logstr,WeekLeaveDto entity) {
		logger.debug(logstr + " Entry ...Attendance Entry");
		int i=entity.getStart();
		StringBuilder sb=new StringBuilder("day");
		sb.append(i++);
		StringBuilder sb1=new StringBuilder("day");
		sb1.append(i++);
		StringBuilder sb2=new StringBuilder("day");
		sb2.append(i++);
		StringBuilder sb3=new StringBuilder("day");
		sb3.append(i++);
		StringBuilder sb4=new StringBuilder("day");
		sb4.append(i++);
		StringBuilder sb5=new StringBuilder("day");
		sb5.append(i++);
		StringBuilder sb6=new StringBuilder("day");
		sb6.append(i++);
		
		
		//System.out.println("#######"+date.getDate());
		// entry in lmm
		Date fdate=new Date();
		Date ldate=new Date();
		int mon;
		if("current".equals(entity.getStatus()))
		{
			mon=fdate.getMonth()+1;
		}
		else
		{
			mon=fdate.getMonth();
			fdate.setMonth(mon-1);
			ldate.setMonth(mon-1);	
		}
		int day=0;
		//int flag = 0;
		if((mon)==2)
		{
			if(fdate.getYear()%4==0)
				day=29;
			else
				day=28;
		}
		else
		{
			if((mon)==1||(mon)==3||(mon)==5||(mon)==7||(mon)==8||(mon)==10||(mon)==12)
				day=31;
			else
				day=30;
		}
		//set first and last date of month
		fdate.setDate(1);
		ldate.setDate(day);
		//Note: query not fetching the exact data ...it is omiting the extremities
		//LMM lmmrecord=lMMRepository.fetchbydate(fdate, ldate, entity.getEmpid());
		LMM lmmrecord=lMMRepository.fetchbydate(fdate, ldate,entity.getEmpid());
//		generateSetter("Day1");
		int start=entity.getStart();
		int stop=entity.getStop();
				
		try {
			if(start<=stop)
			{
				if(entity.getSunday()=="")
					entity.setSunday("A");
				BeanUtils.setProperty(lmmrecord,sb.toString(),entity.getSunday());
				start++;
			}
			if(start<=stop)
			{
				if(entity.getMonday()=="")
					entity.setMonday("A");
				BeanUtils.setProperty(lmmrecord,sb1.toString(),entity.getMonday());
				start++;
			}
			if(start<=stop)
			{
				if(entity.getTuesday()=="")
					entity.setTuesday("A");
				BeanUtils.setProperty(lmmrecord,sb2.toString(),entity.getTuesday());
				start++;
			}	
			if(start<=stop)
			{
				if(entity.getWednesday()=="")
					entity.setWednesday("A");
				BeanUtils.setProperty(lmmrecord,sb3.toString(),entity.getWednesday());
				start++;
			}
			if(start<=stop)
			{
				if(entity.getThursday()=="")
					entity.setThursday("A");
				BeanUtils.setProperty(lmmrecord,sb4.toString(),entity.getThursday());
				start++;
			}
			if(start<=stop)
			{
				if(entity.getFriday()=="")
					entity.setFriday("A");
				BeanUtils.setProperty(lmmrecord,sb5.toString(),entity.getFriday());
				start++;
			}
			if(start<=stop)
			{
				if(entity.getSaturday()=="")
					entity.setSaturday("A");
				BeanUtils.setProperty(lmmrecord,sb6.toString(),entity.getSaturday());
				start++;
			}
			lMMRepository.save(lmmrecord);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
		
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public String generateSetter(String day) {
		StringBuilder set = new StringBuilder();
		set.append("set");
		set.append(day);
		return set.toString();
	} 
	
	//function to get month
	public static String theMonth(int month)
	{
	    String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
	    return monthNames[month];
	}
	
	public String Login(String logstr,String name,String pass)
	{
		if(name.equals("admin") &&pass.equals("admin"))
			return "valid";
		else
			return "notvalid";
		
	}
	@SuppressWarnings("deprecation")
	public File createExel(int mm, int yyyy)
	{
		File fileName = new File("C:/Users/saurabh/Documents/workspace/LMS/lmscore/Fund.xls");
		try {
			
	        FileOutputStream fos = new FileOutputStream(fileName);
	        HSSFWorkbook  workbook = new HSSFWorkbook();            

	        HSSFSheet sheet = workbook.createSheet("fund");  
	        HSSFFont font=workbook.createFont();
	        font.setBold(true);
	        CellStyle style = workbook.createCellStyle();
	        style.setFont(font);
	        //read lmm all
	        Iterable<Emp> emprecords=empRepository.findAll();
	        int i=0;
	        Row row = sheet.createRow(i);
	        
	      //  row.setRowStyle(style);
	        
	        Cell cell0 = row.createCell(i);
	        cell0.setCellValue("Sr.No");
	        row.getCell(0).setCellStyle(style);
	        Cell cell1 = row.createCell(1);
	       
	        cell1.setCellValue("Emp.Id");
	        row.getCell(1).setCellStyle(style);
	        Cell cell2 = row.createCell(2);
	        cell2.setCellValue("Name");
	        row.getCell(2).setCellStyle(style);
	        Cell cell3 = row.createCell(3);
	        cell3.setCellValue("Location");
	        row.getCell(3).setCellStyle(style);
	        Cell cell4 = row.createCell(4);
	        cell4.setCellValue("Department");
	        row.getCell(4).setCellStyle(style);
	        Date date=new Date();
	        date.setDate(2);
	        date.setYear(yyyy);
	        date.setMonth(mm);
	        int day;
	        int mon=mm+1;
	        if((mon)==2)
			{
				if(date.getYear()%4==0)
					day=29;
				else
					day=28;
			}
			else
			{
				if((mon)==1||(mon)==3||(mon)==5||(mon)==7||(mon)==8||(mon)==10||(mon)==12)
					day=31;
				else
					day=30;
			}
	    
	        for(int j=0;j<day;j++)
	        {
	        	
	        	Cell cell5 = row.createCell(j+5);
	        	row.getCell(j+5).setCellStyle(style);
	        	StringBuilder day1=new StringBuilder();
	        	day1.append(j+1);
	        	day1.append(" "+theMonth(date.getMonth()));
		        cell5.setCellValue(day1.toString());	        	
	        }
	        i=1;
	        //Emp data start
	        Date fdate=new Date(yyyy, mm, 1);
	        Date ldate=new Date(yyyy, mm, day);
	        
	        System.out.println(fdate.getYear());
	        System.out.println(ldate);
	        for (Emp emp : emprecords) 
	        {
	        	Row row1 = sheet.createRow(i);
	        	List<LMM> lmmrc=lMMRepository.fetchbydate2(fdate, ldate);
	        	LMM lmmrecord=lMMRepository.fetchbydate(fdate, ldate, emp.getEmpid());
	        	i++;
	        	Cell cel0 = row1.createCell(0);
		        cel0.setCellValue(i);
		     
		        Cell cel1 = row1.createCell(1);
		        cel1.setCellValue(emp.getEmpid());
		        
		        Cell cel2 = row1.createCell(2);
		        cel2.setCellValue(emp.getEmpname());
		        
		        Cell cel3 = row1.createCell(3);
		        cel3.setCellValue(emp.getLocation());
		        
		        Cell cel4 = row1.createCell(4);
		        cel4.setCellValue(emp.getDepartment());
		        
		        if(lmmrecord==null)
		        	continue;
		        for(int k=0;k<day;k++)
		        {
		        	
		    		StringBuilder sb=new StringBuilder("day");
		    		sb.append(k+1);
		        	Cell cell5 = row1.createCell(k+5);
			        try {
						cell5.setCellValue(BeanUtils.getProperty(lmmrecord,sb.toString()));
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }      	
			}
	  	        
	        try
	        {
	        	workbook.write(fos);
	        	//InputStreamResource ios=new InputStreamResource(inputStream);
	        	fos.flush();
	        	fos.close();
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		return fileName;
		
	}
	
	//Checking the previous record.
	public String checkingThePrevAttend(Long empid,int start,int stop)
	{
		Date fdate=new Date();
		Date ldate=new Date();
		int mon=fdate.getMonth()+1;
		int day=0;
		int flag = 0;
		if((mon)==2)
		{
			if(fdate.getYear()%4==0)
				day=29;
			else
				day=28;
		}
		else
		{
			if((mon)==1||(mon)==3||(mon)==5||(mon)==7||(mon)==8||(mon)==10||(mon)==12)
				day=31;
			else
				day=30;
		}
		//set first and last date of month
		fdate.setDate(1);
		ldate.setDate(day);
		
		LMM lmm=lMMRepository.fetchbydate(fdate, ldate,empid);
	//	List<LMM> lmmrc=lMMRepository.fetchbydate2(fdate, ldate);
		
		//Check Lmm entry exist fr perticular emp
		if(lmm==null)
		{
			//create entry in lmm
			LMM lmmrecord=new LMM();
			fdate.setDate(2);
			lmmrecord.setDate(fdate);
			lmmrecord.setEmpid(empid);
			lMMRepository.save(lmmrecord);
			flag=1;
		}
		else
		{
			StringBuilder sb=new StringBuilder("day");
			sb.append(start);
			//	BeanUtils.setProperty(lmm,sb.toString(),entity.getMonday());
			
			try {
					if(BeanUtils.getProperty(lmm,sb.toString())==null)
						flag=1;
				} catch (Exception e) {
			// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		if(flag==1)
			return "prevRemain";
		else
			return "complete";
		}
	
	
	//checking last day of prev month only when current date is less than 7
	@SuppressWarnings("deprecation")
	public String checkingThePrevAttendLastday(Long empid,Date date,int day)
	{
		Date fdate= new Date();
		Date ldate=new Date();
		fdate.setDate(1);
		fdate.setMonth(date.getMonth());
		
		ldate.setMonth(date.getMonth());
		ldate.setDate(day);
		
		Date testDate=new Date();
		if(testDate.getMonth()==1)
		{
			fdate.setYear(testDate.getYear()-1);
			ldate.setYear(testDate.getYear()-1);
		}
		//fdate.setDate(1);
		
		LMM lmm=lMMRepository.fetchbydate(fdate, ldate,empid);
	//	List<LMM> lmmrc=lMMRepository.fetchbydate2(fdate, ldate);
		int flag = 0;
		//Check Lmm entry exist fr perticular emp
		if(lmm==null)
		{
			flag=0;
		}
		else
		{
			StringBuilder sb=new StringBuilder("day");
			sb.append(date.getDate());
			//	BeanUtils.setProperty(lmm,sb.toString(),entity.getMonday());
			
			try {
					if(BeanUtils.getProperty(lmm,sb.toString())==null)
						flag=1;
				} catch (Exception e) {
			// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		if(flag==1)
			return "prevRemain";
		else
			return "complete";
		}
}

