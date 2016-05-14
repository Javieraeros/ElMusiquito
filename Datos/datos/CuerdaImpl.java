/* Restricciones:
 * **************
 * Cuerdas mayor que 0
 * Registro con menos de 20 caracteres
 * Tipo de cuerda tomará los valores 0 ó 1. 0 para Cuerda frotada y 1 para cuerda percutida.
 * 
 * Métodos Añadidos/Heredados:
 * String toString();
 * 
 * 
 */

package datos;

import enums.Marca;
import interfaces.Cuerda;

public class CuerdaImpl extends InstrumentoImpl implements Cuerda {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cuerdas;
	private String registro;
	private byte tipoCuerda;

	public CuerdaImpl() {
		super();
		this.cuerdas=6;
		this.registro="C3-C5";
		this.tipoCuerda=(byte) 0;
	}

	public CuerdaImpl(String nombre, Marca marca, String descripcion, String modelo, double precio,
			int cuerdas,String registro,byte tipoCuerda) {
		super(nombre, marca, descripcion, modelo, precio);
		if(cuerdas<1){
			System.out.println("Error, el número de cuerdas no puede ser menor o igual que 0");
			System.out.println("Se guardará 1 cuerda");
			cuerdas=1;
		}
		this.cuerdas=cuerdas;
		if(registro.length()>20){
			System.out.println("Error, el registro no puede contener más de 20 caracteres");
			registro=registro.substring(0,20);
			System.out.println("Se guardará: "+registro);
		}
		this.registro=registro;
		if(tipoCuerda!=0 && tipoCuerda!=1){
			System.out.println("Error, el tipo de cuerda tiene que ser 0 ó 1. Se guardará 1");
			tipoCuerda=1;
		}
		this.tipoCuerda=tipoCuerda;
	}

	public CuerdaImpl(int id, String nombre, Marca marca, String descripcion, String modelo, 
			double precio,int cuerdas,String registro,byte tipoCuerda) {
		super(id, nombre, marca, descripcion, modelo, precio);
		if(cuerdas<1){
			System.out.println("Error, el número de cuerdas no puede ser menor o igual que 0");
			System.out.println("Se guardará 1 cuerda");
			cuerdas=1;
		}
		this.cuerdas=cuerdas;
		if(registro.length()>20){
			System.out.println("Error, el registro no puede contener más de 20 caracteres");
			registro=registro.substring(0,20);
			System.out.println("Se guardará: "+registro);
		}
		this.registro=registro;
		if(tipoCuerda!=0 && tipoCuerda!=1){
			System.out.println("Error, el tipo de cuerda tiene que ser 0 ó 1. Se guardará 1");
			tipoCuerda=1;
		}
		this.tipoCuerda=tipoCuerda;
	}
	
	
	public CuerdaImpl(InstrumentoImpl instrumento,int cuerdas, String registro, byte tipoCuerda) {
		super(instrumento);
		if(cuerdas<1){
			System.out.println("Error, el número de cuerdas no puede ser menor o igual que 0");
			System.out.println("Se guardará 1 cuerda");
			cuerdas=1;
		}
		this.cuerdas=cuerdas;
		if(registro.length()>20){
			System.out.println("Error, el registro no puede contener más de 20 caracteres");
			registro=registro.substring(0,20);
			System.out.println("Se guardará: "+registro);
		}
		this.registro=registro;
		if(tipoCuerda!=0 && tipoCuerda!=1){
			System.out.println("Error, el tipo de cuerda tiene que ser 0 ó 1. Se guardará 1");
			tipoCuerda=1;
		}
		this.tipoCuerda=tipoCuerda;
	}

	
	//Copia, no generará id.
	public CuerdaImpl(CuerdaImpl cuerda){
		super(cuerda.getId(), cuerda.getNombre(), cuerda.getMarca(), 
				cuerda.getDescripcion(), cuerda.getModelo(), cuerda.getPrecioVenta());
		this.cuerdas=cuerda.getCuerdas();
		this.registro=cuerda.getRegistro();
		this.tipoCuerda=cuerda.getTipoCuerda();
	}
	
	
	@Override
	public int getCuerdas() {
		return cuerdas;
	}

	@Override
	public String getRegistro() {
		return registro;
	}

	@Override
	public byte getTipoCuerda() {
		return tipoCuerda;
	}

	@Override
	public void setCuerdas(int cuerdas) {
		if(cuerdas<1){
			System.out.println("Error, el número de cuerdas no puede ser menor o igual que 0");
			System.out.println("Se guardará 1 cuerda");
			cuerdas=1;
		}
		this.cuerdas=cuerdas;
	}

	@Override
	public void setRegistro(String registro) {
		if(registro.length()>20){
			System.out.println("Error, el registro no puede contener más de 20 caracteres");
			registro=registro.substring(0,20);
			System.out.println("Se guardará: "+registro);
		}
		this.registro=registro;
	}

	@Override
	public void setTipoCuerda(byte tipoCuerda) {
		if(tipoCuerda!=0 && tipoCuerda!=1){
			System.out.println("Error, el tipo de cuerda tiene que ser 0 ó 1. Se guardará 1");
			tipoCuerda=1;
		}
		this.tipoCuerda=tipoCuerda;
	}
	
	@Override
	public String toString(){
		String salida=super.toString();
		salida=salida+"\n"+cuerdas+" "+registro+" "+tipoCuerda;
		return salida;
	}
}
