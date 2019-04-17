package DataObjects;

public class Veterinario {
	
	public String id_vet;

	public String nome_vet;

	public String data_nasc_vet;

	public String pass_vet;

	public String telemovel_vet;
	
	public String morada_vet;
	
	public String email_vet;
	 
	public String id_seg;
	

	
	public Veterinario (String  id_vet, String nome_vet, String data_nasc_vet, String pass_vet, String telemovel_vet, String morada_vet, String email_vet, String id_seg)
	{
		this.id_vet =  id_vet;
	
		this.nome_vet= nome_vet;
	
		this.data_nasc_vet = data_nasc_vet;
	
		this.pass_vet = pass_vet;
		
		this.telemovel_vet = telemovel_vet;
		
		this.morada_vet = morada_vet;
		
		this.email_vet = email_vet;
		
		this.id_seg = id_seg;
		
	}
	
}
