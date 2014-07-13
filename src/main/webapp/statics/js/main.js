app = angular.module('myApp',
						 [ 'ngGrid'
						 , 'ngResource'
						 , 'ngRoute'
						 , 'pascalprecht.translate' 
						 ]);
app.config(function($translateProvider) {
	  $translateProvider.translations('en', {
		'org.ramanh.domain.invalid.lastname':  'Last name should include letters and numbers only.', 
		'org.ramanh.domain.invalid.firstname': 'First name should include letters only.',
	  }).preferredLanguage('en');;
	});
