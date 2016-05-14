/**
 * Restricciones:
 * *************
 * El atributo afinación nos dirá la nota base del instrumento,los caracteres válidos serán:
 * 			-a,b,c,d,e,f,g
 * 			-A,B,C,D,E,F,G.
 * Si el caracter está en minúscula,consideraremos que es la nota con un bemos
 * 
 * La tesitura nos dirá cuales son las notas "habituales" del instrumento
 * 
 * Boquilla solo podrá obteher 0 ó 1 (0 para viento-madera, 1 para viento-metal)
 * 
 * Métodos heredados/añadidos:
 * ***************************
 * boolean afinacionValida(char afinacion);
 * String toString();
 */

package datos;

import enums.Marca;
import interfaces.Viento;

public class VientoImpl extends InstrumentoImpl implements Viento {

	/**
	 * No la usaremos, pero eclipse nos la exige :(
	 */
	private static final long serialVersionUID = 1L;

	private char afinacion;
	private String tesitura;
	private byte boquilla;

	public VientoImpl() {
		super();
		this.afinacion = 'C';
		this.tesitura = "C3-C5";
		this.boquilla = 0;
	}

	public VientoImpl(String nombre, Marca marca, String descripcion, String modelo, double precio, char afinacion,
			String tesitura, byte boquilla) {
		super(nombre, marca, descripcion, modelo, precio);
		if (!afinacionValida(afinacion)) {
			System.out.println("Error, la afinación no es válida");
			System.out.println("Se guardará C");
			afinacion = 'C';
		}
		this.afinacion = afinacion;
		this.tesitura = tesitura;
		if (boquilla != 1 && boquilla != 0) {
			System.out.println("Error, la boquilla debe ser 0 ó 1.\nSe guardará 0");
			boquilla = 0;
		}
		this.boquilla = boquilla;

	}

	public VientoImpl(int id,String nombre, Marca marca, String descripcion, String modelo, double precio, char afinacion,
			String tesitura, byte boquilla) {
		super(id,nombre, marca, descripcion, modelo, precio);
		if (!afinacionValida(afinacion)) {
			System.out.println("Error, la afinación no es válida");
			System.out.println("Se guardará C");
			afinacion = 'C';
		}
		this.afinacion = afinacion;
		this.tesitura = tesitura;
		if (boquilla != 1 && boquilla != 0) {
			System.out.println("Error, la boquilla debe ser 0 ó 1.\nSe guardará 0");
			boquilla = 0;
		}
		this.boquilla = boquilla;

	}

	public VientoImpl(InstrumentoImpl instrumento, char afinacion, String tesitura, byte boquilla) {
		this();
		System.out.println("En desarrollo");
	}

	@Override
	public char getAfinacion() {
		return afinacion;
	}

	@Override
	public String getTesitura() {
		return tesitura;
	}

	@Override
	public byte getBoquilla() {
		return boquilla;
	}

	@Override
	public void setAfiancion(char afinacion) {
		if (!afinacionValida(afinacion)) {
			System.out.println("Error, la afinación no es válida");
			System.out.println("Se guardará C");
			afinacion = 'C';
		}
		this.afinacion = afinacion;
	}

	@Override
	public void setTesitura(String tesitura) {
		this.tesitura = tesitura;
	}

	@Override
	public void setBoquilla(byte boquilla) {
		if (boquilla != 1 && boquilla != 0) {
			System.out.println("Error, la boquilla debe ser 0 ó 1.\nSe guardará 0");
			boquilla = 0;
		}
		this.boquilla = boquilla;
	}

	public boolean afinacionValida(char afinacion) {
		boolean resultado = true;
		if ((int) afinacion < 65 || (int) afinacion > 71 && (int) afinacion < 97 || (int) afinacion > 103) {
			resultado = false;
		}
		return resultado;
	}

	public String toString() {
		String salida = super.toString();
		salida = salida + " " + afinacion + " " + tesitura + " " + " " + boquilla;
		return salida;
	}
}
