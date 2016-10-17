package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import anchoFijo.Controlador;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;


public class AdministradorDeInformacion {
	
	private Controlador ctrl= new Controlador();
	private Map<String, Password> hashDePasswords= new TreeMap<String, Password>(String.CASE_INSENSITIVE_ORDER);
	//private Hashtable <String, Password> hashDePasswords= new Hashtable<String, Password>();
	
	public void cargarInformacion(String ruta){
		try {
			ctrl.cargarInformacionArchivos(ruta);
			ArrayList<String> infoArchivos= new ArrayList<String>();
			infoArchivos= ctrl.getInformacionArchivos();
			System.out.println(infoArchivos.get(0));
			System.out.println(infoArchivos.get(1));
			ctrl.construirClase(infoArchivos.get(0),infoArchivos.get(1));
			System.out.println("lalala");
			convertirListaAHashTable();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void convertirListaAHashTable(){
		ArrayList<Object> lista=ctrl.getListaDeObjetos();
		for(int i=0; i<lista.size(); i++){
			Password pass= (Password)lista.get(i);
			hashDePasswords.put(pass.getPlataforma(), pass);
		}
		System.out.println(hashDePasswords.keySet());
	}
	
	public void imprimir(Map<String, Password> hashDePasswords){
		ctrl.setListaDeObjetos(convertirHashTableALista(hashDePasswords));
		ctrl.getListaDeObjetos().size();
		ctrl.imprimir("datos//Passwords.txt");
	}
	
	public ArrayList<Object> convertirHashTableALista(Map<String, Password> hashDePasswords){
		ArrayList<Object> lista= new ArrayList<Object>();
		Iterator<String> it= hashDePasswords.keySet().iterator();
		while(it.hasNext()){
			String key= it.next();
			lista.add(hashDePasswords.get(key));
		}
		return lista;
	}
	
	public Map<String, Password> getHashDePasswords(){
		System.out.println("Devolviendo");
		System.out.println(hashDePasswords.keySet());
		return hashDePasswords;
	}

}