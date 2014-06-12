/**
 * User CRUDE service
 * Every method returns resource.
 * The $promise can be extracted from the resource to handle the flow.
 * E.g.
 * <code>
 * 	var resource = userService.updateUser(user);
 *			
 *			resource.$promise.then(
 *				function(updatedUser){
 *						//success...
 *					}, function(error){
 *						//failure...		
 *					});
 *	</code>				
 */
app.service('userService', function userService($http, Users) {
	this.listUsers = function(){
	  return  Users.query();
	};
	
	this.addUser = function (user){
		return Users.save(user);
	};
	
	this.getUser = function(userId){
		return Users.get({id:userId});
	};
	
	this.updateUser = function (user){
		//we could user user.$update() as well
		return Users.update(user);
	 };
	
	this.deleteUser = function (userId){
		// we are using remove and not update because delete can
		// make problems in IE.
		return Users.remove({'id':userId});
	};

});
