package DataObjects;

public class Observacao {
	
	public String id_obser;

	public String data_observ;

	public String descricao;

	public String id_animal;
	
	
	
	public Observacao (String id_obser, String data_observ, String descricao, String id_animal)
	{
		this.id_obser = id_obser;
	
		this.data_observ = data_observ;
	
		this.descricao = descricao;
	
		this.id_animal = id_animal;
		
	}

}
