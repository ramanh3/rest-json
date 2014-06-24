app.controller('userCtrl', function($scope, $http, $location,$routeParams,$parse, Users, userService) {
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
						$location.path('/');
					}, function(error){
						console.log("User updated failed with message " +error.data.errorMessage);
					    var serverMessage = $parse('userForm.firstName.$error.serverMessage');
					    $scope.userForm.$setValidity('firstName', false, $scope.userForm);
		                serverMessage.assign($scope, error.data.fieldErrors['firstName']);
					    
					});
		}else{
			var resource = userService.addUser(user);

			resource.$promise.then(
					function(updatedUser){
						console.log("User with id "+ updatedUser.id +" created successfully");
						$location.path('/');
					}, function(error){
						console.log("Create failed with message " +error.data.errorMessage);					
					});
		}
		
	};
	
	$scope.invalidServerError= function (changeEvent){
		//ng-change does not create an event like on-click
		console.log(changeEvent);
		 var serverMessage = $parse('userForm.firstName.$error.serverMessage');
		 $scope.userForm.$setValidity('firstName', true, $scope.userForm);
         serverMessage.assign($scope, undefined);		
	};
	
});