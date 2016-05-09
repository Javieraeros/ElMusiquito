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
	public String getRegistro();
	public byte getBoquilla();
	
	public void setAfiancion(char afiancion);
	public void setRegistro(String registro);
	public void setBoquilla(byte boquilla);
}
