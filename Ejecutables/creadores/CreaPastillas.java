package creadores;

import java.io.*;
import java.util.Scanner;

import datos.*;
import enums.*;
import ficheros.*;

public class CreaPastillas {

	public static void main(String[] args) {
		String ruta = "Ficheros//Tests//PruebaOrdenarPastillas.dat";
		File fichero = new File(ruta);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FicheroPastillas fp = new FicheroPastillas();
		Scanner teclado = new Scanner(System.in);

		Pastilla p = null;
		boolean puenteFlotante;
		char continuar;
		String marca="";
		String modelo="";
		int bobinas;
		do {
			System.out.println("Desea introducir una pastilla?(s/n)");
			continuar = Character.toLowerCase(teclado.nextLine().charAt(0));
		} while (continuar != 's' && continuar != 'n');
		while (continuar == 's') {
			// Crear pastilla
			
			do {
				System.out.println("Introduce la marca(menos de 20 caracteres)");
				marca=teclado.nextLine();
			} while (marca.length() > 20);

			do {
				System.out.println("Introduce el modelo(menos de 20 caracteres)");
				modelo=teclado.nextLine();
			} while (modelo.length() > 20);
			
			do {
				System.out.println("introduce el número de bobinas(1,2,3)");
				bobinas = Integer.parseInt(teclado.nextLine());
			} while (bobinas < 0 || bobinas > 4);
			

			
			p=new Pastilla(marca, modelo, bobinas);
			// Guardar Pastilla
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
				fp.guardaPastilla(ruta, p);
			}
			do {
				System.out.println("Desea introducir otra pastilla?(s/n)");
				continuar = Character.toLowerCase(teclado.nextLine().charAt(0));
			} while (continuar != 's' && continuar != 'n');
		}

	}

}
