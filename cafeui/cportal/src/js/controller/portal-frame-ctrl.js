'use strict';

/* Controllers */
var pAuthControllers = angular.module('portalFrameControllers', []);

pAuthControllers.controller('frameCtrl',
    ['$scope','$rootScope','$state','leavesrvc',
    function($scope,$rootScope,$state,leavesrvc) {
		
    	$scope.$broadcast('show-errors-reset');
   
    	$scope.showReport=function()
    	{
    		//alert("IN REPORT GENRATION FUNCTION");
    		location.href="#/dashboard/reportMonth";
    	}
    	
    	$scope.login=function()
    	{
    		leavesrvc.hrlogin($scope.username,$scope.password,hrloginSuccess,hrloginFailure)
    	}
    	function hrloginSuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        	alert("Login Succesfully");
        	location.href="#/dashboard";
        	//location.href="#/success"; 
        	//location.href = "#/login";        	
        }
        function hrloginFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        } 	
    	
        $scope.showAddEmp=function()
        {
        	location.href="#/dashboard/addemp";
        }
      //Display add/View Holiday page
        $scope.showAddHoliday=function()
        {
        	location.href="#/dashboard/addholiday";
        //	$rootScope.holidayCtrl.fetchHolidays();
        }
        //Display Delete Employee page
        $scope.showDelEmp=function()
        {
        	location.href="#/dashboard/delemp";
        }
        
        
      //Display Delete Holiday page
        $scope.showDelHoliday=function()
        {
        	location.href="#/dashboard/delholiday";
        }

        //Display week attendence page
        $scope.showWeekAttend=function()
        {
        	location.href="#/dashboard/weekAttend";
        }
        
        //To display/hide the Slide bar more option
    	$scope.showEmpFun=function()
    	{
    		if($scope.empFun==true)
    			$scope.empFun=false;
    		else
    		{
    			$scope.empFun=true;
    			$scope.holidayFun=false;
    			$scope.reportFun=false;
    		}
    	}
    	$scope.showHolidayFun=function()
    	{
    		if($scope.holidayFun==true)
    			$scope.holidayFun=false;
    		else
    		{
    			$scope.empFun=false;
    			$scope.holidayFun=true;
    			$scope.reportFun=false;
    		}
    	}
    	$scope.showReportFun=function()
    	{
    		if($scope.reportFun==true)
    			$scope.reportFun=false;
    		else
    		{
    			$scope.empFun=false;
    			$scope.holidayFun=false;
    			$scope.reportFun=true;
    		}
    	}
    	
    	$scope.showReconcilations=function()
 		{
 			location.href="#/app/ReconcileDetail" 
 		}
    	
                
    }]);



