/* ***********
 * Propiedades
 * ***********
 * Basicas:
 * cuerdas: int,consultable y modificable. Mayot que 0
 * tesitura: String consultable y modificable.
 * tipoCuerda:byte,consultable y modificable 
 */

package interfaces;

public interface Cuerda {
	public int getCuerdas();
	public String getTesitura();
	public byte getTipoCuerda();
	
	public void setCuerdas(int cuerdas);
	public void setTesitura(String tesitura);
	public void setTipoCuerda(byte tipoCuerda);
}
