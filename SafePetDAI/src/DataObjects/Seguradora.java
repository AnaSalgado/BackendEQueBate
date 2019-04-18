package DataObjects;

public class Seguradora {

	public String id_seg;

	public String nome_seg;

	public String pass_seg;

	public String morada_seg;

	public String telefone_seg;
	
	public String email_seg;
	
	public String estado;

	
	public Seguradora (String  id_seg, String nome_seg, String pass_seg, String morada_seg, String telefone_seg, String email_seg, String estado)
	{
		this. id_seg =  id_seg;
	
		this.nome_seg= nome_seg;
	
		this.pass_seg = pass_seg;
	
		this.morada_seg = morada_seg;
		
		this.telefone_seg = telefone_seg;
		
		this.email_seg= email_seg;
		
		this.estado = estado;
		
	}
	
}
