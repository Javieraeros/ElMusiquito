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
}
