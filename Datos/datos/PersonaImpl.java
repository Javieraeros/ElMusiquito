package datos;
/* Restricciones:
 * Tanto el nombre como los apellidos tendrán de tamaño máximo 20 caracteres
 * El dni no podrá ser 0 o negativo
 * 
 */
import interfaces.Persona;

public class PersonaImpl implements Persona {
	private long dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	
	public PersonaImpl(){
		dni=00000001;
		nombre="Desconocido";
		apellido1="Desconocido";
		apellido2="Desconocido";
	}
	/*
	 * 
	 */
	public PersonaImpl(long dni,String nombre,String apellido1,String apellido2){
		this();
		if(nombre.length()<=20 &&
		   apellido1.length()<=20 &&
		   apellido2.length()<=20 &&
		   dni>0){
			this.dni=dni;
			this.nombre=nombre;
			this.apellido1=apellido1;
			this.apellido2=apellido2;
		}else{
			if(dni<0){
				System.out.println("Error, el DNI no puede ser menor que 0");
			}
			if(nombre.length()>20){
				System.out.println("Error, el nombre no puede contener más de 20 caracteres");
			}
			if(apellido1.length()>20){
				System.out.println("Error, el primer apellido no puede contener más de 20 caracteres");
			}
			if(apellido2.length()>20){
				System.out.println("Error, el segundo apellido no puede contener más de 20 caracteres");
			}
		}
		
		
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
			throw new IllegalArgumentException("Error, el dni no puede ser menor que 0");
		}

	}

	public void setNombre(String nombre) throws IllegalArgumentException {
		if (nombre.length() > 20) {
			this.nombre = nombre;
		} else {
			throw new IllegalArgumentException("Error, el nombre no puede ser mayor que 20");
		}

	}

	public void setApellido1(String apellido) throws IllegalArgumentException {
		if (apellido.length() > 20) {
			this.apellido1 = apellido;
		} else {
			throw new IllegalArgumentException("Error, el apellido no puede ser mayor que 20");
		}
	}

	public void setApellido2(String apellido) throws IllegalArgumentException {
		if (apellido.length() > 20) {
			this.apellido2 = apellido;
		} else {
			throw new IllegalArgumentException("Error, el apellido no puede ser mayor que 20");
		}
	}

}
