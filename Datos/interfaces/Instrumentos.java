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
 * Compartidas:
 * IdCompartido: int, consultable.
 */

package interfaces;

import enums.Marca;

public interface Instrumentos {
	public int getId();
	public int getIdComapartido();
	public String getNombre();
	public Marca getMarca();
	public String getModelo();
	public Double getPrecioVenta();
	public String getDescripcion();
	
	public void setNombre(String nombre);
	public void setMarca(Marca marca);
	public void setModelo(String modelo);
	public void setPrecioVenta(String precioVenta);
	public void setDescripcion(String descripcion);
}
