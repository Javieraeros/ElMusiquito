package ficherosTest;

import java.util.Vector;

import datos.*;
import enums.*;
import ficheros.*;

public class TestNoJunitFicheros {

	public static void main(String[] args) {
		FicheroInstrumento fi=new FicheroInstrumento();
		FicheroPersona fp=new FicheroPersona();
		FicheroCliente fc=new FicheroCliente();
		/*System.out.println(fi.descripcionInstrumento("Ficheros//Tests//Descripcion.txt", 2));
		CuerdaImpl chelo=new CuerdaImpl("Violonchelo", Marca.Stradivarious, 
				"Bueno,bueno", "Strad400", 900.50, 4, "Mi2-Sol4",(byte) 0);
		fi.guardaDescripcion("Ficheros//Descripcion.txt", chelo);
		System.out.println(fi.descripcionInstrumento("Ficheros//Tests//Descripcion.txt", chelo.getId()));*/
		
		
		/*System.out.println(fi.devuelveInstrumento("Ficheros//Tests//Instrumentos.dat", 15));*/
		
		
		/*fp.muestraPersonas("Ficheros//Personas.dat");
		System.out.println();
		System.out.println();
		System.out.println(fc.devuelveCliente("Ficheros//Tests//Clientes.dat", "Ficheros//Tests//Personas.dat", 555,
				"Ficheros//Tests//Instrumentos.dat","Ficheros//Tests//Compras.dat"));*/
		
		Vector<InstrumentoImpl> v=fc.devuelveCompras("Ficheros//Tests//Compras.dat","Ficheros//Tests//Instrumentos.dat", 47428157);
		
		System.out.println(v.get(1));
	}

}
