/**
 * Restricciones:
 * **************
 *  El atributo afinación nos dirá la nota base del instrumento,los caracteres válidos serán:
 * 			-a,b,c,d,e,f,g
 * 			-A,B,C,D,E,F,G.
 * Si el caracter está en minúscula,consideraremos que es la nota con un bemol
 * 
 * El material no podrá sobrepasar los 20 caracteres, de ser así solo se guardarán los
 * 20 primeros
 * 
 * Métodos heredados/añadidos;
 * boolean afinacionValida(char afinacion);
 * String toString();
 */

package datos;

import enums.Marca;
import interfaces.Percusion;

public class PercusionImpl extends InstrumentoImpl implements Percusion {

	/**
	 * Lo mismo de siempre, you know
	 */
	private static final long serialVersionUID = 1L;

	private char afinacion;
	private String material;
	private boolean accesorio;
	
	public PercusionImpl() {
		super();
		this.afinacion='C';
		this.material="Defecto";
		this.accesorio=false;
	}

	public PercusionImpl(String nombre, Marca marca, String descripcion, String modelo,
			double precio,char afinacion,String material,boolean accesorio) {
		super(nombre, marca, descripcion, modelo, precio);
		if (!afinacionValida(afinacion)) {
			System.out.println("Error, la afinación no es válida");
			System.out.println("Se guardará C");
			afinacion = 'C';
		}
		this.afinacion = afinacion;
		if(material.length()>20){
			System.out.println("Error, el material es demasiado grande, se guardará: ");
			material=material.substring(0, 20);
			System.out.println(material);
		}
		this.material=material;
		this.accesorio=accesorio;
		
	}

	public PercusionImpl(int id, String nombre, Marca marca, String descripcion, String modelo, 
			double precio,char afinacion,String material,boolean accesorio) {
		super(id, nombre, marca, descripcion, modelo, precio);
		if (!afinacionValida(afinacion)) {
			System.out.println("Error, la afinación no es válida");
			System.out.println("Se guardará C");
			afinacion = 'C';
		}
		this.afinacion = afinacion;
		if(material.length()>20){
			System.out.println("Error, el material es demasiado grande, se guardará: ");
			material=material.substring(0, 20);
			System.out.println(material);
		}
		this.material=material;
		this.accesorio=accesorio;
	}
	
	public PercusionImpl(InstrumentoImpl instrumento,char afinacion,String material,boolean accesorio){
		super(instrumento);
		if (!afinacionValida(afinacion)) {
			System.out.println("Error, la afinación no es válida");
			System.out.println("Se guardará C");
			afinacion = 'C';
		}
		this.afinacion = afinacion;
		if(material.length()>20){
			System.out.println("Error, el material es demasiado grande, se guardará: ");
			material=material.substring(0, 20);
			System.out.println(material);
		}
		this.material=material;
		this.accesorio=accesorio;
	}
	
	//Copia
	public PercusionImpl(PercusionImpl percu){
		super(percu.getId(), percu.getNombre(), percu.getMarca(), 
				percu.getDescripcion(), percu.getModelo(), percu.getPrecioVenta());
		this.afinacion=percu.getAfinacion();
		this.material=percu.getMaterial();
		this.accesorio=percu.getAccesorios();
	}

	public char getAfinacion() {
		return afinacion;
	}

	public String getMaterial() {
		return material;
	}

	public boolean getAccesorios() {
		return accesorio;
	}

	public void setAfinacion(char afinacion) {
		if (!afinacionValida(afinacion)) {
			System.out.println("Error, la afinación no es válida");
			System.out.println("Se guardará C");
			afinacion = 'C';
		}
		this.afinacion = afinacion;
	}

	public void setMaterial(String material) {
		if(material.length()>20){
			System.out.println("Error, el material es demasiado grande, se guardará: ");
			material=material.substring(0, 20);
			System.out.println(material);
		}
		this.material=material;
	}

	public void setAccesorios(boolean accesorios) {
		this.accesorio=accesorios;
	}
	
	public boolean afinacionValida(char afinacion) {
		boolean resultado = true;
		if ((int) afinacion < 65 || (int) afinacion > 71 && (int) afinacion < 97 || (int) afinacion > 103) {
			resultado = false;
		}
		return resultado;
	}
	
	public String toString(){
		String salida=super.toString();
		salida=salida+"\n"+afinacion+" "+material+" "+accesorio;
		return salida;
	}
}
