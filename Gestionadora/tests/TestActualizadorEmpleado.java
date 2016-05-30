package tests;

import ficherosGes.Actualizador;

import java.io.*;

import datos.*;
import ficheros.*;
public class TestActualizadorEmpleado {

	public static void main(String[] args) {
		Actualizador act=new Actualizador();
		FicheroPersona fp=new FicheroPersona();
		FicheroEmpleado fe=new FicheroEmpleado();
		
		String rutaPersonas="Ficheros//Tests//Actualizador//PersonasEmpleados.dat";
		
		
		String rutaEmpleados="Ficheros//Tests//Actualizador//Empleados.dat";
		String rutaEmpleadosTemp="Ficheros//Tests//Actualizador//EmpleadosTemp.dat";
		
		PersonaImpl p;
		EmpleadoImpl emp;
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		//Creamos el fichero MAestro
		try {
			fos = new FileOutputStream(rutaPersonas);
			oos = new ObjectOutputStream(fos);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		
		
		for(long i=1;i<11;i++){
			p=new PersonaImpl(i,"Javi","Ruiz","Rodriguez");
			emp=new EmpleadoImpl(p, "ES123456789012345678", 50,(short) 2);
			fp.guardaPersona(rutaPersonas, p);
			fe.guardaEmpleado(rutaEmpleados,emp);
		}
		System.out.println("Mostramos toda los empleados del fichero:");
		fe.muestraEmpleados(rutaEmpleados);
		
		
		System.out.println("Eliminamos el empleado con dni 2");
		p=new PersonaImpl(2, "", "", "");
		emp=new EmpleadoImpl(p, "ES123456789012345678", 50,(short) 0);
		fe.guardaEmpleado(rutaEmpleadosTemp, emp);
		
		System.out.println("Modificamos al empleado con dni 4");
		p=new PersonaImpl(4, "", "", "");
		emp=new EmpleadoImpl(p, "ES123456789059345678", 5000,(short) 2);
		fe.guardaEmpleado(rutaEmpleadosTemp, emp);
		
		System.out.println("Añadimos un empleado con dni 14");
		p=new PersonaImpl(14, "", "", "");
		emp=new EmpleadoImpl(p, "ES123456789012345678", 50,(short) 2);
		fe.guardaEmpleado(rutaEmpleadosTemp, emp);
		
		System.out.println("Mostramos todo lo del temporal:");
		fe.muestraEmpleados(rutaEmpleadosTemp);
		
		System.out.println("Actualizamos y mostramos el maestro");
		act.actualizaEmpleados(rutaEmpleados, rutaEmpleadosTemp);
		fe.muestraEmpleados(rutaEmpleados);
	}

}
