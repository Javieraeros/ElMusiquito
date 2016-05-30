package ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import compartidas.UtilidadesCompartidas;
import datos.*;

public class FicheroEmpleado {

	/* 
	 * Interfaz 
	 * Cabecera:public void guardaEmpleado(String ruta,EmpleadoImpl e)
	 * Proceso:Guarda el dni,cuenta corriente, sueldo y tienda de un empleado.
	 * Precondiciones:Ninguna
	 * Entrada:1 empleado
	 * Salida:Nada
	 * Entrada/Salida:! cadena para la ruta del fichero
	 * Postcondiciones:El fichero quedará escrito
	 */
	
	public void guardaEmpleado(String ruta,EmpleadoImpl emp){
		File ficheroEmpleado=new File(ruta);
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos=new FileOutputStream(ficheroEmpleado,true);
			dos=new DataOutputStream(fos);
			//Guardamos el dni
			dos.writeLong(emp.getDNI());
			
			//Guardamos la cuenta corriente
			dos.writeChars(emp.getCuentaCorriente());
			
			//Guardamos el sueldo
			dos.writeDouble(emp.getSueldo());
			
			//Guardamos la tienda
			dos.writeShort(emp.getTienda());
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(dos!=null){
				try {
					dos.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public EmpleadoImpl devuelveEmpleado(String rutaEmpleado,String rutaPersona,long dni)
	 * Proceso:Devuelve un empleado de un fichero dado
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para el fichero de empleados
	 * 			1 cadena para el fichero de personas
	 * 			1 entero largo para el dni
	 * Salida:El empleado
	 * Entrada/Salida:Nada
	 * Postcondiciones:Empleado asociado al nombre, null sin o exsite
	 */
	
	public EmpleadoImpl devuelveEmpleado(String rutaEmpleado, String rutaPersona, long dni) {
		EmpleadoImpl emp = null;
		PersonaImpl p = null;

		File ficheroEmpleado = new File(rutaEmpleado);
		FileInputStream fis = null;
		DataInputStream dis = null;
		long dniLeido;
		String cuentaCorriente="";
		double sueldo;
		short tienda;

		FicheroPersona fp = new FicheroPersona();
		p = fp.devuelvePersona(rutaPersona, dni);

		if (p != null) {
			try {
				fis = new FileInputStream(ficheroEmpleado);
				dis = new DataInputStream(fis);
				dniLeido = dis.readLong();
				while (dis.available() > 0 && dniLeido != dni) {
					dis.skip(50);
					dniLeido = dis.readLong();
				}

				if (dniLeido == dni) {
					// Lee cuenta
					for (int i = 0; i < 20; i++) {
						cuentaCorriente = cuentaCorriente + dis.readChar();
					}
					
					// lee sueldo
					sueldo=dis.readDouble();
					
					//Lee tienda
					tienda=dis.readShort();
					emp = new EmpleadoImpl(p, cuentaCorriente,sueldo,tienda);
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			} finally{
				if(dis!=null){
					try {
						dis.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
				if (fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}

		}
		return emp;
	}
	

	/*
	 * Interfaz 
	 * Cabecera:public int cuentaEmpleados(String ruta) 
	 * Proceso:Cuenta el numero de empleados que hay en un fichero.
	 * Precondiciones:Ninguna 
	 * Entrada:1 cadena para el fichero 
	 * Salida:1 entero con el número de empleados
	 * Entrada/Salida:Nada
	 * Postcondiciones:Entero asociado al nombre
	 */

	public int cuentaEmpleados(String ruta) {
		int numeroEmpleados = 0;
		FileInputStream fis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			while (dis.available()>0) {
				dis.skipBytes(58); //Me salto el número de Bytes que ocupa un cliente 8+60+40
				numeroEmpleados++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally{
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return numeroEmpleados;
	}

	/*
	 * Interfaz 
	 * Cabecera:public void muestraEmpleados(String ruta)
	 * Proceso:Muestra el dni, la cuenta corriente, el sueldo y la tienda de un empleado
	 * Precondiciones:Fichero con empleados
	 * Entrada:1 cadena con la ruta del fichero 
	 * Salida:Nada, pinta en pantalla 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Pintará en pantalla todas los empleados
	 */

	public void muestraEmpleados(String ruta) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		int contador, numeroEmpleados = cuentaEmpleados(ruta);
		long dni;
		String cuenta;
		double sueldo;
		short tienda;
		try {
			fis = new FileInputStream(ruta);
			dis = new DataInputStream(fis);
			for (contador = 0; contador < numeroEmpleados; contador++) {
				dni=dis.readLong();
				System.out.print(dni+" ");
				//lee cuenta
				cuenta="";
				for(int i=0;i<20;i++){
					cuenta=cuenta+dis.readChar();
				}
				System.out.print(cuenta+" ");
				//lee sueldo
				sueldo=dis.readDouble();
				System.out.print(sueldo+" ");
				//lee teinda
				tienda=dis.readShort();
				System.out.print(tienda+" ");
				
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally{
			if(dis!=null){
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
}
