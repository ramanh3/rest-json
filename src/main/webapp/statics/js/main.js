var app = angular.module('myApp', ['ngGrid','ngResource','ngRoute']);

angular.module('myApp').factory('Users', ['$resource', function($resource) {
	/**
	 *  Define our RESTfull API as $resource so we could support CRUD
	 **/
	return $resource('rest/users/:id',
			//actions: query ,save and remove are predefined see https://docs.angularjs.org/api/ngResource/service/$resource
			{remove:{method:'DELETE'},params:{id: '@id'}},
			{update:{method:'PUT'}}
		);
	}]);

