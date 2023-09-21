package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class TiempoSemana {


	//Atributos
	private String codigo = "";
	private int aux = 0;
	public  ArrayList<TiempoDia> predicciones;
	public  ArrayList<Comunidades> listaComunidades;
	public  ArrayList<Provincias> listaProvincias;
	public  ArrayList<Municipios> listaMunicipios;
	public ArrayList<EstadosCielo> listaEstadosCielo;

	//Constructores
	public TiempoSemana(String codigo) {
		super();
		this.codigo            = codigo;
		this.predicciones      = new ArrayList<TiempoDia>();		
		this.listaComunidades  = new ArrayList<Comunidades>();
		this.listaProvincias   = new ArrayList<Provincias>();
		this.listaMunicipios   = new ArrayList<Municipios>();
		this.listaEstadosCielo = new ArrayList<EstadosCielo>();

	}
	public TiempoSemana() {
		this.listaEstadosCielo = new ArrayList<EstadosCielo>();
	}

	//Getters and Setters
	public ArrayList<TiempoDia> getPredicciones() {
		return predicciones;
	}

	public void setPredicciones(ArrayList<TiempoDia> predicciones) {
		this.predicciones = predicciones;
	}

	//Metodos
	public void descargar_crear_XML() {
		try {
			//Iniciar URL donde se encuentra el fichero a leer
			URL url = new URL("https://www.aemet.es/xml/municipios/localidad_" + this.codigo + ".xml");

			//Utilizar el scanner con el fichero especificado en la URL
			Scanner sc = new Scanner( url.openStream() );

			//Crear fichero local donde guardar la informacion
			String fichero = "localidad_" + this.codigo + ".xml";
			PrintWriter pw = new PrintWriter( new File(fichero) );

			String linea;
			//Leer del scanner
			while ( sc.hasNext() ) {


				//Leer linea del fichero de Internet
				linea = sc.nextLine();


				//Escribir linea en el fichero local
				pw.println( linea );

			}
			pw.close();
		} catch (IOException ex) {
			//
			ex.printStackTrace();
		}
	}

	public void informacionXML() {

		try {
			File fxmlFile = new File("localidad_" + this.codigo + ".xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			//Documento a recorrer con los datos del fichero XML
			Document doc = dBuilder.parse(fxmlFile);

			//La forma normal es util para operaciones que requieren una estructura de arbol de documento particular
			doc.getDocumentElement().normalize();

			//Recuperar una lista con los elementos DIA
			NodeList listaDias = doc.getElementsByTagName("dia");

			//Recorrer lista de los elementos DIA
			for (int i = 0; i < listaDias.getLength(); i++) {

				TiempoDia td = new TiempoDia();

				Node elementoDia = listaDias.item(i);
				Element eElement = (Element) elementoDia;

				//1.- Dia

				//2.- Estado del cielo
				NodeList nEstadoCielo = eElement.getElementsByTagName("estado_cielo");
				for (int j = 0; j < nEstadoCielo.getLength(); j++) {

					Node estadoCielo = nEstadoCielo.item(j);
					Element eEstadoCielo = (Element) estadoCielo;
					//Guardar todas las descripciones
					td.setEstadoCielo(eEstadoCielo.getAttribute("descripcion"));
				}

				//3.- Temperatura
				NodeList nTemp = eElement.getElementsByTagName("temperatura");
				for (int j = 0; j < nTemp.getLength(); j++) {

					Node temperatura = nTemp.item(j);
					Element eTemp = (Element) temperatura;
					// Guardar todas las temperaturas maximas
					NodeList tempMax = eTemp.getElementsByTagName("maxima");
					for (int k = 0; k < tempMax.getLength(); k++) {
						Node temperaturaMaxima = tempMax.item(k);
						Element eTempMax = (Element) temperaturaMaxima;

						td.setTempMax(eTempMax.getTextContent());
					}

					//Guardar todas las temperaturas minimas
					NodeList tempMin = eTemp.getElementsByTagName("minima");
					for (int k = 0; k < tempMin.getLength(); k++) {
						Node temperaturaMinima = tempMin.item(k);
						Element eTempMin = (Element) temperaturaMinima;

						td.setTempMin(eTempMin.getTextContent());
					}
				}

				//4.- Sensacion termica
				NodeList nsTerm = eElement.getElementsByTagName("sens_termica");
				for (int j = 0; j < nsTerm.getLength(); j++) {

					Node sensacionTermica = nsTerm.item(j);
					Element esTerm = (Element) sensacionTermica;

					//Guardar todas las sensaciones maximas
					NodeList sensMax = esTerm.getElementsByTagName("maxima");
					for (int k = 0; k < sensMax.getLength(); k++) {
						Node sensacionMaxima = sensMax.item(k);
						Element eSensMax = (Element) sensacionMaxima;

						td.setSensMax(eSensMax.getTextContent());
					}
					//Guardar todas las sensaciones minima
					NodeList sensMin = esTerm.getElementsByTagName("minima");
					for (int k = 0; k < sensMin.getLength(); k++) {
						Node sensacionMinima = sensMin.item(k);
						Element eSensMin = (Element) sensacionMinima;

						td.setSensMin(eSensMin.getTextContent());
					}
				}

				//5.- Viento
				NodeList ndViento = eElement.getElementsByTagName("viento");
				for (int j = 0; j < ndViento.getLength(); j++) {

					Node datosViento = ndViento.item(j);
					Element eDatosViento = (Element) datosViento;

					// Guardar velocidad viento
					NodeList velViento = eDatosViento.getElementsByTagName("velocidad");
					for (int k = 0; k < velViento.getLength(); k++) {
						Node velocidadViento = velViento.item(k);
						Element eVelViento = (Element) velocidadViento;

						td.setVelViento(eVelViento.getTextContent());
					}

					//Guardar direccion viento
					NodeList dirViento = eDatosViento.getElementsByTagName("direccion");
					for (int k = 0; k < dirViento.getLength(); k++) {
						Node direccionViento = dirViento.item(k);
						Element eDirViento = (Element) direccionViento;

						td.setDirViento(eDirViento.getTextContent());
					}
				}
				//6.- UV maxima
				NodeList uvMaxima = eElement.getElementsByTagName("uv_max");
				for (int j = 0; j < uvMaxima.getLength(); j++) {
					
					Node uvMax = uvMaxima.item(j);
					Element eUVMax = (Element) uvMax;
					
					td.setUvMax(eUVMax.getTextContent());
				}
				
				predicciones.add(td);
			}

		} catch (ParserConfigurationException e) {
			//
			e.printStackTrace();
		} catch (SAXException e) {
			//
			e.printStackTrace();
		} catch (IOException e) {
			//
			e.printStackTrace();
		}		
	}

	// -------------------------------------------------------------------------------------------------
	// -																							   -
	// - METODOS 																					   -
	// -																							   -
	// -------------------------------------------------------------------------------------------------

	public void LeerProvincias() {
		try {
			Scanner teclado = new Scanner(new File("src/archivos/Provincias.txt"));

			String linea;

			String codCom;
			String codProv;
			String nomProv;

			String[] trozos;

			while (teclado.hasNext()) {

				linea = teclado.nextLine();

				trozos = linea.split(";");
				
				codCom  = trozos[0];
				codProv = trozos[1];
				nomProv = trozos[2];


				Provincias provincias = new Provincias(codCom, codProv, nomProv);

				listaProvincias.add(provincias);
			}
			teclado.close();

		} catch (FileNotFoundException e) {
			//
			e.printStackTrace();
		}
	}
	public void LeerComunidades() {
		try {
			Scanner teclado = new Scanner(new File("src/archivos/Comunidades.txt"));

			String linea;

			String codCom;
			String nomCom;

			String[] trozos;

			while (teclado.hasNext()) {

				linea = teclado.nextLine();

				trozos = linea.split(";");

				codCom  = trozos[0];
				nomCom  = trozos[1];

				Comunidades comunidades = new Comunidades(codCom, nomCom);

				listaComunidades.add(comunidades);
			}
			teclado.close();

		} catch (FileNotFoundException e) {
			//
			e.printStackTrace();
		}
	}
	public void LeerMunicipios() {
		try {
			Scanner teclado = new Scanner(new File("src/archivos/Municipios.txt"));

			String linea;

			String codMun;
			String nomMun;

			String[] trozos;

			while (teclado.hasNext()) {

				linea = teclado.nextLine();

				trozos = linea.split(";");

				codMun  = trozos[0];
				nomMun  = trozos[1];

				Municipios municipios = new Municipios(codMun, nomMun);

				listaMunicipios.add(municipios);
			}
			
			teclado.close();

		} catch (FileNotFoundException e) {
			//
			e.printStackTrace();
		}
	}
	public void leerEstadosCielo() {
		try {
			Scanner teclado = new Scanner(new File("src/archivos/estadosCielo.txt"));

			String linea;

			while (teclado.hasNext()) {
				linea = teclado.nextLine();
				EstadosCielo estadosCielo = new EstadosCielo(linea);
				listaEstadosCielo.add(estadosCielo);
			}

			teclado.close();
		} catch (FileNotFoundException e) {
			//
			e.printStackTrace();
		}
	}
	//Conseguir numero icono estado cielo
	public int numeroiconoEC(String s) {
		int n = 1;
		
		if (this.aux == 0) {
			leerEstadosCielo();
		}
		for (int i = 0; i < listaEstadosCielo.size(); i++) {
			if (listaEstadosCielo.get(i).getEstadoCielo().equals(s)) {
				n = i + 1;
				this.aux++;

			}
		}
		return n;
	}
}
