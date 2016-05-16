package ficheros;

import java.io.*;

import compartidas.MiOOS;
import datos.*;

public class FicheroPastillas {


	/*
	 * Interfaz Cabecera:public void guardaPastilla(String ruta,Pastilla p)
	 * Proceso:Guarda una pastilla en el fichero dado 
	 * Precondiciones:Ninguna
	 * Entrada:Una pastilla 
	 * Salida:Nada 
	 * Entrada/Salida:El fichero con la pastilla guardada
	 * Postcondiciones:El fichero quedará escrito.
	 */

	public void guardaPastilla(String ruta, Pastilla p) {
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
	 * Cabecera:public int cuentaPastillas(String ruta) 
	 * Proceso:Cuenta el numero de pastillas que hay en un fichero
	 * Precondiciones:Ninguna 
	 * Entrada:1 cadena para el fichero 
	 * Salida:1 entero con el número de pastillas 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Entero asociado al nombre
	 */

	public int cuentaPastillas(String ruta) {
		int numeroPastillas = 0;
		Pastilla p;
		FileInputStream fos = null;
		ObjectInputStream ois = null;

		try {
			fos = new FileInputStream(ruta);
			ois = new ObjectInputStream(fos);
			p = (Pastilla) ois.readObject();
			while (p != null) {
				numeroPastillas++;
				p = (Pastilla) ois.readObject();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		}
		return numeroPastillas;
	}

	/*
	 * Interfaz 
	 * Cabecera:public void muestraPastillas(String ruta)
	 * Proceso:Muestra todas las pastillas de un fichero dado
	 * Precondiciones:Fichero con pastillas 
	 * Entrada:1 cadena con la ruta del fichero 
	 * Salida:Nada, pinta en pantalla 
	 * Entrada/Salida:Nada
	 * Postcondiciones:Pintará en pantalla todas las pastillas
	 */

	public void muestraPastillas(String ruta) {
		Pastilla p;
		FileInputStream fos = null;
		ObjectInputStream ois = null;
		int contador, numeroPastillas = cuentaPastillas(ruta);

		try {
			fos = new FileInputStream(ruta);
			ois = new ObjectInputStream(fos);
			for (contador = 0; contador < numeroPastillas; contador++) {
				p = (Pastilla) ois.readObject();
				System.out.println(p);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/*
	 * Interfaz 
	 * Cabecera:public PastillaImpl devuelvePastilla(String ruta,int id) 
	 * Proceso:Devuelve una pastilla con dicho id
	 * Precondiciones:Ninguna
	 * Entrada:1 cadena para la ruta del fichero 
	 * 			1 entero para el id
	 * Salida:1 pastilla 
	 * Entrada/Salida:Nada 
	 * Postcondiciones:Pastilla asociada al nombre. Null si dicho id no se encuentra en el dicero.
	 */

	public Pastilla devuelvePersona(String ruta, int id) {
		Pastilla aux = null;
		Pastilla p=null;
		FileInputStream fos = null;
		ObjectInputStream ois = null;
		int contador = 0, numeroPersonas = cuentaPastillas(ruta);
		boolean encontrado = false;

		try {
			fos = new FileInputStream(ruta);
			ois = new ObjectInputStream(fos);
			while (contador < numeroPersonas && !encontrado) {
				aux = (Pastilla) ois.readObject();
				encontrado = aux.getId() == id; // encontrado será true si el
												// id que nos dan es igual al
												// id de la pastilla que
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
