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
import enums.Tipo;

public interface GuitarraElectrica {
	public Tipo getTipo();
	public int[] getIdPastillas();
	public boolean getPuenteFlotante();
	public int getControles();
	
	public void setTipo(Tipo tipo);
	public void setIdPastillas(int[] pastillas);//Máximo 3 pastillas!!!
	public void setPuenteFlotante(boolean puenteFlotante);
	public void setControles(int controles);
}
