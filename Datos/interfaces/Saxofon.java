/* ***********
 * Propiedades
 * ***********
 * Basicas:
 * Familia:Enum FamiliaSaxo consultable y modificable
 * BoquillaSaxo:String consultable y modificable.
 * Material:String consultable y modificable
 * AcabadoString consultable y modificable
 * 
 */

package interfaces;

import enums.FamiliaSaxo;

public interface Saxofon extends Viento {
	public FamiliaSaxo getFamilia();
	public String getBoquillaSaxo();
	public String getMaterial();
	public String getAcabado();
	
	public void setFamilia(FamiliaSaxo familia);
	public void setBoquillaSaxo(String boquillaSaxo);
	public void setMaterial(String material);
	public void setAcabado(String acabado);
	
}
