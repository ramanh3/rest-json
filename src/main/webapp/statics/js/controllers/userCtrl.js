app.controller('userCtrl', function($scope, $http, $location,$routeParams, Users, userService) {
	// Load users on init (could be done on the routers as well).
	$scope.editUser =  userService.getUser($routeParams.id);	
	$scope.cancelSave = function(){
		$location.path('/');
	};
	
	$scope.saveOrUpdateUser = function(user){
		if(user.id){
			userService.updateUser(user);
		}else{
			userService.addUser(user);
		}
		
		$location.path('/');
	};
	
});