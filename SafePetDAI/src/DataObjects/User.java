package DataObjects;

public class User {
	public String email_user;

	public String pass_user;

	
	
	
	public User (String  email_user, String pass_user)
	{
		this.email_user = email_user;
	
		this.pass_user = pass_user;
	
}
	
	public String getEmailUser() {
		return email_user;
		}

	public String getPassUser() {
		return pass_user;
	}

	


}