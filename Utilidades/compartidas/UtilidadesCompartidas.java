package compartidas;

public class UtilidadesCompartidas {

	 /* 
	 * Interfaz 
	 * Cabecera:public String ajustaLongitud(String cadena,int longitud)
	 * Proceso:Corta una cadena hasta una longitud máxima
	 * Precondiciones:longitud>0
	 * Entrada:Un String para la cadena que queremos modificar
	 * 			Un entero para la longitud máxima
	 * Salida:La cadena con longitud igual a la indicada
	 * Entrada/Salida:Ninguna
	 * Postcondiciones:En caso de acortar, mostrará en pantalla que ha acortado
	 */
	public static String ajustaLongitud(String cadena,int longitud){
		String salida=cadena;
		if(salida.length()>longitud){
			System.out.println("Error, la longitud no puede ser mayor que "+longitud);
			salida=salida.substring(0,longitud);
			System.out.println("se guardará: "+salida);
			
		}
		return salida;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public String completaCadena(String cadena,int longitud)
	 * Proceso:Completa una cadena con * hasta llegar a la longitud indicada
	 * Precondiciones:Longitud mayor que la longitud de la cadena
	 * Entrada:1 String con la cadena que quermos completar
	 * 			1 numero que indique la longitud de la cadena
	 * Salida:1 cadena con **
	 * Entrada/Salida:Nada
	 * Postcondiciones:Cadena asociada al nombre
	 */
	public static String completaCadena(String cadena,int longitud){
		String salida=cadena;
		for(int i=salida.length();i<longitud;i++){
			salida=salida+"*";
		}
		return salida;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static String quitaAsterisco(String cadena)
	 * Proceso:Quita todos los asteriscos del final de nua cadena
	 * Precondiciones:Ninguna
	 * Entrada:Una cadena con asteriscos
	 * Salida:Una cadena sin asteriscos
	 * Entrada/Salida:nada
	 * Postcondiciones:Caadena asociada al nombre
	 */
	public static String quitaAsterisco(String cadena){
		String salida="";
		for(int i=0;i<cadena.length();i++){
			if(cadena.charAt(i)!='*'){
				salida=salida+cadena.charAt(i);
			}
		}
		return salida;
	}
	
}
