/* ***********
 * Propiedades
 * ***********
 * Basicas:
 * Afinacion:char, consultable y modificable
 * Material:String, consultable y modificable
 * Accesorios:boolean,consultable y modificable
 */

package interfaces;

public interface Percusion {
	public char getAfinacion();
	public String getMaterial();
	public boolean getAccesorios();
	
	public void setAfinacion(char afinacion);
	public void setMaterial(String material);
	public void setAccesorios(boolean accesorios);
}
