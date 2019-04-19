package DataObjects;

public class Dono extends User{
	
	public String id_dono;

	public String nome_dono;

	public String morada_dono;

	public String telemovel_dono;

	public String estado;

	
	
	public Dono (String email_user, String pass_user, String id_dono, String nome_dono, String morada_dono, String telemovel_dono, String estado)
	{
		super(email_user, pass_user);
		
		
		this.id_dono = id_dono;
	
		this.nome_dono = nome_dono;
	
		this.morada_dono = morada_dono;
	
		this.telemovel_dono = telemovel_dono;
	    
		this.estado = estado;
	
		
	}
	
	
	
}