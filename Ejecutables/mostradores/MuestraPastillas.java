package mostradores;

import ficheros.FicheroPastillas;

public class MuestraPastillas {

	public static void main(String[] args) {
		FicheroPastillas fp=new FicheroPastillas();
		fp.muestraPastillas("Ficheros//Pastillas//Pastillas.dat");
		System.out.println(fp.devuelvePastilla("Ficheros//PastillasPrueba.dat", 162));
	}

}
