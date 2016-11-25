var app = angular.module('App', []);								
app.controller('ProductController', function($scope,$http)
		{
			$http.get("data").then(
            function(response)
            {
                   $scope.Data= response.data;
            });
		});
