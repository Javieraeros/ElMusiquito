package tests;

import ficherosGes.*;

import java.io.*;
import java.util.Vector;

import datos.*;
import enums.Marca;
import ficheros.*;
public class TestActualizadorCompras {

	public static void main(String[] args) {
		Actualizador act=new Actualizador();
		FicheroCliente fc=new FicheroCliente();
		Ordenador o=new Ordenador();
		
		String rutaCompras="Ficheros//Tests//Actualizador//Compras.dat";
		String rutaComprasTemp="Ficheros//Tests//Actualizador//ComprasTemp.dat";
		ClienteImpl c;

		Vector<InstrumentoImpl> v;
		InstrumentoImpl ins1;
		InstrumentoImpl ins2;
		for(long i=1;i<11;i++){
			ins1=new InstrumentoImpl((int) i, "Genérico",Marca.Fender,"n","Genérico",100);
			ins2=new InstrumentoImpl((int) (i+1), "Genérico",Marca.Fender,"n","Genérico",100);
			v=new Vector<InstrumentoImpl>(2,1);
			v.add(ins1);
			v.add(ins2);
			
			c=new ClienteImpl(i,"Javi","Ruiz","Rodriguez","javi@javi.com","Mi casa",v);
			fc.guardaCompras(rutaCompras, c, 0);
			fc.guardaCompras(rutaCompras, c, 1);
		}
		
		fc.muestraCompras(rutaCompras);
		o.ordenaFicheroCompras(rutaCompras);
		System.out.println("Fichero de Compras ya ordenado: ");
		fc.muestraCompras(rutaCompras);
		
		
		System.out.println("Añadimos una compra con dni 25 e id 80");
		ins1=new InstrumentoImpl(80, "Añadir",Marca.Fender,"n","Añadir",100);
		v=new Vector<InstrumentoImpl>(1,1);
		v.add(ins1);
		
		c=new ClienteImpl(25,"Javi","Ruiz","Rodriguez","javi@javi.com","Mi casa",v);
		fc.guardaCompras(rutaComprasTemp,c,0);
		
		
		System.out.println("Eliminamos la compra con dni 1 e id 2");
		ins1=new InstrumentoImpl(2, "Eliminar",Marca.Fender,"n","Eliminar",100);
		v=new Vector<InstrumentoImpl>(1,1);
		v.add(ins1);
		
		c=new ClienteImpl(1,"Javi","Ruiz","Rodriguez","javi@javi.com","Mi casa",v);
		fc.guardaCompras(rutaComprasTemp,c,0);
		
		
		System.out.println("Mostramos todo lo del temporal,ya ordenado:");
		o.ordenaFicheroCompras(rutaComprasTemp);
		fc.muestraCompras(rutaComprasTemp);
		
		
		System.out.println("Actualizamos y mostramos el maestro");
		act.actualizaCompras(rutaCompras, rutaComprasTemp);
		fc.muestraCompras(rutaCompras);
	}

}
