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

import datos.InstrumentoImpl;

public interface Cliente {
	public String getCorreoe();
	public String getDireccion();
	public double getFacturado();
	public Vector<InstrumentoImpl> getCompras();
	
	public void setCorreoe(String correoe);
	public void setDireccion(String direccion);
	public void setCompras(Vector<InstrumentoImpl> compras);
}
