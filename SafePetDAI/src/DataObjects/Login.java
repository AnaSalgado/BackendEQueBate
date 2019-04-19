package DataObjects;



public boolean class Login (String email, String password){

	for(User user: this.user_list) {
		 if(user.getEmailUser().equals(email) && user.getPassUser().equals(password)){
			user = user;
			return true;
		 }
	}
	return false;
	}







