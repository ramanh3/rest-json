app.service('userService', function userService($http, Users) {
	this.listUsers = function(){
	  return  Users.query();
	};
	
	this.addUser = function (user){
		Users.save(user);
	};
	
	this.getUser = function(userId){
		return Users.get({id:userId});
	};
	
	this.updateUser = function (user){
		//we could user user.$update() as well
		Users.update(user);
	 };
	
	this.deleteUser = function (userId){
		// we are using remove and not update because delete can
		// make problems in IE.
		Users.remove({'id':userId});
	};

});
