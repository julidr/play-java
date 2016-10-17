package anchoFijo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ControladorDeAnotaciones {
		
	private ArrayList<Integer> anchos= new ArrayList<Integer>();
	private boolean esTomate= false;
	private boolean esCampo= false;
	
	public void lectorAnotacionesDeClase(Class<?> clase) throws TomateException{
	    System.out.println("Hola lei la primera vez "+clase.getName());
		Annotation anotacionesDeClase[]= clase.getAnnotations();
		for(int i=0; i<anotacionesDeClase.length; i++){
			if(anotacionesDeClase[i] instanceof Tomate){
			    System.out.println("Soy Tomate");
				esTomate=true;
				lectorAnotacionesDeCampo(clase);
				break;
			}
			else{
				throw new TomateException("La Clase no tiene la anotacion Tomate");
			}
		}
	}
	
	public void lectorAnotacionesDeCampo(Class<?> clase) throws TomateException{
		Field[] campos= clase.getDeclaredFields();
		for(int i=0; i<campos.length; i++){
			Annotation anotacionesDeCampo[]= campos[i].getAnnotations();
			for(int j=0; j<anotacionesDeCampo.length; j++){
				if(anotacionesDeCampo[j] instanceof Campo){
				    System.out.println("Entre a Campo");
					esCampo=true;
					anchos.add(((Campo) anotacionesDeCampo[j]).ancho());
				}
				else{
					esCampo=false;
					throw new TomateException("El atributo no tiene la anotacion Campo");
				}
			}
		}
	}
	
	public ArrayList<Integer> getAnchos(){
		return anchos;
	}
	
	public boolean getEsTomate(){
		return esTomate;
	}
	
	public boolean getEsCampo(){
		return esCampo;
	}
	
}