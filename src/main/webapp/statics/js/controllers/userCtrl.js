app.controller('userCtrl', function($scope, $http, $location,$routeParams,$parse, Users, userService) {
	// Load users on init (could be done on the routers as well).
	$scope.editUser =  userService.getUser($routeParams.id);
	
	$scope.cancelSave = function(){
		$location.path('/');
	};
	
	$scope.populateValidationErrors =
	
	$scope.saveOrUpdateUser = function(user){
		if(user.id){
			var resource = userService.updateUser(user);
			
			resource.$promise.then(
					function(updatedUser){
						console.log("User with id "+ updatedUser.id +" updated successfully");
						
					},
						$scope.populateErrors
					);
		}else{
			var resource = userService.addUser(user);

			resource.$promise.then(
					function(updatedUser){
						console.log("User with id "+ updatedUser.id +" created successfully");
						$location.path('/');
					},
						$scope.populateErrors
					);
		}
		
	};
	
	$scope.populateErrors = function(error){
		console.log("User updated/create failed with message " +error.data.errorMessage);
		//copy all errors from response to controller 
		for(var field in error.data.fieldErrors){
			var serverMessage = $parse('userForm.'+field+'.$error.serverMessage');
			serverMessage.assign($scope, error.data.fieldErrors[field]);
			$scope.userForm[field].$setValidity('rstServerError', false);
		}
	}
});