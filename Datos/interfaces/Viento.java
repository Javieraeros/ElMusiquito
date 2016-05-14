/* ***********
 * Propiedades
 * ***********
 * Basicas:
 * Afinación char, consultable y modificable
 * Registro: String consultable y modificable
 * Boquilla: byte, consultable y modificable
 */


package interfaces;

public interface Viento {
	public char getAfinacion();
	public String getTesitura();
	public byte getBoquilla();
	
	public void setAfinacion(char afinacion);
	public void setTesitura(String tesitura);
	public void setBoquilla(byte boquilla);
}
