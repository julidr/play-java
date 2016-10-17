package model;

import anchoFijo.Campo;
import anchoFijo.Tomate;

@Tomate
public class Password {
	
	@Campo(ancho=21)private String plataforma;
	@Campo(ancho=58)private String cuenta;
	@Campo(ancho=39)private String password;
	
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Password [plataforma=" + plataforma + ", cuenta=" + cuenta + ", password=" + password + "]";
	}
	
}