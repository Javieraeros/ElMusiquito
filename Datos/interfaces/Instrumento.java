/* ***********
 * Propiedades
 * ***********
 * Basicas:
 * Id: int consultable.
 * Nombre:String consultable y modificable.
 * Marca:String consultable y modificable.
 * Modelo:String consultable y modificable
 * PrecioVenta:Double consultable y modificable.
 * Descripción:String, consultable y modificable.
 * 
 */

package interfaces;

import enums.Marca;

public interface Instrumento {
	public int getId();
	public String getNombre();
	public Marca getMarca();
	public String getModelo();
	public double getPrecioVenta();
	public String getDescripcion();
	
	public void setNombre(String nombre);
	public void setMarca(Marca marca);
	public void setModelo(String modelo);
	public void setPrecioVenta(double precioVenta);
	public void setDescripcion(String descripcion);
}
