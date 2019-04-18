package DataObjects;

public class Animal {

	public String id_animal;

	public String nome_animal;

	public String raca;

	public String especie;

	public String data_nasc;
	
	public String id_seg;
	
	public String id_dono;
	
	public String ativo;
	
	
	
	public Animal (String id_animal, String nome_animal, String raca, String especie, String data_nasc, String id_seg, String id_dono, String ati)
	{
		this.id_animal = id_animal;
	
		this.nome_animal = nome_animal;
	
		this.raca = raca;
	
		this.especie = especie;
	
		this.data_nasc = data_nasc;
		
		this.id_seg = id_seg;
		
		this.id_dono = id_dono;
		
		this.ativo = ati;
		
	}
	
	
}
