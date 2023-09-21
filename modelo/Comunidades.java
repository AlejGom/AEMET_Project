package modelo;

public class Comunidades {

	private String codComunidad;
	private String nombreComunidad;

	public Comunidades(String codComunidad, String nombreComunidad) {
		super();
		this.codComunidad = codComunidad;
		this.nombreComunidad = nombreComunidad;
	}
	
	public String getCodComunidad() {
		return codComunidad;
	}

	public void setCodComunidad(String codComunidad) {
		this.codComunidad = codComunidad;
	}

	public String getNombreComunidad() {
		return nombreComunidad;
	}

	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}
	
}
