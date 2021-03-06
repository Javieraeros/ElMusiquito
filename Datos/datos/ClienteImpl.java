/*
 * Restricciones:
 * El correoe será del tipo:
 * 			******@******.***   (máximo 30 caracteres)
 * Dirección máximo 20 caracteres
 * Métodos Añadidos/Heredados:
 * 
 * boolean correoeValido(String correoe);
 * String toString();
 */

package datos;

import java.util.Vector;

import compartidas.UtilidadesCompartidas;
import interfaces.Cliente;

public class ClienteImpl extends PersonaImpl implements Cliente {

	private static final long serialVersionUID = 1L;
	private String correoe;
	private String direccion;
	private Vector<InstrumentoImpl> compras;

	public ClienteImpl() {
		super();
		this.correoe = "defecto@defecto.es";
		this.direccion = "defecto";
		this.compras = new Vector<InstrumentoImpl>(10, 1);
	}

	public ClienteImpl(long dni, String nombre, String apellido1, String apellido2, String correoe, String direccion,
			Vector<InstrumentoImpl> compras) {
		super(dni, nombre, apellido1, apellido2);
		if (correoeValido(correoe)) {
			this.correoe = correoe;
		} else {
			this.correoe = "defecto@defecto.es";
			System.out.println("Correo no válido, se usará el por defecto");
		}
		direccion = UtilidadesCompartidas.ajustaLongitud(direccion, 20);
		this.direccion = direccion;

		this.compras = compras;

	}

	public ClienteImpl(PersonaImpl p ,String correoe, String direccion,
			Vector<InstrumentoImpl> compras) {
		super(p);
		if (correoeValido(correoe)) {
			this.correoe = correoe;
		} else {
			this.correoe = "defecto@defecto.es";
			System.out.println("Correo no válido, se usará el por defecto");
		}
		direccion = UtilidadesCompartidas.ajustaLongitud(direccion, 20);
		this.direccion = direccion;

		this.compras = compras;

	}

	public String getCorreoe() {

		return correoe;
	}

	@Override
	public String getDireccion() {

		return direccion;
	}

	/*
	 * Lo correcto sería crear los métodos para que la clase controle al objeto
	 * que contiene (getNombreInstrumento,getIdInstrumento...) Si esto no es
	 * borrado, es por falta de tiempo para crear los métodos
	 */
	@Override
	public double getFacturado() {
		double resultado = 0;
		for (int i = 0; i < compras.size(); i++) {
			resultado = resultado + compras.elementAt(i).getPrecioVenta();
		}
		return resultado;
	}

	@Override
	public Vector<InstrumentoImpl> getCompras() {

		return compras;
	}

	@Override
	public void setCorreoe(String correoe) throws IllegalArgumentException {
		if (correoeValido(correoe)) {
			this.correoe = correoe;
		} else {
			throw new IllegalArgumentException("Error, el correo no es válido");
		}

	}

	@Override
	public void setDireccion(String direccion) {
		direccion = UtilidadesCompartidas.ajustaLongitud(direccion, 20);
	}

	@Override
	public void setCompras(Vector<InstrumentoImpl> compras) {
		this.compras = compras;

	}

	/*
	 * Interfaz Cabecera:boolean correoeValido(String correoe) Proceso:Método
	 * que comprueba que un correo sea valido: *@*.* longitud máxima=30
	 * Precondiciones:Ninguna Entrada:Una cadena con el correoe Salida:Un
	 * booleano indicando la validez del correoe Entrada/Salida:Nada
	 * Postcondiciones:Booleano asociado al nombre. lógica positiva.
	 */
	public boolean correoeValido(String correoe) {
		boolean valido = false;
		if (correoe.length() <= 30) {
			char[] aux = correoe.toCharArray();
			for (int i = 0; i < aux.length && !valido; i++) {
				// Si hay al menos una letra (i>0) y un @
				if (i > 0 && aux[i] == '@') {
					for (int j = i; j < aux.length; j++) {
						if (j > i + 1 && aux[j] == '.') {
							valido = true;
						}
					}
				}
			}
		}
		return valido;
	}

	@Override
	public String toString() {

		return (super.toString() + "\nCorreo-e: " + correoe + "\nDireccion: " + direccion);
	}
}
