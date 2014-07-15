app = angular.module('myApp', ['ngGrid'
                               , 'ngResource'
                               , 'ngRoute'
                               ,  'pascalprecht.translate' ]);

app.config(function($translateProvider) {
	$translateProvider.useStaticFilesLoader({
		prefix : 'statics/i18n/app-',
		suffix : '.json'
	});
	$translateProvider.preferredLanguage('en_US');

});
