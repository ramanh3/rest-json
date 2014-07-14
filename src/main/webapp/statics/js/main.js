app = angular.module('myApp',
						 [ 'ngGrid'
						 , 'ngResource'
						 , 'ngRoute'
						 , 'pascalprecht.translate' 
						 ]);
app.config(function($translateProvider) {
	  $translateProvider.translations('en', {
		'org.ramanh.domain.user':{
				 'username':'User Name'
				,'invalid':{
					'lastname' :{
						'character':  'Last name should include letters and numbers only.',
						'minlength':  'Last name should be at least three letters.'
					},
					'firstname':{
						'minlength': 'First name should be at least three letters',
						'character': 'First name should include letters only.'
					},
				},
		}		
	  }).preferredLanguage('en');;
	});
