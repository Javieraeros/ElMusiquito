/* Restricciones:
 * Tanto el nombre como los apellidos tendrán de tamaño máximo 20 caracteres
 * El dni no podrá ser 0 o negativo
 * 
 * Métodos Añadidios/Heredados:
 * 
 * String toString();
 * boolean equals();
 */

package datos;
import java.io.Serializable;
import compartidas.*;
import interfaces.Persona;

public class PersonaImpl implements Persona,Serializable {
	
	private static final long serialVersionUID = 1L;
	private long dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	
	public PersonaImpl(){
		dni=1;
		nombre="Desconocido";
		apellido1="Desconocido";
		apellido2="Desconocido";
	}
	/*
	 * 
	 */
	public PersonaImpl(long dni,String nombre,String apellido1,String apellido2){
		this();
		if(dni>0){
			this.dni=dni;
		}else{
			System.out.println("Error, el DNI no puede ser menor que 0");
			System.out.println("Se usará 1");
		}
		nombre=UtilidadesCompartidas.ajustaLongitud(nombre, 20);
		this.nombre=nombre;
		
		apellido1=UtilidadesCompartidas.ajustaLongitud(apellido1, 20);
		this.apellido1=apellido1;
		
		apellido2=UtilidadesCompartidas.ajustaLongitud(apellido2, 20);
		this.apellido2=apellido2;
		
	}
	
	public long getDNI() {
		return this.dni;
	}

	
	public String getNombre() {
		return this.nombre;
	}

	
	public String getApellido1() {
		return this.apellido1;
	}

	
	public String getApellido2() {
		return this.apellido2;
	}

	
	public void setDNI(long dni)throws IllegalArgumentException {
		if(dni>0){
			this.dni=dni;
		}else{
			throw new IllegalArgumentException("Error, el dni no puede ser menor que 0,se guardará 1");
		}

	}

	public void setNombre(String nombre) throws IllegalArgumentException {
		if (nombre.length() < 20) {
			this.nombre = nombre;
		} else {
			throw new IllegalArgumentException("Error, el nombre no puede ser mayor que 20");
		}

	}

	public void setApellido1(String apellido) throws IllegalArgumentException {
		if (apellido.length() < 20) {
			this.apellido1 = apellido;
		} else {
			throw new IllegalArgumentException("Error, el apellido no puede ser mayor que 20");
		}
	}

	public void setApellido2(String apellido) throws IllegalArgumentException {
		if (apellido.length() < 20) {
			this.apellido2 = apellido;
		} else {
			throw new IllegalArgumentException("Error, el apellido no puede ser mayor que 20");
		}
	}
	
	public String toString(){
		return (dni+" "+nombre+" "+apellido1+" "+apellido2);
	}
	
	/* 
	 * Interfaz 
	 * Cabecera: boolean equals(Object o)
	 * Proceso: M�todo que devuelve si un objeto es IGUAL a otro
	 * Precondiciones:Ninguna
	 * Entrada:1 objeto
	 * Salida: 1 booleano
	 * Entrada/Salida:Nada
	 * Postcondiciones:Booleano asociado al nombre, true si los objetos son iguales, false en caso contrario
	 * 					Consideraremos que dos personas son iguales si tienen el mismo dni
	 */
	@Override
	public boolean equals(Object o){
		boolean resultado=false;
		if(o!=null && o instanceof PersonaImpl){
			PersonaImpl pi=(PersonaImpl) o;
			resultado=this.dni==pi.getDNI();
		}
		return resultado;
	}
}
