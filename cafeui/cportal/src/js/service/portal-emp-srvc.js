var EmployeeService = angular.module('employeeSrvc', []);

EmployeeService.factory('empsrvc',['$http',  function($http) {
	var empsrvc={};
	var ur="http://localhost:8080/lmsms/emp";
	var url="http://localhost:8080/lmsms/empleave";
	
	empsrvc.addemp=function(eid,ename,mobile,email,location,dept,bgp,gender,successFn,errorFn) {
	 	
		var request = {
	             method: 'POST',
	             url: ur,
	             data:{
	            	 "empid":eid,
	            	 "empname":ename,
	            	 "mobile":mobile,
	            	 "email":email,
	            	 "location":location,
	            	 "department":dept,
	            	 "bloodgp":bgp,
	            	 "gender":gender
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
		
	//service for to add leave policy for employee
	empsrvc.addLeavePolicy=function(eid,cl,ml,sl,pl,mml,el,cpl,successFn,errorFn) {
	 	
		var request = {
	             method: 'POST',
	             url: url,
	             data:{
	            	 "empid":eid,
	            	 "cl":cl,
	            	 "ml":ml,
	            	 "sl":sl,
	            	 "pl":pl,
	            	 "mml":mml,
	            	 "el":el,
	            	 "cpl":cpl
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
		
	//Service to delete emp record and its leave policy
	empsrvc.deleteEmp=function(eid,successFn,errorFn) {
 	
	var request = {
             method: 'DELETE',
             url: ur+"/"+eid
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

	
	
	
	
			
	 return empsrvc;	
		
		
	}]);

	

                  

					
                          