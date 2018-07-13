var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider) {
	$locationProvider.hashPrefix('');
	
    $routeProvider
    .when('/london', {
        templateUrl : 'london.html',
        controller : 'londonCtrl'
    })
    .when('/paris', {
        templateUrl : 'paris.html',
        controller : 'parisCtrl'
    });

    
});
app.controller('londonCtrl', function ($scope) {
	$scope.name = 'londonCtrl';
   
});
app.controller('parisCtrl', function ($scope) {
	$scope.name = 'parisCtrl';
});
