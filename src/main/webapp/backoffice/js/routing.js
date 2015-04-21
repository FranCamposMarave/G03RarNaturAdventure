
// Create the module
var app = angular.module('bk-app',
    ['bk-controllers','ngRoute']);

app.config(function(cfpLoadingBarProvider) {
    cfpLoadingBarProvider.latencyThreshold = 10;
});
// Config routes
app.config(function($routeProvider) {
	$routeProvider
		.when('/', {
            templateUrl : 'templates/home.html',
            controller 	: 'backofficeCtrl'
        })
        .when('/activity/:id', {
            templateUrl : 'templates/activity.html',
            controller 	: 'activityCtrl'
        })
        .when('/activity', {
            templateUrl : 'templates/activity.html',
            controller 	: 'activityCtrl'
        })
        .when('/activities', {
            templateUrl : 'templates/activities.html',
            controller 	: 'activitiesCtrl'
        })
        .when('/category', {
            templateUrl : 'templates/category.html',
            controller 	: 'categoryCtrl'
        })
        .when('/categories', {
            templateUrl : 'templates/categories.html',
            controller 	: 'categoriesCtrl'
        })
        .when('/monitor', {
            templateUrl : 'templates/monitor.html',
            controller 	: 'monitorCtrl'
        })
        .when('/monitors', {
            templateUrl : 'templates/monitors.html',
            controller 	: 'monitorsCtrl'
        })
		.otherwise({
			redirectTo: '/'
		});
});

