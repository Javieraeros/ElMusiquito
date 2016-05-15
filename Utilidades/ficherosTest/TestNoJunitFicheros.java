package ficherosTest;

import datos.CuerdaImpl;
import enums.Marca;
import ficheros.FicheroInstrumento;

public class TestNoJunitFicheros {

	public static void main(String[] args) {
		FicheroInstrumento fi=new FicheroInstrumento();
		/*System.out.println(fi.descripcionInstrumento("Ficheros//Descripcion.txt", 2));
		CuerdaImpl chelo=new CuerdaImpl("Violonchelo", Marca.Stradivarious, 
				"Bueno,bueno", "Strad400", 900.50, 4, "Mi2-Sol4",(byte) 0);
		fi.guardaDescripcion("Ficheros//Descripcion.txt", chelo);
		System.out.println(fi.descripcionInstrumento("Ficheros//Descripcion.txt", chelo.getId()));*/
		System.out.println(fi.devuelveInstrumento("Ficheros//Instrumentos.dat", 15));
	}

}
