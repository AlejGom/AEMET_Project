package modelo;

public class Provincias {

	private String codComunidad    = "";
	private String codProvincia    = "";
	private String nombreProvincia = "";

	public Provincias(String codComunidad, String codProvincia, String nombreProvincia) {
		super();
		this.codComunidad = codComunidad;
		this.codProvincia = codProvincia;
		this.nombreProvincia = nombreProvincia;
	}
	
	public String getCodComunidad() {
		return codComunidad;
	}

	public void setCodComunidad(String codComunidad) {
		this.codComunidad = codComunidad;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
}
