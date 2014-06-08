app.controller('usersCtrl', function($scope, $http, $location, Users, userService) {
	//Load users on init (could be done on the routers as well).
	$scope.usersList = userService.listUsers();

	$scope.addUser = function() {
		Users.save({
			'firstName' : 'saved from ui',
			lastName : "lname"
		});
	};

	$scope.editUser = function(userId) {
		$location.path('edituser/'+userId);	
	};

	$scope.addUser = function() {
		$location.path('createuser/');	
	};

	$scope.deleteUser = function(userId) {
		userService.deleteUser(userId);
		$scope.usersList = userService.listUsers();
	};
	
});