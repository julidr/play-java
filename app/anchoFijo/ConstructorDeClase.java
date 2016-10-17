package anchoFijo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;

public class ConstructorDeClase {
	
	private ArrayList<Object> listaDeObjetos = new ArrayList<Object>();
	private ArrayList<Integer> anchos = new ArrayList<Integer>();
	private ControladorDeAnotaciones cda = new ControladorDeAnotaciones();
	private String formatoDate="yyyy/mm/dd";
	private Field tipoAtributos[];
		
	public void conseguirAnchos(String nombreClase) throws ClassNotFoundException, TomateException{
		System.out.println("Lector de Clases");
		Class<?> clase= Class.forName(nombreClase);
		cda.lectorAnotacionesDeClase(clase);
		if(cda.getEsTomate()==false){
			throw new TomateException("La Clase no tiene la anotacion Tomate");
		}
		else if(cda.getEsCampo()==false){
			throw new TomateException("El atributo no tiene la anotacion Campo");
		}
		else{
			anchos= cda.getAnchos();
		}
	}
	
	public void construirClase(String nombreClase, ArrayList<String> trozos)throws TomateException{
		try {
			Class<?> clase= Class.forName(nombreClase);
			Object nn= clase.newInstance();
			tipoAtributos= clase.getDeclaredFields();
			if(cda.getAnchos().size()<tipoAtributos.length){
				throw new TomateException("No todos los atributos de la clase tienen la anotacion Campo");
			}
			else{
				for(int i=0; i<tipoAtributos.length; i++){
					Class<?> tipoAtributo= tipoAtributos[i].getType();
					Method metodo= clase.getMethod("set"+inicialMayuscula(tipoAtributos[i].getName()), tipoAtributo);
					implementarMetodo(nn,metodo,trozos.get(i), tipoAtributo.getName());
				}
				listaDeObjetos.add(nn);
				System.out.println(nn.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error 3: " + e);
		}
	}
	
	public void cortarPorAnchos(String datos, String nombreClase) throws TomateException{
		ArrayList<String> trozos= new ArrayList<String>();
		for(int i=0; i< anchos.size(); i++){
			trozos.add(datos.substring(0, anchos.get(i)).trim());
			datos= datos.substring(anchos.get(i));
		}
		System.out.println(trozos);
		construirClase(nombreClase, trozos);
	}
	
	public String inicialMayuscula(String atributo){
		String inicial= atributo.substring(0,1).toUpperCase();
		atributo=inicial+atributo.substring(1, atributo.length());
		return atributo;
	}
	
	public void imprimir(String ruta){
		try {
			BufferedWriter escribir= new BufferedWriter(new FileWriter(ruta));
			//Collections.shuffle(listaDeObjetos);
			String getis="get";
			for(int i=0; i<listaDeObjetos.size(); i++){
				for(int j=0; j<tipoAtributos.length; j++){
					if(tipoAtributos[j].getType().getName().equals("java.lang.Boolean")|| tipoAtributos[j].getType().getName().equals("boolean") ){
						getis="set";
					}
					Method mm=listaDeObjetos.get(i).getClass().getMethod(getis+inicialMayuscula(tipoAtributos[j].getName()));
					if(tipoAtributos[j].getType().getName().equals("java.util.Date")){
						SimpleDateFormat formatoDelTexto= new SimpleDateFormat(formatoDate);
						String fecha= formatoDelTexto.format(mm.invoke(listaDeObjetos.get(i)));
						escribir.write(String.format("%1$-"+anchos.get(j)+"s",fecha));
					}
					else{
						escribir.write(String.format("%1$-"+anchos.get(j)+"s",mm.invoke(listaDeObjetos.get(i))));
					}
				}
				escribir.newLine();
			}
			escribir.close();
		} catch (Exception e) {
			System.out.println("Error 4: " + e);
		}
		
	}
	
	public void implementarMetodo(Object nn,Method metodo,String atributo, String tipoAtributo) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
		if(tipoAtributo.equals("java.lang.String")){
			metodo.invoke(nn,atributo);
		}
		else if(tipoAtributo.equals("java.lang.Integer") || tipoAtributo.equals("int") ){
			int entero= Integer.parseInt(atributo);
			metodo.invoke(nn,entero);
		}
		else if(tipoAtributo.equals("java.lang.Character")|| tipoAtributo.equals("char")){
			char character= atributo.charAt(0);
			metodo.invoke(nn,character);
			
		}
		else if(tipoAtributo.equals("java.lang.Double") || tipoAtributo.equals("double")){
			double doble= Double.parseDouble(atributo);
			metodo.invoke(nn,doble);
		}
		else if(tipoAtributo.equals("java.lang.Long") || tipoAtributo.equals("long")){
			long largo= Long.parseLong(atributo);
			metodo.invoke(nn, largo);
		}
		else if(tipoAtributo.equals("java.lang.Float") || tipoAtributo.equals("float")){
			float flotante= Float.parseFloat(atributo);
			metodo.invoke(nn,flotante);
		}
		else if(tipoAtributo.equals("java.lang.Boolean")|| tipoAtributo.equals("boolean") ){
			boolean booleano= Boolean.parseBoolean(atributo);
			metodo.invoke(nn,booleano);
		}
		else if(tipoAtributo.equals("java.util.Date")){
			SimpleDateFormat formatoDelTexto= new SimpleDateFormat("yyyy/mm/dd");
			String fecha= atributo+"";
			metodo.invoke(nn,formatoDelTexto.parse(fecha));
		}
	}
	
	public ArrayList<Object> getListaDeObjectos(){
		return listaDeObjetos;
	}
	
	public boolean estaVaciaListaDeObjectos(){
		return listaDeObjetos.isEmpty();
	}
	
	public ArrayList<Integer> getAnchos(){
		return anchos;
	}
	
	public void setListaDeObjetos(ArrayList<Object> lista){
		listaDeObjetos= new ArrayList<Object>();
		this.listaDeObjetos= lista;
	}
	
	public void setTipoAtributos(Field[] tipoAtributos){
		this.tipoAtributos= tipoAtributos;
	}
	
	public void setFormatoDate(String formato){
		this.formatoDate= formato;
	}
	
}