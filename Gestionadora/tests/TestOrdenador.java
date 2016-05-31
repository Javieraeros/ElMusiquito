package tests;
import ficheros.*;
import datos.*;
import enums.Marca;
import ficherosGes.*;
import java.util.*;
public class TestOrdenador {

	public static void main(String[] args) {
		String rutaPersonas="Ficheros//Tests//PruebaOrdenarPersonas.dat";
		String rutaClientes="Ficheros//Tests//PruebaOrdenarClientes.dat";
		String rutaEmpleados="Ficheros//Tests//PruebaOrdenarEmpleados.dat";
		String rutaPastillas="Ficheros//Tests//PruebaOrdenarPastillas.dat";
		String rutaCompras="Ficheros//Tests//PruebaOrdenarCompras.dat";
		FicheroPersona fp=new FicheroPersona();
		FicheroCliente fc=new FicheroCliente();
		FicheroEmpleado fe=new FicheroEmpleado();
		FicheroPastillas fpas=new FicheroPastillas();
		Ordenador o=new Ordenador();
		PersonaImpl p;
		ClienteImpl c;
		EmpleadoImpl emp;
		Pastilla pas;
		long dni;
		int id;
		
		/*
		for(int i=0;i<10000;i++){
			dni=(long) (Math.random()*100000000);
			PersonaImpl p=new PersonaImpl(dni, "Francisco Javier", "Ruiz Ruiz Ruiz Ruiz", "Rodríguez-De la ");
			fp.guardaPersona(ruta, p);
		}
		
		fp.muestraPersonas(ruta);
		o.ordenaFicheroPersona(ruta);
		System.out.println("Ya ordenado: \n\n\n");
		fp.muestraPersonas(ruta);
		*/
		/*
		for(int i=0;i<10;i++){
			dni=(long) (Math.random()*100000000);
			p=new PersonaImpl(dni, "Francisco Javier", "Ruiz Ruiz Ruiz Ruiz", "Rodríguez-De la ");
			c=new ClienteImpl(p, "javi@gmail.com", "Mi casa", null);
			fc.guardaClienteSinCompras(rutaClientes, c);
		}
		
		fc.muestraClientes(rutaClientes);
		o.ordenaFicheroCliente(rutaClientes);
		System.out.println("Ta ordenado: \n\n\n");
		fc.muestraClientes(rutaClientes);
		*/
		/*
		for(int i=0;i<1000;i++){
			dni=(long) (Math.random()*100000000);
			p=new PersonaImpl(dni, "Francisco Javier", "Ruiz", "Rodríguez");
			emp=new EmpleadoImpl(p, "ES123456789123456789",500.00,(short)5);
			fe.guardaEmpleado(rutaEmpleados, emp);
		}
		
		fe.muestraEmpleados(rutaEmpleados);
		o.ordenaFicheroEmpleado(rutaEmpleados);
		System.out.println("Ta ordenado: \n\n\n");
		fe.muestraEmpleados(rutaEmpleados);
		*/
		/*
		for(int i=0;i<1000;i++){
			id=(int) (Math.random()*100000000);
			pas=new Pastilla(id, "Generica", "Generica", 1);
			fpas.guardaPastilla(rutaPastillas, pas);
		}
		
		fpas.muestraPastillas(rutaPastillas);
		o.ordenaFicheroPastillas(rutaPastillas);
		System.out.println("Ordenado: \n");
		fpas.muestraPastillas(rutaPastillas);
		System.out.println(fpas.cuentaPastillas(rutaPastillas));
		*/
		
		Vector<InstrumentoImpl> v;
		InstrumentoImpl ins1;
		InstrumentoImpl ins2;
		for(long i=1;i<11;i++){
			ins1=new InstrumentoImpl((int) (Math.random()*100), "Genérico",Marca.Fender,"n","Genérico",100);
			ins2=new InstrumentoImpl((int) (Math.random()*100), "Genérico",Marca.Fender,"n","Genérico",100);
			v=new Vector<InstrumentoImpl>(2,1);
			v.add(ins1);
			v.add(ins2);
			
			c=new ClienteImpl(i*(long) (Math.random()*100),"Javi","Ruiz","Rodriguez","javi@javi.com","Mi casa",v);
			fc.guardaCompras(rutaCompras, c, 0);
			fc.guardaCompras(rutaCompras, c, 1);
		}
		
		fc.muestraCompras(rutaCompras);
		o.ordenaFicheroCompras(rutaCompras);
		System.out.println("Fichero de Compras ya ordenado: ");
		fc.muestraCompras(rutaCompras);
	}

}
