package anchoFijo;

import java.io.IOException;
import java.util.ArrayList;

public class Controlador {
	
	private ControladorDeInformacion cdi= new ControladorDeInformacion();
	private ConstructorDeClase cda = new ConstructorDeClase();
	
	public ArrayList<String> getInformacionArchivos(){
		return cdi.getInformacionArchivo();
	}
	public void cargarInformacionArchivos(String ruta) throws IOException{
		cdi.leerArchivos(ruta, 1);
	}
	
	public void cargarInformacion(String ruta) throws IOException{
		cdi.leerArchivos(ruta, 2);
	}
	
	public void construirClase(String nombreClase, String ruta){
		try {
			cargarInformacion(ruta);
			cda.conseguirAnchos(nombreClase);
			ArrayList<String> lineas= new ArrayList<String>();
			lineas= cdi.getLineasLeidas();
			for(int i=0; i<lineas.size(); i++){
				cda.cortarPorAnchos(lineas.get(i), nombreClase);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public void construirClase(String nombreClase, ArrayList<Object> listaDeObjetos){
		try {
			Class<?> clase= Class.forName(nombreClase);
			cda.conseguirAnchos(nombreClase);
			cda.setTipoAtributos(clase.getDeclaredFields());
			cda.setListaDeObjetos(listaDeObjetos);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public void setFormatoDeLaFecha(String formato){
		cda.setFormatoDate(formato);
	}
	
	public void imprimir(String ruta){
		cda.imprimir(ruta);
	}
	
	public ArrayList<Object> getListaDeObjetos(){
		return cda.getListaDeObjectos();
	}
	
	public void setListaDeObjetos(ArrayList<Object> lista){
		cda.setListaDeObjetos(lista);
	}

}