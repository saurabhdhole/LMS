var AttendenceService = angular.module('attendSrvc', []);

AttendenceService.factory('attendencesrvc',['$http',  function($http) {
	var attendencesrvc={};
	var ur="http://localhost:8080/lmsms/emp";
	var urw="http://localhost:8080/lmsms/leave/week";
	var urp="http://localhost:8080/lmsms/leave/prevCheck/";
	var urm="http://localhost:8080/lmsms/leave/prevchecklastMonth/";
	
	
		
	///Fetch all emp record
	attendencesrvc.fetchEmprecords=function(successFn,errorFn) {
	 	
		var request = {
	             method: 'GET',
	             url: ur
			}
		
	     console.log(request);
	     $http(request)	         
	     .success(function(data, status, headers, config){
	         console.log("data fetched successfully");
	         
	         successFn(data,status,headers,config);
	     })
	     .error(function(data, status, headers, config){
	         console.log("Data is not fetched");
	         errorFn(data,status,headers,config);
	     });    
	};
	
	//Checking prev date record
	attendencesrvc.checklmmrc=function(empid,start,stop,successFn,errorFn) {
	 	
		var request = {
	             method: 'GET',
	             url: urp+empid+"/"+start+"/"+stop
			}
		
	     console.log(request);
	     $http(request)	         
	     .success(function(data, status, headers, config){
	         console.log("data fetched successfully");
	         
	         successFn(data,status,headers,config);
	     })
	     .error(function(data, status, headers, config){
	         console.log("Data is not fetched");
	         errorFn(data,status,headers,config);
	     });    
	};
	
	//adding attendance for employee
	attendencesrvc.addWeekAttendance=function(empid,start,stop,status,sun,mon,tue,wed,thur,fri,sat,successFn,errorFn) {
	 	
		var request = {
	             method: 'PATCH',
	             url: urw,
	             data:{
	            	 "empid":empid,
	            	 "start":start,
	            	 "stop":stop,
	            	 "sunday":sun,
	            	 "monday":mon,
	            	 "tuesday":tue,
	            	 "wednesday":wed,
	            	 "thursday":thur,
	            	 "friday":fri,
	            	 "saturday":sat,
	            	 "status":status
	             }
			}
		
	     console.log(request);
	     $http(request)	         
	     .success(function(data, status, headers, config){
	         console.log("data fetched successfully");
	         
	         successFn(data,status,headers,config);
	     })
	     .error(function(data, status, headers, config){
	         console.log("Data is not fetched");
	         errorFn(data,status,headers,config);
	     });    
	};
	
	
	//Checking last day of prev month
	attendencesrvc.checklastDaymonth=function(empid,date,successFn,errorFn) {
	 	
		var request = {
	             method: 'GET',
	             url: urm+empid+"/"+date
			}
		
	     console.log(request);
	     $http(request)	         
	     .success(function(data, status, headers, config){
	         console.log("data fetched successfully");
	         
	         successFn(data,status,headers,config);
	     })
	     .error(function(data, status, headers, config){
	         console.log("Data is not fetched");
	         errorFn(data,status,headers,config);
	     });    
	};
	
	
			
	 return attendencesrvc;	
		
		
	}]);

	

                  

					
                          