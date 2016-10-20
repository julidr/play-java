package model;

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
	
	
	public ArrayList<Password> getListaDePasswords(){
		return adminPass.getListaDePasswords();
	}
	
	public boolean verificarDisponibilidad(String plataforma){
		return adminPass.verificarDisponibilidad(plataforma);
	}

}