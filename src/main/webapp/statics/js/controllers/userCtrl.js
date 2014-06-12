app.controller('userCtrl', function($scope, $http, $location,$routeParams, Users, userService) {
	// Load users on init (could be done on the routers as well).
	$scope.editUser =  userService.getUser($routeParams.id);	
	$scope.cancelSave = function(){
		$location.path('/');
	};
	
	$scope.saveOrUpdateUser = function(user){
		if(user.id){
			var resource = userService.updateUser(user);
			
			resource.$promise.then(
					function(updatedUser){
						console.log("User with id "+ updatedUser.id +" updated successfully");
					}, function(error){
						console.log("User updated failed with message " +error.data.message);		
					});
		}else{
			var resource = userService.addUser(user);

			resource.$promise.then(
					function(updatedUser){
						console.log("User with id "+ updatedUser.id +" created successfully");
					}, function(error){
						console.log("Create failed with message " +error.data.message);		
					});
		}
		
		$location.path('/');
	};
	
});