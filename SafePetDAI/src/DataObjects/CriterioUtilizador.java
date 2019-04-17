package DataObjects;

public class CriterioUtilizador {

	public String id_animal;

	public String ritmo_min;

	public String ritmo_max;

	public String raio;

	public String latitude;
	
	public String longitude;
	
	

	public CriterioUtilizador (String id_animal, String ritmo_min, String ritmo_max, String raio, String latitude, String longitude)
	{
		this.id_animal = id_animal;
	
		this.ritmo_min = ritmo_min;
	
		this.ritmo_max = ritmo_max;
	
		this.raio = raio;
	
		this.latitude = latitude;
		
		this.longitude = longitude;
		
	}
	
	
}
