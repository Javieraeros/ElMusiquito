package tests;
import ficheros.*;
import datos.*;
import ficherosGes.*;
public class TestOrdenador {

	public static void main(String[] args) {
		String rutaPersonas="Ficheros//Tests//PruebaOrdenarPersonas.dat";
		String rutaClientes="Ficheros//Tests//PruebaOrdenarClientes.dat";
		String rutaEmpleados="Ficheros//Tests//PruebaOrdenarEmpleados.dat";
		FicheroPersona fp=new FicheroPersona();
		FicheroCliente fc=new FicheroCliente();
		FicheroEmpleado fe=new FicheroEmpleado();
		Ordenador o=new Ordenador();
		PersonaImpl p;
		ClienteImpl c;
		EmpleadoImpl emp;
		long dni;
		
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
	}

}
