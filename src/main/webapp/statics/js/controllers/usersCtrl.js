app.controller('usersCtrl', function($scope, $http, $location, Users, userService) {
	//Load users on init (could be done on the routers as well).
	$scope.usersList = userService.listUsers();

	$scope.editUser = function(userId) {
		$location.path('edituser/'+userId);	
	};

	$scope.addUser = function() {
		$location.path('createuser/');	
	};

	$scope.deleteUser = function(userId) {
		var resource = userService.deleteUser(userId);
		resource.$promise.then(
				function(updatedUser){
					console.log("User with id "+ updatedUser.id +" deleted successfully");
				}, function(error){
					console.log("Delete failed with message " +error.data.message);		
				});
		$scope.usersList = userService.listUsers();
	};
	
});