<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>SpringMVC REST/JSON Demo</title>

<link rel="stylesheet" type="text/css" href="statics/css/ng-grid.css" />
<link rel="stylesheet" type="text/css" href="statics/css/style.css" />
<!--
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/X.Y.Z/angular-route.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="http://angular-ui.github.com/ng-grid/lib/ng-grid.debug.js"></script>
-->
<!-- AngularJS and third-party replayed imports -->
<script src="statics/libs/jquery.min.js"></script>
<script src="statics/libs/angular.js"></script>
<script src="statics/libs/angular-route.js"></script>
<script src="statics/libs/ng-grid.debug.js"></script>
<script src="statics/libs/angular-resource.js"></script>
<script src="statics/libs/angular-translate.js"></script>
<script src="statics/libs/angular-translate-loader-static-files.js"></script>
<!-- Source code related imports -->
<script type="text/javascript" src="statics/js/main.js"></script>
<script type="text/javascript" src="statics/js/route.js"></script>
<script type="text/javascript" src="statics/js/controllers/userCtrl.js"></script>
<script type="text/javascript" src="statics/js/controllers/usersCtrl.js"></script>
<script type="text/javascript" src="statics/js/services/userService.js"></script>
<script type="text/javascript" src="statics/js/services/userAPI.js"></script>
<script type="text/javascript" src="statics/js/directives/serverError.js"></script>


</head>
<body>
	<div ng-view></div>
	<!-- <div ng-include="'statics/pages/usersGrid.html'"></div> -->
</html>		