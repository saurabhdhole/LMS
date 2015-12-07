var ReportService = angular.module('reportSrvc', []);

ReportService.factory('reportsrvc',['$http',  function($http) {
	var reportsrvc={};
	var ur="http://localhost:8080/lmsms/leave/exel/";
//	var url="http://localhost:8080/lmsms/empleave";
	
	///Add holiday record to DB
	reportsrvc.genReportByMonth=function(mm,yyyy,successFn,errorFn) {
	 	
		var request = {
	             method: 'GET',
	             url: ur+mm+"/"+yyyy
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
			
	 return reportsrvc;	
		
		
	}]);

	

                  

					
                          