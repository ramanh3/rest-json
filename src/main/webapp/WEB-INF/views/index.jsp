<!DOCTYPE html>
<html ng-app="myApp">
<head>
<meta charset="UTF-8">
<title>SpringMVC REST/JSON Demo</title>

<link rel="stylesheet" type="text/css" href="statics/css/ng-grid.css" />
<link rel="stylesheet" type="text/css" href="statics/css/style.css" />
<!--
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="http://angular-ui.github.com/ng-grid/lib/ng-grid.debug.js"></script>
-->
<script src="statics/js/jquery.min.js"></script>
<script src="statics/js/angular.min.js"></script> 
<script src="statics/js/ng-grid.debug.js"></script>
<script src="statics/js/angular-resource.js"></script>

<script type="text/javascript" src="statics/js/main.js"></script>
</head>
<body ng-controller="MyCtrl">
        <div class="gridStyle" ng-grid="gridOptions"></div>
 </body>
 <div>
 	<button ng-click="addUser()">Create User</button>		
 	<button ng-click="getUser()">Read User</button>
 	<button ng-click="updateUser()">Update User</button>
 	<button ng-click="deleteUser()">Delete User</button>
 	
 </div> 
</html>