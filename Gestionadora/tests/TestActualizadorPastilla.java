package tests;

import ficherosGes.Actualizador;

import java.io.*;

import datos.*;
import ficheros.*;
public class TestActualizadorPastilla {

	public static void main(String[] args) {
		Actualizador act=new Actualizador();
		FicheroPastillas fp=new FicheroPastillas();
		
		String rutaPastillas="Ficheros//Tests//Actualizador//Pastillas.dat";
		String rutaPastillasTemp="Ficheros//Tests//Actualizador//PastillasTemp.dat";
		
		Pastilla p;
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		//Creamos el fichero MAestro
		try {
			fos = new FileOutputStream(rutaPastillas);
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
		
		//Creamos el fichero temporal
		try {
			fos = new FileOutputStream(rutaPastillasTemp);
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
		
		for(int i=1;i<11;i++){
			p=new Pastilla(i,"Generica","generica",2);
			fp.guardaPastilla(rutaPastillas, p);
		}
		System.out.println("Mostramos todas las pastillas del fichero:");
		fp.muestraPastillas(rutaPastillas);
		
		
		System.out.println("Eliminamos la pastilla con id 2");
		p=new Pastilla(2,"","borrada",2);
		fp.guardaPastilla(rutaPastillasTemp, p);
		
		System.out.println("Modificamos la pastilla con id 4");
		p=new Pastilla(4,"Modificada","modi",2);
		fp.guardaPastilla(rutaPastillasTemp, p);
		
		System.out.println("Añadimos una pastilla con id 14");
		p=new Pastilla(14,"Nueva","New",2);
		fp.guardaPastilla(rutaPastillasTemp, p);
		
		System.out.println("Mostramos todo lo del temporal:");
		fp.muestraPastillas(rutaPastillasTemp);
		
		System.out.println("Actualizamos y mostramos el maestro");
		act.actualizaPastillas(rutaPastillas, rutaPastillasTemp);
		fp.muestraPastillas(rutaPastillas);
	}

}
