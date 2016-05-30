package tests;

import ficherosGes.Actualizador;

import java.io.*;

import datos.*;
import ficheros.*;
public class TestActualizadorCliente {

	public static void main(String[] args) {
		Actualizador act=new Actualizador();
		FicheroPersona fp=new FicheroPersona();
		FicheroCliente fc=new FicheroCliente();
		
		String rutaPersonas="Ficheros//Tests//Actualizador//PersonasClientes.dat";
		
		String rutaClientes="Ficheros//Tests//Actualizador//Clientes.dat";
		String rutaClientesTemp="Ficheros//Tests//Actualizador//ClientesTemp.dat";
		
		PersonaImpl p;
		ClienteImpl c;
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
			c=new ClienteImpl(p, "javi@javi.com", "Mi casa", null);
			fp.guardaPersona(rutaPersonas, p);
			fc.guardaClienteSinCompras(rutaClientes, c);
		}
		System.out.println("Mostramos toda los clientes del fichero:");
		fc.muestraClientes(rutaClientes,rutaPersonas);
		
		
		System.out.println("Eliminamos el cliente con dni 2");
		p=new PersonaImpl(2, "", "", "");
		c=new ClienteImpl(p, "javi@javi.com", "", null);
		fc.guardaClienteSinCompras(rutaClientesTemp,c);
		
		System.out.println("Modificamos al cliente con dni 4");
		p=new PersonaImpl(4, "", "", "");
		c=new ClienteImpl(p, "correoCambiado@javi.com", "Casa nueva", null);
		fc.guardaClienteSinCompras(rutaClientesTemp,c);
		
		/*System.out.println("Añadimos uan persona con dni 14");
		p=new PersonaImpl(14,"Nueva","Personita","en el mundo");
		fp.guardaPersona(rutaPersonasTemp,p);*/
		
		System.out.println("Mostramos todo lo del temporal:");
		fc.muestraClientes(rutaClientesTemp,rutaPersonas);
		
		System.out.println("Actualizamos y mostramos el maestro");
		act.actualizaClientes(rutaClientes, rutaClientesTemp);
		fc.muestraClientes(rutaClientes,rutaPersonas);
	}

}
