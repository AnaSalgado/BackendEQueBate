package DataObjects;

public class Alerta
{
	public String id_alerta;

	public String titulo_alerta;

	public String descricao_alerta;

	public String data_alerta;

	public String hora_alerta;
	
	
	
	public Alerta(String id, String titulo, String descricao, String data, String hora)
	{
		this.id_alerta = id;
	
		this.titulo_alerta = titulo;
	
		this.descricao_alerta = descricao;
	
		this.data_alerta = data;
	
		this.hora_alerta = hora;
	}
}