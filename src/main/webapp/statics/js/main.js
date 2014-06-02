angular.module('myApp', ['ngGrid','ngResource']);

angular.module('myApp')
	.factory('Users', ['$resource',"id",function($resource,id){
	return $resource('rest/users/');	  
}]);

angular.module('myApp').factory('Users', ['$resource', function($resource) {
	/**
	 *  Define our RESTfull API as $resource so we could support CRUD
	 **/
	return $resource('rest/users/:userId',
			//paramDefaults
			{userId: '@id'},
			//actions: query ,save and remove are predefined see https://docs.angularjs.org/api/ngResource/service/$resource
			//add update call 
			{update:{method:'PUT'}}
		);
	}]);

	angular.module('myApp')
       .controller('MyCtrl', function($scope,$http,Users) {
	//Create a resource for users
	
	$scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    };
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [5, 10, 20],
        pageSize: 5,
        currentPage: 1
    };  
    $scope.setPagingData = function(data, page, pageSize){	
        this.pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.myData = this.pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };
    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                $http.get('rest/users').success(function (largeLoad) {		
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });            
            } else {
               	Users.query(function(data) {
                	 $scope.setPagingData(data,page,pageSize);
                });               
            }
        }, 100);
    };
    
    $scope.addUser = function (){
		Users.save({'firstName':'saved from ui',lastName: "lname"});
    };

    $scope.getUser = function (){
    	this.selectedUsers = $scope.gridOptions.selectedItems;
    	if($scope.isSingleUserSelected('delete')){
    		Users.get({'userId':this.selectedUsers[0].id});
    	}
    };
    
    $scope.updateUser = function (){
    	this.selectedUsers = $scope.gridOptions.selectedItems;
    	if($scope.isSingleUserSelected('update')){
    			this.user = this.selectedUsers[0]; 
    			this.user.firstName = this.user.firstName+" updated!";
    			this.user.$update(); 
    		};
    };
   
    $scope.deleteeUser = function (){
    	this.selectedUsers = $scope.gridOptions.selectedItems;
    	if($scope.isSingleUserSelected('delete')){
    		//we are using remove and not update because delete can make problems in IE.
    		Users.remove({'userId':this.selectedUsers[0].id});
    	}
    };
    
    
    $scope.isSingleUserSelected = function(action){
    	this.selectedUsers = $scope.gridOptions.selectedItems;
    	this.singleSelection = true ;
    	
    	if(this.selectedUsers.length==0){
    		alert('please select a user to '+action+'.');
    		singleSelection = false;
    	}else if(this.selectedUsers.length>1){
    		alert('Multi selection is not allowed.');
    		singleSelection = false;
    	}
    	
    	return this.singleSelection;
    };
    
    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	
    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
	
    $scope.gridOptions = {
        data: 'myData',
        selectedItems: [],
        enablePaging: true,
        showFooter: true,        
        totalServerItems:'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions
    };
});