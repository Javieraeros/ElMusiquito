package tests;

import ficherosGes.Actualizador;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import datos.*;
import ficheros.*;
public class TestActualizador {

	public static void main(String[] args) {
		Actualizador act=new Actualizador();
		FicheroPersona fp=new FicheroPersona();
		String rutaPersonas="Ficheros//Tests//PruebaActualizarPersonas.dat";
		String rutaPersonasTemp="Ficheros//Tests//PruebaActualizarPersonasTemp.dat";
		PersonaImpl p;
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		//Creamos el fichero Temporal
		try {
			fos = new FileOutputStream(rutaPersonasTemp);
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
			fp.guardaPersona(rutaPersonas, p);
		}
		System.out.println("Mostramos toda las personas del fichero:");
		fp.muestraPersonas(rutaPersonas);
		
		
		System.out.println("Eliminamos la persona con dni 2");
		p=new PersonaImpl(2,"","","");
		fp.guardaPersona(rutaPersonasTemp, p);
		
		/*System.out.println("Añadimos uan persona con dni 14");
		p=new PersonaImpl(14,"Nueva","Personita","en el mundo");
		fp.guardaPersona(rutaPersonasTemp,p);
		*/
		
		System.out.println("Modificamos a al persona 4");
		p=new PersonaImpl(4, "Persona", "Modificada", "Por mi");
		fp.guardaPersona(rutaPersonasTemp, p);
		
		System.out.println("Mostramos todo lo del temporal:");
		fp.muestraPersonas(rutaPersonasTemp);
		
		System.out.println("Actualizamos y mostramos el maestro");
		act.actualizaPersonas(rutaPersonas, rutaPersonasTemp);
		fp.muestraPersonas(rutaPersonas);
	}

}
