package DataObjects;

import java.util.ArrayList;

public class Login{
	
	public String password;
	public String email;
	public ArrayList<User> user_list = new ArrayList<User>();
	
	public Login (String email, String password) {
		this.email=email;
		this.password=password;
		user_list = new ArrayList<User>();
	}
	
	
	public boolean LoginUser(String email, String password) {

		boolean exis = false;
		for(User user: user_list) {
			if(user.getEmailUser().equals(email) && user.getPassUser().equals(password)){
				exis = true;
				} else {
					exis = false;
					}
			}
		return exis;
		}
	}
