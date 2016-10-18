package model;

import java.util.Map;
import java.util.ArrayList;

public class Controlador {
	
	AdministradorDePasswords adminPass;
	
	public Controlador(){
		adminPass= new AdministradorDePasswords();
	}
	
	public void cargarInformacion(String ruta){
		adminPass.cargarInformacion(ruta);
	}
	
	public void agregarNuevoPassword(String plataforma, String cuenta, String password){
		adminPass.crearPassword(plataforma, cuenta, password);
	}
	
	public Password buscarPassword(String plataforma){
		return adminPass.buscarPassword(plataforma);
	}
	
	public Map<String, Password> getHashDePasswords(){
		return adminPass.getHashDePasswords();
	}
	
	public ArrayList<Password> getListaDePasswords(){
		return adminPass.getListaDePasswords();
	}

}