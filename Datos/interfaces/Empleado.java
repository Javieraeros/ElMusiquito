/* ***********
 * Propiedades
 * ***********
 * Basicas:
 * cuentaCorriente: String consultable y modificable
 * sueldo: double, consultable y modificable
 * Tienda: short, consultable y modificable
 * 
 * Debido a que la tienda trabajará en un ámbito europeo, el código de la cuenta corriente
 * estará constituido por dos letras mayúsculas y 20 dígitos.
 * 
 * El sueldo jamás deberá ser negativo. Somos una tienda honrada.
 * 
 */

package interfaces;

public interface Empleado {
	public String getCuentaCorriente();
	public double getSueldo();
	public short getTienda();
	
	public void setCuentaCorriente(String cuenta) throws IllegalArgumentException;
	public void setSueldo(double sueldo) throws IllegalArgumentException;
	public void setTienda(short tienda);
}
