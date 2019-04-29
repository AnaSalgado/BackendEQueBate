package DataObjects;

public class Vacina {
	
	public String id_vacina;

	public String nome_vac;

	public String descricao_vac;

	public String data_validade;

	public String id_animal;
	
	
	public Vacina (String  id_vacina, String nome_vac, String descricao_vac, String data_validade, String id_animal)
	{
		this. id_vacina =  id_vacina;
	
		this.nome_vac = nome_vac;
	
		this.descricao_vac = descricao_vac;
	
		this.data_validade = data_validade;
		
		this.id_animal = id_animal;
	
}
}