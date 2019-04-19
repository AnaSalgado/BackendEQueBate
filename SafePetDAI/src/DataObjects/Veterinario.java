package DataObjects;

public class Veterinario extends User{
	
	public String id_vet;

	public String nome_vet;

	public String data_nasc_vet;

	public String telemovel_vet;
	
	public String morada_vet;
	 
	public String id_seg;
	
	public String estado;
	
	
	
	public Veterinario (String email_user, String pass_user, String  id_vet, String nome_vet, String data_nasc_vet,  String telemovel_vet, String morada_vet, String id_seg, String estado)
	{
		super(email_user, pass_user);
		
		this.id_vet =  id_vet;
	
		this.nome_vet= nome_vet;
	
		this.data_nasc_vet = data_nasc_vet;
		
		this.telemovel_vet = telemovel_vet;
		
		this.morada_vet = morada_vet;
		
		this.id_seg = id_seg;
		
		this.estado = estado;
		
	}
	
}
