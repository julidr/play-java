package model;

import java.io.IOException;
import java.util.ArrayList;
import anchoFijo.Controlador;


public class AdministradorDeInformacion {
	
	private Controlador ctrl= new Controlador();
	private ArrayList<Password> listaDePasswords= new ArrayList<Password>();
	
	public void cargarInformacion(String ruta){
		try {
			ctrl.cargarInformacionArchivos(ruta);
			ArrayList<String> infoArchivos= new ArrayList<String>();
			infoArchivos= ctrl.getInformacionArchivos();
			System.out.println(infoArchivos.get(0));
			System.out.println(infoArchivos.get(1));
			ctrl.construirClase(infoArchivos.get(0),infoArchivos.get(1));
			System.out.println("lalala");
			listaDePasswords= getListaDeObjetos();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<Password> getListaDeObjetos(){
		ArrayList<Object> lista=ctrl.getListaDeObjetos();
		ArrayList<Password> listaPasswords= new ArrayList<Password>();
		
		for(int i=0; i<lista.size(); i++){
			Password pass= (Password)lista.get(i);
			listaPasswords.add(pass);
		}
		return listaPasswords;
	}
	
	
	public void imprimir(ArrayList<Password> listaDePasswords){
		ctrl.setListaDeObjetos(convertirAListaDeObjetos(listaDePasswords));
		ctrl.getListaDeObjetos().size();
		ctrl.imprimir("datos//Passwords.txt");
	}
	
	public ArrayList<Object> convertirAListaDeObjetos(ArrayList<Password> listaDePasswords){
		ArrayList<Object> lista= new ArrayList<Object>();
		for(int i=0; i<listaDePasswords.size(); i++){
			lista.add(listaDePasswords.get(i));
		}
		return lista;
	}
	
	public ArrayList<Password> getListaDePasswords(){
		return listaDePasswords;
	}

}