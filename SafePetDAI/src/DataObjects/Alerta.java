package DataObjects;

public class Alerta
{
	public String id_alerta;

	public String titulo_alerta;

	public String descricao_alerta;

	public String data_alerta;

	public String hora_alerta;
	
	public String id_dono;
	
	
	
	public Alerta(String id, String titulo, String descricao, String data, String hora, String dono)
	{
		this.id_alerta = id;
	
		this.titulo_alerta = titulo;
	
		this.descricao_alerta = descricao;
	
		this.data_alerta = data;
	
		this.hora_alerta = hora;
		
		this.id_dono = dono;
	}
}