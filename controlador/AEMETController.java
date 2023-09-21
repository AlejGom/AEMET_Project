package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import modelo.Comunidades;
import modelo.Provincias;
import modelo.TiempoSemana;

public class AEMETController implements Initializable{

	TiempoSemana tiempo;
	Comunidades  comunidades;
	Provincias   provincias;

	//Declaraciones FXML
	@FXML private Button boton_buscar;
	@FXML private PanelesController panel_1Controller;
	@FXML private PanelesController panel_2Controller;
	@FXML private PanelesController panel_3Controller;
	@FXML private PanelesController panel_4Controller;
	@FXML private PanelesController panel_5Controller;
	@FXML private PanelesController panel_6Controller;
	@FXML private ComboBox<String> combo1;
	@FXML private ComboBox<String> combo2;
	@FXML private ComboBox<String> combo3;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		tiempo = new TiempoSemana("");

		// -------------------------------------------------------------------------------------------------
		// -																							   -
		// - BOTON Y COMBOBOX																			   -
		// -																							   -
		// -------------------------------------------------------------------------------------------------

		cargarCombo1();
		LeerProvincias();
		LeerMunicipios();
		mostrarPaneles(false);

		combo1.setOnAction((event)             -> cargarCombo2());
		combo2.setOnAction((event)             -> cargarCombo3());
		combo3.setOnAction((event)             -> codigoMunicipioCombo3());

		boton_buscar.setOnMouseClicked((event) -> asignarCodigo());

	}

	// -------------------------------------------------------------------------------------------------
	// -																							   -
	// - METODOS 																					   -
	// -																							   -
	// -------------------------------------------------------------------------------------------------

	private String leerCombo(String combo) {
		String valorCombo1 = combo1.getValue();
		String valorCombo2 = combo2.getValue();
		String aux = "";

		try {
			if (combo.equals("combo1")) {
				for (int i = 0; i < tiempo.listaComunidades.size(); i++) {
					if (valorCombo1.equals(tiempo.listaComunidades.get(i).getNombreComunidad())) {
						aux = tiempo.listaComunidades.get(i).getCodComunidad();
					}
				}
			}
			if (combo.equals("combo2")) {
				for (int i = 0; i < tiempo.listaProvincias.size(); i++) {
					if (valorCombo2.equals(tiempo.listaProvincias.get(i).getNombreProvincia())) {
						aux = tiempo.listaProvincias.get(i).getCodProvincia();
					}
				}
			}
		} catch (NullPointerException e) {
			combo2.setPromptText("Provincia");
		}


		return aux;
	}

	private void LeerProvincias() {
		tiempo.LeerProvincias();
	}
	private void LeerComunidades() {
		tiempo.LeerComunidades();
	}
	private void LeerMunicipios() {
		tiempo.LeerMunicipios();
	}

	//Combobox	
	private void cargarCombo1() {
		combo2.getItems().clear();
		combo3.getItems().clear();

		LeerComunidades();

		for (int i = 0; i < tiempo.listaComunidades.size(); i++) {
			combo1.getItems().add(tiempo.listaComunidades.get(i).getNombreComunidad());
		}
	}

	private void cargarCombo2() {
		combo2.getItems().clear();
		
		for (int i = 0; i < tiempo.listaProvincias.size(); i++) {
			if (leerCombo("combo1").equals(tiempo.listaProvincias.get(i).getCodComunidad())) {
				combo2.getItems().add(tiempo.listaProvincias.get(i).getNombreProvincia());

			}
		}
	}

	private void cargarCombo3() {
		combo3.getItems().clear();

		for (int i = 0; i < tiempo.listaMunicipios.size(); i++) {

			if (leerCombo("combo2").equals(tiempo.listaMunicipios.get(i).getCodMunicipio().substring(0, 2))) {
				combo3.getItems().add(tiempo.listaMunicipios.get(i).getNomMunicipio());
			}
		}
	}

	private String codigoMunicipioCombo3() {
		String valorCombo3 = combo3.getValue();
		String aux = "";
		LeerMunicipios();

		try {
			for (int i = 0; i < tiempo.listaMunicipios.size(); i++) {
				if (valorCombo3.equals(tiempo.listaMunicipios.get(i).getNomMunicipio())) {
					aux = tiempo.listaMunicipios.get(i).getCodMunicipio();
				}
			}



		} catch (NullPointerException e) {
			//
			combo3.setPromptText("Municipio");
		}
		return aux;
	}
	//Asigna el codigo de la municipio
	private void asignarCodigo() {
		try {
			if (!codigoMunicipioCombo3().equals("")) {
				tiempo = new TiempoSemana(codigoMunicipioCombo3());
				descargar_crear_XML();
				refrescarInformacion();
			}


		} catch (Exception e) {
			//
		}
	}
	//Método para refrescar la informacion de los paneles reutilizados
	private void refrescarInformacion() {

		mostrarPaneles(true);
		this.panel_1Controller.refrescarInformacion(tiempo.getPredicciones().get(0));
		this.panel_2Controller.refrescarInformacion(tiempo.getPredicciones().get(1));
		this.panel_3Controller.refrescarInformacion(tiempo.getPredicciones().get(2));
		this.panel_4Controller.refrescarInformacion(tiempo.getPredicciones().get(3));
		this.panel_5Controller.refrescarInformacion(tiempo.getPredicciones().get(4));
		this.panel_6Controller.refrescarInformacion(tiempo.getPredicciones().get(5));


	}
	private void mostrarPaneles(Boolean sn) {
		this.panel_1Controller.mostrarPaneles(sn);
		this.panel_2Controller.mostrarPaneles(sn);
		this.panel_3Controller.mostrarPaneles(sn);
		this.panel_4Controller.mostrarPaneles(sn);
		this.panel_5Controller.mostrarPaneles(sn);
		this.panel_6Controller.mostrarPaneles(sn);
	}

	private void descargar_crear_XML() {
		tiempo.descargar_crear_XML();
		leerInformacion();
		
	}
	private void leerInformacion() {
		tiempo.informacionXML();

	}
}
