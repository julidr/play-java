package model;

import java.util.ArrayList;

public class AdministradorDePasswords {
	
	public AdministradorDeInformacion adminInfo= new AdministradorDeInformacion();
	ArrayList<Password> listaDePasswords;
	
	
	public void crearPassword(String plataforma, String cuenta, String password){
		if(verificarDisponibilidad(plataforma)==false){
			System.out.println("Plataforma ya agregada");
		}
		else{
			Password pass= new Password();
			pass.setCuenta(cuenta);
			pass.setPlataforma(plataforma);
			pass.setPassword(password);
			listaDePasswords.add(pass);
			System.out.println(listaDePasswords.size());
			actualizar();
		}
	}
	
	public void actualizar(){
		adminInfo.imprimir(listaDePasswords);
	}
	
	public void cargarInformacion(String ruta){
		adminInfo.cargarInformacion(ruta);
		listaDePasswords= adminInfo.getListaDePasswords();
		
	}
	
	public ArrayList<Password> getListaDePasswords(){
		return listaDePasswords;
	}
	
	public boolean verificarDisponibilidad(String plataforma){
		for(int i=0; i<listaDePasswords.size(); i++){
			if(listaDePasswords.get(i).getPlataforma().equalsIgnoreCase(plataforma)){
				return false;
			}
		}
		return true;
	}

}