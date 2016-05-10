/* ***********
 * Propiedades
 * ***********
 * Basicas:
 * correoe: String, consultable y modificable
 * direccion:String, consultable y modificable
 * compras: vector<instrumentos>,consultable y modificable 
 * 
 * Derivadas:
*  facturado: double,consultable
 */

package interfaces;

import java.util.Vector;

public interface Cliente {
	public String getCorreoe();
	public String getDireccion();
	public double getFacturado();
	public Vector<Instrumentos> getCompras();
	
	public void setCorreoe(String correoe);
	public void setDireccion(String direccion);
	public void setCompras(Vector<Instrumentos> compras);
}
