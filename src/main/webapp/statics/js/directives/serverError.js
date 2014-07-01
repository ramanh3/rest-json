'use strict';
angular.module('myApp').directive('rstServerError', function() {
	return {
		require : 'ngModel',
		link : function(scope, element, attrs, ngModelCtrl) {
			
			//push a function to the $parsers chain so it will be executed on validation 
			ngModelCtrl.$parsers.push(function (viewValue){
				if(ngModelCtrl.$error && ngModelCtrl.$error.serverMessage){
					//clear server side error
					ngModelCtrl.$error.serverMessage = undefined;
					ngModelCtrl.$setValidity('rstServerError',true);
				}
				return viewValue;
			});
		}
	};
});
