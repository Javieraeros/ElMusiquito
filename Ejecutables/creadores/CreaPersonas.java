/*
 * El siguiente programa principal crea un fichero con personas, o bien si el fichero
 * ya existe, guarda las persoans que queramos introducir
 */

package creadores;

import java.io.*;
import java.util.Scanner;

import compartidas.MiOOS;
import datos.PersonaImpl;
import ficheros.*;

public class CreaPersonas {

	public static void main(String[] args) {
		String ruta = "Ficheros//Personas//Personas.dat";
		File fichero = new File(ruta);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FicheroPersona fp = new FicheroPersona();
		Scanner teclado = new Scanner(System.in);

		PersonaImpl p = null;
		long dni;
		String nombre, apellido1, apellido2;
		char continuar;
		do {
			System.out.println("Desea introducir una persona?(s/n)");
			continuar = Character.toLowerCase(teclado.nextLine().charAt(0));
		} while (continuar != 's' && continuar != 'n');
		while (continuar == 's') {
			// Crear persona
			do {
				System.out.println("introduce el dni");
				dni = Long.parseLong(teclado.nextLine());
			} while (dni < 0 || dni > 99999999);
				
			do {
				System.out.println("Introduce el nombre(menos de 20 caracteres");
				nombre=teclado.nextLine();
			} while (nombre.length() > 20);

			do {
				System.out.println("Introduce el primer apellido(menos de 20 caracteres");
				apellido1=teclado.nextLine();
			} while (apellido1.length() > 20);

			do {
				System.out.println("Introduce el segundo apellido(menos de 20 caracteres");
				apellido2=teclado.nextLine();
			} while (apellido2.length() > 20);

			
			p=new PersonaImpl(dni, nombre, apellido1, apellido2);
			// Guardar Persona
			if (!fichero.exists()) {
				System.out.println("El fichero no existe");
				try {
					fos = new FileOutputStream(fichero);
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
				fp.guardaPersona(ruta, p);
			}
			do {
				System.out.println("Desea introducir una persona?(s/n)");
				continuar = Character.toLowerCase(teclado.nextLine().charAt(0));
			} while (continuar != 's' && continuar != 'n');
		}

	}

}
