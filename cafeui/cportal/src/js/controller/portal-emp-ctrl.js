'use strict';

/* Controllers */
var pAuthControllers = angular.module('portalEmpControllers', []);

pAuthControllers.controller('empCtrl',
    ['$scope','$rootScope','$state','empsrvc','WizardHandler',
    function($scope,$rootScope,$state,empsrvc,WizardHandler) {
		
    	$scope.$broadcast('show-errors-reset');
    	
    	//submit description
    	$scope.submitdesc=function(empid,empname)
    	{
    		$scope.empid=empid;
    		$scope.empname=empname;
    	}
    	
    	$scope.addEmp=function()
    	{
    		$rootScope.eid=$scope.empid;
    		$rootScope.gender=$scope.gender;
    		
    		empsrvc.addemp($scope.empid,$scope.empname,$scope.mobile,$scope.email,$scope.location,$scope.department,$scope.bgp,$scope.gender,addEmpSuccess,addEmpFailure)
    	}
    	function addEmpSuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
       // 	alert("Login Succesfully");
        	location.href="#/dashboard/addleavepolicy";
        	//location.href="#/success"; 
        	//location.href = "#/login";        	
        }
        function addEmpFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        } 	
    	
        $scope.addLeavePolicy=function()
    	{
        	empsrvc.addLeavePolicy($rootScope.eid,$scope.cl,$scope.ml,$scope.sl,$scope.pl,$scope.mml,$scope.el,$scope.cpl,addLeavePolicySuccess,addLeavePolicyFailure)
    	}
        function addLeavePolicySuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
       // 	alert("Login Succesfully");
        	location.href="#/dashboard";
        	//location.href="#/success"; 
        	//location.href = "#/login";        	
        }
        function addLeavePolicyFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        }
        $scope.genderleave=function()
        {
        	if($rootScope.gender=="female")
        		return true;
        	else
        		return false;
        }
        
        //calling the employee delete service
        $scope.deleteEmp=function()
        {
        	empsrvc.deleteEmp($scope.empid,delEmpSuccess,delEmpFailure)
        }
        function delEmpSuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        	location.href="#/dashboard";
        	
        	        	
        }
        function delEmpFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        }
                
    }]);



