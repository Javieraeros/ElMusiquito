/*
 * Crea un cliente, guarda su parte de persona en persona, y la parte de cliente en cliente
 */

package creadores;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

import compartidas.UtilidadesCompartidas;
import datos.*;
import ficheros.*;

public class CreaCliente {

	public static void main(String[] args) {
		String rutaCliente = "Ficheros//Clientes.dat";
		String rutaPersona="Ficheros//Personas.dat";
		File ficheroCliente=new File(rutaCliente);
		File ficheroPersona = new File(rutaPersona);
		FileOutputStream fos = null;
		ObjectOutputStream oos=null;
		DataOutputStream dos = null;
		FicheroPersona fp = new FicheroPersona();
		Scanner teclado = new Scanner(System.in);

		PersonaImpl p = null;
		long dni;
		String nombre, apellido1, apellido2,correoe,direccion;
		char continuar;
		Vector<InstrumentoImpl> v=new Vector<InstrumentoImpl>(1,0);
		do {
			System.out.println("Desea introducir un cliente?(s/n)");
			continuar = Character.toLowerCase(teclado.nextLine().charAt(0));
		} while (continuar != 's' && continuar != 'n');
		while (continuar == 's') {
			// Crear persona/cliente
			do {
				System.out.println("introduce el dni");
				dni = Long.parseLong(teclado.nextLine());
			} while (dni < 0 || dni > 99999999);
				
			do {
				System.out.println("Introduce el nombre(menos de 20 caracteres)");
				nombre=teclado.nextLine();
			} while (nombre.length() > 20);

			do {
				System.out.println("Introduce el primer apellido(menos de 20 caracteres)");
				apellido1=teclado.nextLine();
			} while (apellido1.length() > 20);

			do {
				System.out.println("Introduce el segundo apellido(menos de 20 caracteres)");
				apellido2=teclado.nextLine();
			} while (apellido2.length() > 20);

			do {
				System.out.println("Introduce el correo-e(menos de 30 caracteres)");
				correoe=teclado.nextLine();
			} while (correoe.length() > 30);
			
			do {
				System.out.println("Introduce la direccion(menos de 20 caracteres)");
				direccion=teclado.nextLine();
			} while (direccion.length() > 20);
			
			p=new PersonaImpl(dni, nombre, apellido1, apellido2);
			// Guardar Persona
			if (!ficheroPersona.exists()) {
				System.out.println("El fichero no existe");
				try {
					fos = new FileOutputStream(ficheroPersona);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(p);
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
			} else {
				fp.guardaPersona(rutaPersona, (PersonaImpl) p);
			}
			//Guardar Cliente
			p=new ClienteImpl(dni, nombre, apellido1, apellido2,correoe,direccion,v);
			try {
				fos=new FileOutputStream(ficheroCliente,true);
				dos=new DataOutputStream(fos);
				//Guardamos el dni
				dos.writeLong(p.getDNI());
				
				//Guardamos el correo
				correoe=UtilidadesCompartidas.completaCadena(correoe, 30);
				dos.writeChars(correoe);
				
				//Guardamos la direccion
				direccion=UtilidadesCompartidas.completaCadena(direccion, 20);
				dos.writeChars(direccion);
				
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
			do {
				System.out.println("Desea introducir otro cliente?(s/n)");
				continuar = Character.toLowerCase(teclado.nextLine().charAt(0));
			} while (continuar != 's' && continuar != 'n');
		}

	}

}
