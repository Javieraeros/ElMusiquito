/*
 * Restricciones:
 * Sueldo>0
 * CuentaCorriente:tendrá dos caracteres en mayúsculas
 * 					seguida de 18 dígitos.
 * Métodos Añadidos/Heredados
 * boolean cuentaValida(String cuenta)
 * boolean equals(Object o)
 * String toString()
 */

package datos;

import interfaces.Empleado;

public class EmpleadoImpl extends PersonaImpl implements Empleado {
	/*
	 * No usaremos la serialización, pero lo pondremos para evitar
	 * advertencias de Eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	private String cuentacorriente;
	private double sueldo;
	private short tienda;
	

	public EmpleadoImpl() {
		super();
		this.cuentacorriente="ES000000000000000000";
		this.sueldo=800.00;
		this.tienda=1;
	}

	public EmpleadoImpl(long dni, String nombre, String apellido1, String apellido2,
						String cc,double sueldo,short tienda) {
		super(dni, nombre, apellido1, apellido2);
		if(cuentaCorrienteValida(cc)){
			this.cuentacorriente=cc;
		}else{
			this.cuentacorriente="ES000000000000000000";
			System.out.println("Error,número de cuenta no válido");
		}
		if(sueldo>0){
			this.sueldo=sueldo;
		}else{
			this.sueldo=1;
			System.out.println("Error, el sueldo no puede ser menor que 0");
		}
		this.tienda=tienda;
	}

	public String getCuentaCorriente() {
		return cuentacorriente;
	}

	public double getSueldo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public short getTienda() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCuentaCorriente(String cuenta) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	public void setSueldo(double sueldo) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	public void setTienda(short tienda) {
		
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public boolean cuentaCorrienteValida(String cc)
	 * Proceso:Comprueba que una cuenta corriente sea válida (principio de la clase)
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena con la cuenta
	 * Salida:1 boolean
	 * Entrada/Salida:Nada
	 * Postcondiciones:Boolean asociado al nombre.Lógica positiva.
	 */

	public boolean cuentaCorrienteValida(String cc){
		boolean valida=false;
		char[] aux=cc.toCharArray();
		if(aux.length==20){
			if((int)aux[0]>=65 && (int)aux[0]<=90 && (int)aux[1]>=65 && (int)aux[1]<=90){
				valida=true;			//Supongo que la cuenta va a ser válida
				/*
				 * Ahora lo qeu hago es comprobar si la cuenta NO ES VALIDA
				 */
				for(int i=2;i<aux.length && valida;i++){
					if((int)aux[i]<48 && (int)aux[i]>57){
						valida=false;
					}
					
				}
			}
		}
		return valida;
	}
}
