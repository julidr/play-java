package model;

import java.util.Map;
import java.util.ArrayList;

public class AdministradorDePasswords {
	
	public AdministradorDeInformacion adminInfo= new AdministradorDeInformacion();
	Map <String, Password> hashDePasswords;
	
	
	public void crearPassword(String plataforma, String cuenta, String password){
		if(hashDePasswords.containsKey(plataforma)){
			System.out.println("Plataforma ya agregada");
		}
		else{
			Password pass= new Password();
			pass.setCuenta(cuenta);
			pass.setPlataforma(plataforma);
			pass.setPassword(password);
			hashDePasswords.put(pass.getPlataforma(), pass);
			System.out.println(hashDePasswords.size());
			actualizar();
		}
	}
	
	public Password buscarPassword(String plataforma){
		if(hashDePasswords.containsKey(plataforma)){
			return hashDePasswords.get(plataforma);
		}
		return null;
	}
	
	public void actualizar(){
		adminInfo.imprimir(hashDePasswords);
	}
	
	public void cargarInformacion(String ruta){
		adminInfo.cargarInformacion(ruta);
		hashDePasswords= adminInfo.getHashDePasswords();
		
	}
	
	public Map<String, Password> getHashDePasswords(){
		return hashDePasswords;
	}
	
	public ArrayList<Password> getListaDePasswords(){
		return adminInfo.getListaDePasswords();
	}

}