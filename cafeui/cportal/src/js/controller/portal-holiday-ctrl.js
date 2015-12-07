'use strict';

/* Controllers */
var pAuthControllers = angular.module('portalHolidayControllers', []);

pAuthControllers.controller('holidayCtrl',
    ['$scope','$rootScope','$state','holidaysrvc',
    function($scope,$rootScope,$state,holidaysrvc) {
		
    	$scope.$broadcast('show-errors-reset');
   
    	$scope.addHoliday=function()
    	{
    		$scope.searchDate=new Date(document.getElementById("startdate").value);
    		holidaysrvc.addholiday($scope.searchDate,$scope.desc,addHolidaySuccess,addHolidayFailure)
    	}
    	function addHolidaySuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
       // 	alert("Login Succesfully");
        	document.getElementById("startdate").value="";
        	$scope.desc="";
        	holidaysrvc.fetchholidays(fetchHolidaysSuccess,fetchHolidaysFailure)
        	//location.href="#/dashboard/addholiday";
        	//location.href="#/success"; 
        	//location.href = "#/login";        	
        }
        function addHolidayFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        } 
        
        //Fetch Holiday records
    	
        $rootScope.fetchHolidays=function()
    	{
        //	alert("blrhaa")
        	holidaysrvc.fetchholidays(fetchHolidaysSuccess,fetchHolidaysFailure)
    	}
    	function fetchHolidaysSuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        	$scope.holidays=data;
       // 	alert("Login Succesfully");
        	//location.href="#/dashboard";
        	//location.href="#/success"; 
        	//location.href = "#/login";        	
        }
        function fetchHolidaysFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        }
        
        //deleteHoliday Records
        $rootScope.deleteHoliday=function()
    	{
        	alert("blrhaa")
        	holidaysrvc.deleteHoliday($scope.hlid,delHolidaySuccess,delHolidayFailure)
    	}
    	function delHolidaySuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        	location.href="#/dashboard/addholiday";
        	
       // 	alert("Login Succesfully");
        	//location.href="#/dashboard";
        	//location.href="#/success"; 
        	//location.href = "#/login";        	
        }
        function delHolidayFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        }               
    }]);



