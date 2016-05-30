/*
 * Clase encargada de manejar los ficheros de Persona.
 * Guardará a las personas como objetos
 */
package ficheros;

import java.io.*;

import compartidas.MiOOS;
import datos.*;

public class FicheroPersona {

	/*
	 * Interfaz Cabecera:public void guardaPersona(String ruta,PerosnaImpl p)
	 * Proceso:Guarda una persona en el fichero dado 
	 * Precondiciones:Ninguna
	 * Entrada:Una persona 
	 * Salida:Nada 
	 * Entrada/Salida:El fichero con la personaguardada 
	 * Postcondiciones:El fichero quedará escrito.
	 */

	public void guardaPersona(String ruta, PersonaImpl p) {
		File fichero = new File(ruta);
		FileOutputStream fos = null;
		MiOOS oos = null;

		try {
			fos = new FileOutputStream(fichero, true);
			oos = new MiOOS(fos);
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

	}

	/*
	 * Interfaz 
	 * Cabecera:public int cuentaPersonas(String ruta) 
	 * Proceso:Cuenta el numero de personas que hay en un fichero,independientemente de que las
	 * personas sean "validas" o no 
	 * Precondiciones:Ninguna 
	 * Entrada:1 cadena para el fichero 
	 * Salida:1 entero con el número de personas 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Entero asociado al nombre
	 */

	public int cuentaPersonas(String ruta) {
		int numeroPersonas = 0;
		PersonaImpl p;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(ruta);
			ois = new ObjectInputStream(fis);
			p = (PersonaImpl) ois.readObject();
			while (p != null) {
				numeroPersonas++;
				p = (PersonaImpl) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return numeroPersonas;
	}

	/*
	 * Interfaz 
	 * Cabecera:public void muestraPersonas(String ruta)
	 * Proceso:Muestra todas las personas de un fichero dado
	 * Precondiciones:Fichero con personas 
	 * Entrada:1 cadena con la ruta del fichero 
	 * Salida:Nada, pinta en pantalla 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Pintará en pantalla todas las personas
	 */

	public void muestraPersonas(String ruta) {
		PersonaImpl p;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		int contador, numeroPersonas = cuentaPersonas(ruta);

		try {
			fis = new FileInputStream(ruta);
			ois = new ObjectInputStream(fis);
			for (contador = 0; contador < numeroPersonas; contador++) {
				p = (PersonaImpl) ois.readObject();
				if(p.getNombre()!=""){ //En caso de que no esté borrada
					System.out.println(p);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if(ois!=null){
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	/*
	 * Interfaz 
	 * Cabecera:public PersonaImpl devuelvePersona(String ruta,longdni) 
	 * Proceso:Devuelve una persona con dicho dni 
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta del fichero 
	 * 			1 entero largo para el dni
	 * Salida:1 persona 
	 * Entrada/Salida:Nada 
	 * Postcondiciones:Persona asociada al nombre. Null si dicho dni no se encuentra en el dicero.
	 */

	public PersonaImpl devuelvePersona(String ruta, long dni) {
		PersonaImpl aux = null;
		PersonaImpl p=null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		int contador = 0, numeroPersonas = cuentaPersonas(ruta);
		boolean encontrado = false;

		try {
			fis = new FileInputStream(ruta);
			ois = new ObjectInputStream(fis);
			while (contador < numeroPersonas && !encontrado) {
				aux = (PersonaImpl) ois.readObject();
				encontrado = aux.getDNI() == dni; // encontrado será true si el
												// dni que nos dan es igual al
												// dni de la persona que
												// acabamos de leer
			}
			if(encontrado){
				p=aux;
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		}
		return p;
	}
	
}
