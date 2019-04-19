package DataObjects;

public class Seguradora extends User {

	public String id_seg;

	public String nome_seg;

	public String morada_seg;

	public String telefone_seg;
	
    public String estado;
	
	
    
    public Seguradora (String email_user, String pass_user, String  id_seg, String nome_seg, String morada_seg, String telefone_seg, String estado)
	{
		
		super(email_user, pass_user);
		
		this. id_seg =  id_seg;
	
		this.nome_seg= nome_seg;
	
		this.morada_seg = morada_seg;
		
		this.telefone_seg = telefone_seg;
		
		this.estado = estado;

	}
	
}