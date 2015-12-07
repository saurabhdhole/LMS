var LoginService = angular.module('loginsrvc', []);

LoginService.factory('leavesrvc',['$http',  function($http) {
	var leavesrvc={};
	var ur="http://localhost:8080/lmsms/leave/login/";
	var urs="http://localhost:8080/foms/order";

	leavesrvc.hrlogin=function(uname,pass,successFn,errorFn) {
	 	
		var request = {
	            method: 'GET',
	            url: ur+uname+"/"+pass
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
		
	
		

	//this function service is for add order name
	
	
	
	
			
	 return leavesrvc;	
		
		
	}]);

	

                  

					
                          