var HolidayService = angular.module('holidaySrvc', []);

HolidayService.factory('holidaysrvc',['$http',  function($http) {
	var holidaysrvc={};
	var ur="http://localhost:8080/lmsms/holiday";
//	var url="http://localhost:8080/lmsms/empleave";
	
	///Add holiday record to DB
	holidaysrvc.addholiday=function(date,desc,successFn,errorFn) {
	 	
		var request = {
	             method: 'POST',
	             url: ur,
	             data:{
	            	 "date":date,
	            	 "description":desc
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
	
	//fetch holiday records
	holidaysrvc.fetchholidays=function(successFn,errorFn) {
	 	
		var request = {
	             method: 'GET',
	             url: ur+"/YYYY"
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
		
	holidaysrvc.deleteHoliday=function(hlid,successFn,errorFn) {
	 	
		var request = {
	             method: 'DELETE',
	             url: ur+"/"+hlid
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
	
	
	
	
	
			
	 return holidaysrvc;	
		
		
	}]);

	

                  

					
                          