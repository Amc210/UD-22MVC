package AppMain;

import Controllers.Controlador;
import Modelos.Modelo;
import Vista.Vista;

/**
 * Hello world!
 *
 */
public class SqlApp {
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
		controlador.iniciarVista();
	}
}
