var app = angular.module('myApp', [ 'ngRoute' ])
app.config(function($routeProvider,$locationProvider) {
	$routeProvider
	.when("/login",{
		templateUrl : "views/login.html"
	})
	
	.when("/register",{
		templateUrl : "views/register.html"
	})

	$locationProvider.html5Mode(true)
});

