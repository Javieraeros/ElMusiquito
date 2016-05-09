 /* ***********
 * Propiedades
 * ***********
 * Basicas:
 * DNI: long consultable y modificable
 * Nombre: string consultable y modificable
 * Apellido1: string consultable y modificable
 * Apellido2: string, consultable y modificable
 */
 

package interfaces;

public interface Persona {
	public long getDNI();
	public String getNombre();
	public String getApellido1();
	public String getApellido2();
	public void setDNI(long dni);
	public void setNombre(String nombre);
	public void setApellido1(String apellido);
	public void setApellido2(String apellido);
}
