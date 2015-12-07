'use strict';

/* Controllers */
var pAuthControllers = angular.module('portalReportControllers', []);

pAuthControllers.controller('reportCtrl',
    ['$scope','$rootScope','$state','reportsrvc','$window',
    function($scope,$rootScope,$state,reportsrvc,$window) {
		
    	$scope.$broadcast('show-errors-reset');
   
    	$scope.genrateByMonth=function()
    	{
    		var dateArr = document.getElementById("startdate").value.split("/")
    		//console.log(dateArr)
    		$scope.searchDate=new Date();
    		$scope.searchDate.setMonth(dateArr[0]-1)
    		$scope.searchDate.setYear(dateArr[1])
    		//console.log($scope.searchDate)
    		//alert($scope.searchDate.getMonth());
//    		reportsrvc.genReportByMonth($scope.searchDate.getMonth(),$scope.searchDate.getYear(),genReportByMonthSuccess,genReportByMonthFailure);
    		$window.open('http://localhost:8080/lmsms/leave/exel/'+$scope.searchDate.getMonth()+'/'+$scope.searchDate.getYear(),'_self');
    	//	$window.open("e://license.txt");
    	}
//    	function genReportByMonthSuccess(data,status,headers,config){
//        	angular.element('#ajaxLoading').hide();
//        	window.open(data);
//        	 	alert("File Received ");               	
//        }
//        function genReportByMonthFailure(data,status,headers,config){
//        	angular.element('#ajaxLoading').hide();
//        } 
        
}]);



