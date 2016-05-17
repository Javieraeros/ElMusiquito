package ficherosTest;

import static org.junit.Assert.*;

import java.util.Vector;

import datos.*;
import ficheros.*;

import org.junit.Test;

public class FicheroClienteTest {
	FicheroPersona fp=new FicheroPersona();
	FicheroCliente fc=new FicheroCliente();
	FicheroInstrumento fi=new FicheroInstrumento();
	String rutaPersona="Ficheros//Tests//PersonaTestCliente.dat";
	String rutaCliente="Ficheros//Tests//ClientesTests.dat";
	String rutaCompras="Ficheros//Tests//Compras.dat";
	String rutaInstrumentos="Ficheros//Tests//Instrumentos.dat";
	String rutaDesc="Ficheros//Tests//Descripcion.txt";
	
	@Test
	public void testGuardaCliente() {
		Vector<InstrumentoImpl> v=new Vector<InstrumentoImpl>(1, 1);
		InstrumentoImpl defecto=new InstrumentoImpl();
		v.add(defecto);
		PersonaImpl p=new PersonaImpl(47428157, "Javi", "Ruiz", "Rodriguez");
		ClienteImpl c=new ClienteImpl(p, "pajarruro@gmail.com", "platino 42", v);
		fp.guardaPersona(rutaPersona,p);
		fi.guardaInstrumento(rutaInstrumentos,rutaDesc, defecto);
		fc.guardaCliente(rutaCliente,rutaCompras, c);
		
	}

	@Test
	public void testDevuelveCliente() {
		ClienteImpl c=fc.devuelveCliente(rutaCliente,rutaPersona, 47428157,
											rutaInstrumentos,rutaCompras);
		assertEquals("platino 42",c.getDireccion());
	}

}
