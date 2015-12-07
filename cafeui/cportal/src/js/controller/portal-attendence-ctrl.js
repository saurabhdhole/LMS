'use strict';

/* Controllers */
var pAuthControllers = angular.module('portalAttendenceControllers', []);

pAuthControllers.controller('attendenceCtrl',
    ['$scope','$rootScope','$state','attendencesrvc',
    function($scope,$rootScope,$state,attendencesrvc) {
		
    	$scope.$broadcast('show-errors-reset');
   
    	
    	$scope.fstweek=function(start,stop)
    	{
    		if($scope.empid=="" || $scope.empid==null || $scope.empid=="undefined")
    			sweetAlert("","Please !!! Select Employee first","error");
    		else
    		{
    			$scope.start=start;
    			$scope.stop=stop;
    			attendencesrvc.checklmmrc($scope.selectedId.empid,start,stop,checkPrevSuccess,CheckPrevFailure);
    		}
    	}
    	function checkPrevSuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        	alert("Fetch Records ");
        	$scope.prevstatus=data;
        	if($scope.prevstatus.status=="prevRemain")
        	{
        		$scope.showWeekA=true;
        		$scope.checkDates($scope.start,$scope.stop);
        		
        	}
        	if($scope.prevstatus.status=="complete")
        	{
        		$scope.showWeekA=false;
        	}
       // 	$scope.checkDates();
        }
    	//mon1 lastday of month success 
    	function checkPrevSuccess1(data,status,headers,config){
    		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
    		var date2 = $scope.tday;
    		angular.element('#ajaxLoading').hide();
        	//alert("Fetch Records ");
        	$scope.prevstatus=data;
        	$scope.flag=0;
        	if($scope.prevstatus.status=="prevRemain")
        	{
        		$scope.showWeekA=false;
        		$scope.flag=1;
        		$scope.mon=$scope.mon1;
				$scope.day=$scope.day1;
				$scope.status="previous";
				$scope.mname=monthNames[$scope.pday.getMonth()];
				date2.setDate($scope.day);
				if(date2.getMonth()==1)
					date2.setMonth(11);
				else
					date2.setMonth(date2.getMonth()-1);
       // 		$scope.checkDates($scope.start,$scope.stop);
				
				$scope.calculate($scope.tday)
        		
        	}
       // 	$scope.checkDates();
        }
        function CheckPrevFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        }
        
        
        
        //Add Emp attendance
        $scope.addAttendanceEmp=function()
        {
        	if($scope.sunday1==undefined||$scope.monday1==undefined||$scope.tuesday1==undefined||$scope.wednesday1==undefined||$scope.thursday1==undefined||$scope.friday1==undefined||$scope.saturday1==undefined)
        	{
        		swal({   title: "Are you sure?",   text: "It will be marked as Absent for unchecked days",   type: "warning",   showCancelButton: true,   confirmButtonColor: "#DD6B55",   confirmButtonText: "Yes, submit it!",   closeOnConfirm: false }, 
        		function(){ 
        			if($scope.sunday1==undefined)
        				$scope.sunday1="";
        			if($scope.monday1==undefined)
        				$scope.monday1="";
        			if($scope.tuesday1==undefined)
        				$scope.tuesday1="";
        			if($scope.wednesday1==undefined)
        				$scope.wednesday1="";
        			if($scope.thursday1==undefined)
        				$scope.thursday1="";
        			if($scope.friday1==undefined)
        				$scope.friday1="";
        			if($scope.saturday1==undefined)
        				$scope.saturday1="";
        			
        			attendencesrvc.addWeekAttendance($scope.empid,$scope.start,$scope.stop,$scope.status,$scope.sunday1,$scope.monday1,$scope.tuesday1,$scope.wednesday1,$scope.thursday1,$scope.friday1,$scope.saturday1,addWeekAttendanceSuccess,addWeekAttendanceFailure)
        		});
       		}
        	else
        	{
        		attendencesrvc.addWeekAttendance($scope.empid,$scope.start,$scope.stop,$scope.status,$scope.sunday1,$scope.monday1,$scope.tuesday1,$scope.wednesday1,$scope.thursday1,$scope.friday1,$scope.saturday1,addWeekAttendanceSuccess,addWeekAttendanceFailure)
        	}
        	function addWeekAttendanceSuccess(data,status,headers,config){
            	angular.element('#ajaxLoading').hide();
            	swal("Success!", "Week attedance  added succusfully  ", "success");
            	$scope.sunday1=undefined;
            	$scope.monday1=undefined;
            	$scope.tuesday1=undefined;
            	$scope.wednesday1=undefined;
            	$scope.thursday1=undefined;
            	$scope.friday1=undefined;
            	$scope.saturday1=undefined;
            	$scope.showWeekA=false;
           // 	$scope.checkDates();
            }
            function addWeekAttendanceFailure(data,status,headers,config){
            	angular.element('#ajaxLoading').hide();
            	$scope.sunday1=undefined;
            	$scope.monday1=undefined;
            	$scope.tuesday1=undefined;
            	$scope.wednesday1=undefined;
            	$scope.thursday1=undefined;
            	$scope.friday1=undefined;
            	$scope.saturday1=undefined;
            	$scope.showWeekA=false;
            }
        	
        }
        
    	//display all week of month
    	$scope.weekMonthsshow=function()
    	{
    		var date2=new Date();
    		//status add current add previous month
    		
    		
    		
    		
    		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
    		$scope.mon=date2.getMonth()+1;
    		$scope.mon1=date2.getMonth();
    		$scope.mname=monthNames[date2.getMonth()];
    		if(($scope.mon)==2)
    		{
    			if(date2.getYear()%4==0)
    				$scope.day=29;
    			else
    				$scope.day=28;
    		}
    		else
    		{
    			if(($scope.mon)==1||($scope.mon)==3||($scope.mon)==5||($scope.mon)==7||($scope.mon)==8||($scope.mon)==10||($scope.mon)==12)
    				$scope.day=31;
    			else
    				$scope.day=30;
    		}
    		
    		//fr mon1 to check prev
    		if(($scope.mon1)==2)
    		{
    			if(date2.getYear()%4==0)
    				$scope.day1=29;
    			else
    				$scope.day1=28;
    		}
    		else
    		{
    			if(($scope.mon1)==1||($scope.mon1)==3||($scope.mon1)==5||($scope.mon1)==7||($scope.mon1)==8||($scope.mon1)==10||($scope.mon1)==12)
    				$scope.day1=31;
    			else
    				$scope.day1=30;
    		}
    		
    		
    		$scope.status="current";
    		//add service to check previous if date is less than 7
    		if(date2.getDate()<7)
    		{
    			//call to check last day
    			$scope.start=$scope.day1;
    			$scope.stop=$scope.day1;
    			var Pdate=new Date();
    			if(Pdate.getMonth()==0)
    				Pdate.setMonth(11);
    			else
    				Pdate.setMonth(date2.getMonth()-1);
    			Pdate.setDate($scope.day1);
    			
        		$scope.pday = Pdate;

        		$scope.tday = date2;
    			
    			///////////write new service for it. otherwise manage in same function
    			
    			attendencesrvc.checklastDaymonth($scope.empid,Pdate,checkPrevSuccess1,CheckPrevFailure) 
    	//		attendencesrvc.checklmmrc($scope.selectedId.empid,Pdate,checkPrevSuccess,CheckPrevFailure);
    			$scope.calculate(date2);
    		}
    		
    		
    		
    		//take date greater > 7 and also consider the day
    		//checking fr 1st 7 day
    	
    	}
    	
    	$scope.calculate=function(date2)
    	{
    		var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];

    		if(date2.getDate()<7)
    		{
    			$scope.temp=date2.getDate()+7-date2.getDay()-1;
    		}
    		else
    		{	
    		$scope.temp=date2.getDate()-date2.getDay()-1;
    		}
    		$scope.temp=$scope.temp%7;
    		
    		if($scope.temp==0)
    		{
    			$scope.w1start=1;
    			$scope.w1stop=7;
//    			$scope.ww1start=$scope.w1start+" "+$scope.mname;
//    			$scope.ww1stop=$scope.w1stop+" "+$scope.mname;
    			$scope.w2start=8;
    			$scope.w2stop=14;
//    			$scope.ww2start=$scope.w2start+" "+$scope.mname;
//    			$scope.ww2stop=$scope.w2stop+" "+$scope.mname;
    			$scope.w3start=15;
    			$scope.w3stop=21;
//    			$scope.ww3start=$scope.w3start+" "+$scope.mname;
//    			$scope.ww3stop=$scope.w3stop+" "+$scope.mname;
    			$scope.w4start=22;
    			$scope.w4stop=28;
//    			$scope.ww4start=$scope.w4start+" "+$scope.mname;
//    			$scope.ww4stop=$scope.w4stop+" "+$scope.mname;
    			if($scope.day>=29)
    			{
    				$scope.w5start=29;
    				$scope.w5stop=$scope.day;
//    				$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    				
    			}
    			$scope.week6=false;
    		}
    		if($scope.temp==1)
    		{
    			$scope.w1start=1;
    			$scope.w1stop=1;
//    			$scope.ww1start=$scope.w1start+" "+$scope.mname;
//    			$scope.ww1stop=$scope.w1stop+" "+$scope.mname;
    			$scope.w2start=2;
    			$scope.w2stop=8;
//    			$scope.ww2start=$scope.w2start+" "+$scope.mname;
//    			$scope.ww2stop=$scope.w2stop+" "+$scope.mname;
    			$scope.w3start=9;
    			$scope.w3stop=15;
    			$scope.w4start=16;
    			$scope.w4stop=22;
//    			$scope.ww3start=$scope.w3start+" "+$scope.mname;
//    			$scope.ww3stop=$scope.w3stop+" "+$scope.mname;
//    			$scope.ww4start=$scope.w4start+" "+$scope.mname;
//    			$scope.ww4stop=$scope.w4stop+" "+$scope.mname;
    			if($scope.day>29)
    			{
    				$scope.w5start=23;
    				$scope.w5stop=29;
//    				$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    			}else
    			{
    				$scope.w5start=23;
    				$scope.w5stop=$scope.day;
//    				$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    			}
    			if($scope.day>=30)
    			{
    				$scope.week6=true;
    				$scope.w6start=30;
    				$scope.w6stop=$scope.day;
//    				$scope.ww6start=$scope.w6start+" "+$scope.mname;
//        			$scope.ww6stop=$scope.w6stop+" "+$scope.mname;
    			}
    			
    		}
    		if($scope.temp==2)
    		{
    			$scope.w1start=1;
    			$scope.w1stop=2;
    			$scope.w2start=3;
    			$scope.w2stop=9;
    			$scope.w3start=10;
    			$scope.w3stop=16;
    			$scope.w4start=17;
    			$scope.w4stop=23;
//    			$scope.ww1start=$scope.w1start+" "+$scope.mname;
//    			$scope.ww1stop=$scope.w1stop+" "+$scope.mname;
//    			$scope.ww2start=$scope.w2start+" "+$scope.mname;
//    			$scope.ww2stop=$scope.w2stop+" "+$scope.mname;
//    			$scope.ww3start=$scope.w3start+" "+$scope.mname;
//    			$scope.ww3stop=$scope.w3stop+" "+$scope.mname;
//    			$scope.ww4start=$scope.w4start+" "+$scope.mname;
//    			$scope.ww4stop=$scope.w4stop+" "+$scope.mname;
    			if($scope.day>29)
    			{
    				$scope.w5start=24;
    				$scope.w5stop=30;
//    				$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    			}else
    			{
    				$scope.w5start=23;
    				$scope.w5stop=$scope.day;
//    				$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    			}
    			if($scope.day>30)
    			{
    				$scope.w6start=31;
    				$scope.w6stop=$scope.day;
//    				$scope.ww6start=$scope.w6start+" "+$scope.mname;
//        			$scope.ww6stop=$scope.w6stop+" "+$scope.mname;
    			}
    			
    		}
    		if($scope.temp==3)
    		{
    			$scope.w1start=1;
    			$scope.w1stop=3;
    			$scope.w2start=4;
    			$scope.w2stop=10;
    			$scope.w3start=11;
    			$scope.w3stop=17;
    			$scope.w4start=18;
    			$scope.w4stop=24;
    				$scope.w5start=25;
    				$scope.w5stop=$scope.day; 			
//    				$scope.ww1start=$scope.w1start+" "+$scope.mname;
//        			$scope.ww1stop=$scope.w1stop+" "+$scope.mname;
//        			$scope.ww2start=$scope.w2start+" "+$scope.mname;
//        			$scope.ww2stop=$scope.w2stop+" "+$scope.mname;
//        			$scope.ww3start=$scope.w3start+" "+$scope.mname;
//        			$scope.ww3stop=$scope.w3stop+" "+$scope.mname;
//        			$scope.ww4start=$scope.w4start+" "+$scope.mname;
//        			$scope.ww4stop=$scope.w4stop+" "+$scope.mname;
//        			$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    		}
    		if($scope.temp==4)
    		{
    			$scope.w1start=1;
    			$scope.w1stop=4;
    			$scope.w2start=5;
    			$scope.w2stop=11;
    			$scope.w3start=12;
    			$scope.w3stop=18;
    			$scope.w4start=19;
    			$scope.w4stop=25;
    				$scope.w5start=26;
    				$scope.w5stop=$scope.day; 			
//    				$scope.ww1start=$scope.w1start+" "+$scope.mname;
//        			$scope.ww1stop=$scope.w1stop+" "+$scope.mname;
//        			$scope.ww2start=$scope.w2start+" "+$scope.mname;
//        			$scope.ww2stop=$scope.w2stop+" "+$scope.mname;
//        			$scope.ww3start=$scope.w3start+" "+$scope.mname;
//        			$scope.ww3stop=$scope.w3stop+" "+$scope.mname;
//        			$scope.ww4start=$scope.w4start+" "+$scope.mname;
//        			$scope.ww4stop=$scope.w4stop+" "+$scope.mname;
//        			$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    		}
    		if($scope.temp==5)
    		{
    			$scope.w1start=1;
    			$scope.w1stop=5;
    			$scope.w2start=6;
    			$scope.w2stop=12;
    			$scope.w3start=13;
    			$scope.w3stop=19;
    			$scope.w4start=20;
    			$scope.w4stop=26;
    				$scope.w5start=27;    				
    				$scope.w5stop=$scope.day;
//    				$scope.ww1start=$scope.w1start+" "+$scope.mname;
//        			$scope.ww1stop=$scope.w1stop+" "+$scope.mname;
//        			$scope.ww2start=$scope.w2start+" "+$scope.mname;
//        			$scope.ww2stop=$scope.w2stop+" "+$scope.mname;
//        			$scope.ww3start=$scope.w3start+" "+$scope.mname;
//        			$scope.ww3stop=$scope.w3stop+" "+$scope.mname;
//        			$scope.ww4start=$scope.w4start+" "+$scope.mname;
//        			$scope.ww4stop=$scope.w4stop+" "+$scope.mname;
//        			$scope.ww5start=$scope.w5start+" "+$scope.mname;
//        			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    		}
    		if($scope.temp==6)
    		{
    			$scope.w1start=1;
    			$scope.w1stop=6;
    			$scope.w2start=7;
    			$scope.w2stop=13;
    			$scope.w3start=14;
    			$scope.w3stop=20;
    			$scope.w4start=21;
    			$scope.w4stop=27;
    			$scope.w5start=28;
    			$scope.w5stop=$scope.day;
//    			$scope.ww1start=$scope.w1start+" "+$scope.mname;
//    			$scope.ww1stop=$scope.w1stop+" "+$scope.mname;
//    			$scope.ww2start=$scope.w2start+" "+$scope.mname;
//    			$scope.ww2stop=$scope.w2stop+" "+$scope.mname;
//    			$scope.ww3start=$scope.w3start+" "+$scope.mname;
//    			$scope.ww3stop=$scope.w3stop+" "+$scope.mname;
//    			$scope.ww4start=$scope.w4start+" "+$scope.mname;
//    			$scope.ww4stop=$scope.w4stop+" "+$scope.mname;
//    			$scope.ww5start=$scope.w5start+" "+$scope.mname;
//    			$scope.ww5stop=$scope.w5stop+" "+$scope.mname;
    		}
 
    	}
    	
    	
    	//Fetching the all emp records
    	$scope.fetchEmpRecords=function()
    	{
    		$scope.empid=0;
    		attendencesrvc.fetchEmprecords(fetchEmpSuccess,fetchEmpFailure)
    	}
    	function fetchEmpSuccess(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        //	alert("Fetch Records ");
        	$scope.emprc=data;
        }
        function fetchEmpFailure(data,status,headers,config){
        	angular.element('#ajaxLoading').hide();
        } 
        
        //selecting the emp
        $scope.showAttendancePanel=function()
        {
        	//console.log($scope.selectedId.empid)
        	$scope.empid=$scope.selectedId.empid;
        	var date1=new Date();
        	$scope.weekMonthsshow();
        }

        // dynamically generating the dates 
        $scope.checkDates=function(start,stop)
        {
        	
        	var date=new Date();
        	//alert(date);
        	//getmonths
        	var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
        	$scope.month=monthNames[date.getMonth()];
        	//available var day start last

        	if(start<=stop)
        	{
        		$scope.sunday=true;
        		$scope.day1=start+" "+$scope.month;
        		start=start+1;
        	}
        	else
        		$scope.sunday=false;
        	if(start<=stop)
        	{
        		$scope.monday=true;
        		$scope.day2=start+" "+$scope.month;
        		start=start+1;
        	}
        	else
        		$scope.monday=false;
        	if(start<=stop)
        	{
        		$scope.tuesday=true;
        		$scope.day3=start+" "+$scope.month;
        		start=start+1;
        	}
        	else
        		$scope.tuesday=false;
        	if(start<=stop)
        	{
        		$scope.wednesday=true;
        		$scope.day4=start+" "+$scope.month;
        		start=start+1;
        	}
        	else
        		$scope.wednesday=false;
        	if(start<=stop)
        	{
        		$scope.thursday=true;
        		$scope.day5=start+" "+$scope.month;
        		start=start+1;
        	}
        	else
        		$scope.thursday=false;
        	if(start<=stop)
        	{
        		$scope.friday=true;
        		$scope.day6=start+" "+$scope.month;
        		start=start+1;
        	}
        	else
        		$scope.friday=false;
        	if(start<=stop)
        	{
        		$scope.saturday=true;
        		$scope.day7=start+" "+$scope.month;
        		start=start+1;
        	}
        	else
        		$scope.saturday=false;
        	
        	
        } 	
}]);
