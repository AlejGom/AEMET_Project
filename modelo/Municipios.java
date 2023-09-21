package modelo;

public class Municipios {

	private String codMunicipio;
	private String nomMunicipio;

	public Municipios(String codMunicipio, String nomMunicipio) {
		super();
		this.codMunicipio = codMunicipio;
		this.nomMunicipio = nomMunicipio;
	}
	
	public String getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getNomMunicipio() {
		return nomMunicipio;
	}

	public void setNomMunicipio(String nomMunicipio) {
		this.nomMunicipio = nomMunicipio;
	}
	
	
}
