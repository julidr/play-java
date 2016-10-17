package anchoFijo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ControladorDeInformacion {
	
	private ArrayList<String> informacionArchivo= new ArrayList<String>();
	private ArrayList<String> lineasLeidas = new ArrayList<String>();
	private BufferedReader leer;
	
	public void leerArchivos(String ruta, int opc) throws IOException{
		File archivo= new File(ruta);
		if(archivo.exists()){
			leer= new BufferedReader(new FileReader(ruta));
			if(opc==1){
				String lectura=leer.readLine();;
				while(lectura!=null){
					informacionArchivo.add(lectura);
					lectura=leer.readLine();
				}
				leer.close();
			}
			else if(opc==2){
				String lectura=leer.readLine();;
				while(lectura!=null){
					lineasLeidas.add(lectura);
					lectura=leer.readLine();
				}
				leer.close();
			}
		}
		else if(!archivo.exists()){
			throw new FileNotFoundException("La Ruta No Existe");
		}
		else{
			throw new IOException("Error en Lectura");
		}
	}
	
	public ArrayList<String> getLineasLeidas(){
		return lineasLeidas;
	}
	
	public String obtenerInformacionArhivo(int posicion){
		if(posicion>=informacionArchivo.size()){
			throw new IndexOutOfBoundsException("Indice No Valido");
		}
		return informacionArchivo.get(posicion);
	}
	
	public ArrayList<String> getInformacionArchivo(){
		return this.informacionArchivo;
	}
	
	public boolean listaVaciaInfoArchivos(){
		return informacionArchivo.isEmpty();
	}
}