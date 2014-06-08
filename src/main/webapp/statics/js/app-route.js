// Set up our mappings between URLs, templates, and controllers
app.config(function($routeProvider) {
	$routeProvider
		.when('/', {
			controller: 'usersCtrl',
			templateUrl: 'statics/pages/usersGrid.html'
		})
		// Notice that for the detail view, we specify a parameterized URL
		// component by placing a colon in front of the id
		.when('/edituser/:id', {
			controller: 'userCtrl',
			templateUrl: 'statics/pages/user.html'
		})
		.when('/createuser/', {
			controller: 'userCtrl',
			templateUrl: 'statics/pages/user.html'
		})		
		.otherwise({
			redirectTo: '/'
			});
	});		