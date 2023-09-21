package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import modelo.TiempoDia;
import modelo.TiempoSemana;

public class PanelesController {

	@FXML private Label fecha;
	@FXML private ImageView estadoCielo;
	@FXML private Label tempMax;
	@FXML private Label tempMin;
	@FXML private Label sensMax;
	@FXML private Label sensMin;
	@FXML private Label velViento;
	@FXML private Label dirViento;
	@FXML private Label uvMax;
	@FXML private AnchorPane panelesTiempo;
	
	TiempoSemana tiempo = new TiempoSemana();
	

	public void refrescarInformacion(TiempoDia td) {
		
		Image image1 = new Image("icons/" + tiempo.numeroiconoEC(td.getEstadoCielo()) + ".png");
		
		estadoCielo.setImage(image1);
		tempMax.setText(td.getTempMax());
		tempMin.setText(td.getTempMin());
		sensMax.setText(td.getSensMax());
		sensMin.setText(td.getSensMin());
		velViento.setText(td.getVelViento());
		dirViento.setText(td.getDirViento());
		uvMax.setText(td.getUvMax());

		
	}
	public void mostrarPaneles(Boolean sn) {
		panelesTiempo.setVisible(sn);
	}
}
