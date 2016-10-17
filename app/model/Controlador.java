package model;

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

}