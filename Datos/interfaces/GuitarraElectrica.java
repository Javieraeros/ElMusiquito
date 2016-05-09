/* ***********
 * Propiedades
 * ***********
 * Tipo: String consultable y modificable.
 * Pastillas: Pastilla[] consultable y modificable.
 * PuenteFlotante: boolean consultable y modificable.
 * Controles: int consultable y modificable. 
 */

package interfaces;

import datos.Pastilla;

public interface GuitarraElectrica {
	public String getTipo();
	public Pastilla[] getPastillas();
	public boolean getPuenteFlotante();
	public int getControles();
	
	public void setTipo(String tipo);
	public void setPastillas(Pastilla[] pastillas);
	public void setPuenteFlotante(boolean puenteFlotante);
	public void setControles(int controles);
	
}
