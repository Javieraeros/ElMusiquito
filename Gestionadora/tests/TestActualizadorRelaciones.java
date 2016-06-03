package tests;

import ficherosGes.*;

import java.io.*;
import java.util.Vector;

import datos.*;
import enums.Marca;
import enums.Tipo;
import ficheros.*;
public class TestActualizadorRelaciones {

	public static void main(String[] args) {
		Actualizador act=new Actualizador();
		FicheroGuitarra fg=new FicheroGuitarra();
		Ordenador o=new Ordenador();
		
		String rutaRelaciones="Ficheros//Tests//Actualizador//Relaciones.dat";
		String rutaRelacionesTemp="Ficheros//Tests//Actualizador//RelacionesTemp.dat";
		
		InstrumentoImpl ins;
		CuerdaImpl cuerda;
		GuitarraElectricaImpl g;
		for(int i=1;i<11;i++){
			ins=new InstrumentoImpl(i, "Genérico",Marca.Fender,"s","Genérico", 100);
			cuerda=new CuerdaImpl(ins, 4, "genérico",(byte) 0);
			int[] pastillas=new int[]{i*2,
									  i*3,
									  i*4};
			g=new GuitarraElectricaImpl(cuerda, Tipo.ES, pastillas,false,3);
			fg.guardaRelacionPastilla(rutaRelaciones, g);
		}
		fg.muestraCompras(rutaRelaciones);
		System.out.println("Ya ordenado:  ");
		o.ordenaFicheroRelaciones(rutaRelaciones);
		fg.muestraCompras(rutaRelaciones);
		
		
		System.out.println("Añadimos una relacion con idGuitarra 225 e idPastillas 8000,8002,9000");
		ins=new InstrumentoImpl(225, "Genérico",Marca.Fender,"s","Genérico", 100);
		cuerda=new CuerdaImpl(ins, 4, "genérico",(byte) 0);
		int[] pastillas=new int[]{8000,
								  8002,
								  9000};
		
		g=new GuitarraElectricaImpl(cuerda, Tipo.ES, pastillas,false,3);
		fg.guardaRelacionPastilla(rutaRelacionesTemp, g);
		
		
		System.out.println("Eliminamos las pastillas de la guitarra 5");
		ins=new InstrumentoImpl(5, "Genérico",Marca.Fender,"s","Genérico", 100);
		cuerda=new CuerdaImpl(ins, 4, "genérico",(byte) 0);
		pastillas=new int[]{10,
							15,
							20};
		
		g=new GuitarraElectricaImpl(cuerda, Tipo.ES, pastillas,false,3);
		fg.guardaRelacionPastilla(rutaRelacionesTemp, g);
		
		
		System.out.println("Mostramos todo lo del temporal,ya ordenado:");
		o.ordenaFicheroRelaciones(rutaRelacionesTemp);
		fg.muestraCompras(rutaRelacionesTemp);
		
		
		System.out.println("Actualizamos y mostramos el maestro");
		act.actualizaRelaciones(rutaRelaciones, rutaRelacionesTemp);
		fg.muestraCompras(rutaRelaciones);
	}

}
