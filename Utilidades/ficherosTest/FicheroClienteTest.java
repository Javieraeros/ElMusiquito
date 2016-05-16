package ficherosTest;

import static org.junit.Assert.*;

import java.util.Vector;

import datos.*;
import ficheros.*;

import org.junit.Test;

public class FicheroClienteTest {
	FicheroPersona fp=new FicheroPersona();
	FicheroCliente fc=new FicheroCliente();
	@Test
	public void testGuardaCliente() {
		Vector<InstrumentoImpl> v=null;
		PersonaImpl p=new PersonaImpl(47428157, "Javi", "Ruiz", "Rodriguez");
		ClienteImpl c=new ClienteImpl(p, "pajarruro@gmail.com", "platino 42", v);
		fp.guardaPersona("Ficheros//Tests//PersonaTestCliente.dat", p);
		fc.guardaCliente("Ficheros//Tests//ClientesTests.dat", c);
		
	}

	@Test
	public void testDevuelveCliente() {
		ClienteImpl c=fc.devuelveCliente("Ficheros//Tests//ClientesTests.dat","Ficheros//Tests//PersonaTestCliente.dat", 47428157);
		assertEquals("platino 42",c.getDireccion());
	}

}
