var lmsApp = angular.module('lmsApp', ['ui.router','portalFrameControllers','portalEmpControllers','portalHolidayControllers','portalAttendenceControllers','portalReportControllers','ui.bootstrap','mgo-angular-wizard',
                                           'ui.bootstrap.showErrors','loginsrvc','employeeSrvc','holidaySrvc','attendSrvc','reportSrvc']);
lmsApp.constant('USER_ROLES', {
	super : 'SUPER',
	regular : 'REGULAR', 
});

lmsApp.constant('AUTH_PARAMS', {
    AUTH_TOKEN : 'X-AUTH-TOKEN',
});


lmsApp.constant('AUTH_EVENTS', {
	notAuthorized : 'auth-not-authorized'
});

lmsApp.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/login');

	$stateProvider

	// HOME STATE FOR Cafe-Frame PAGE ========================================
	.state('dash', {
		url : '/dashboard',
		templateUrl : 'src/partials/Dashboard.html',
		controller : 'frameCtrl',
	})
	.state('dash.addemp', {
		url : '/addemp',
		templateUrl : 'src/partials/addEmployee.html',
		controller : 'empCtrl',
	})
	
	.state('dash.addleavepolicy', {
		url : '/addleavepolicy',
		templateUrl : 'src/partials/addLeavePolicy.html',
		controller : 'empCtrl',
	})
	.state('dash.delemp', {
		url : '/delemp',
		templateUrl : 'src/partials/deleteEmployee.html',
		controller : 'empCtrl',
	})
	.state('dash.delholiday', {
		url : '/delholiday',
		templateUrl : 'src/partials/deleteHoliday.html',
		controller : 'holidayCtrl',
	})
	.state('dash.weekAttend', {
		url : '/weekAttend',
		templateUrl : 'src/partials/WeekAttendence.html',
		controller : 'attendenceCtrl',
	})
	
	.state('dash.reportMonth', {
		url : '/reportMonth',
		templateUrl : 'src/partials/repoertGenerate.html',
		controller : 'reportCtrl',
	})
	.state('dash.addholiday', {
		url : '/addholiday',
		templateUrl : 'src/partials/addViewHoliday.html',
		controller : 'holidayCtrl',
	})
	
	.state('dash.wizz', {
		url : '/wizz',
		templateUrl : 'src/partials/EmpWizzard.html',
		controller : 'empCtrl',
	})
	

	.state('login', {
		url : '/login',
		templateUrl : 'src/partials/lui-login.html',
		controller : 'frameCtrl',
		});
	;
	})

lmsApp.run(function($rootScope, USER_ROLES) {

});
