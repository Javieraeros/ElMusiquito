package ficherosTest;

import static org.junit.Assert.*;
import datos.*;
import ficheros.*;
import org.junit.Test;

public class FicheroPersonaTest {
	FicheroPersona util=new FicheroPersona();
	@Test
	public void testGuardaPersona() {
		PersonaImpl p=new PersonaImpl(47428157, "Francisco Javier", "Ruiz", "Rodríguez");
		util.guardaPersona("Ficheros//PersonasPrueba.dat", p);
	}

	@Test
	public void testCuentaPersonas() {
		assertEquals(2,util.cuentaPersonas("Ficheros//PersonasTest.dat"));
	}

	@Test
	public void testDevuelvePersona(){
		PersonaImpl p=util.devuelvePersona("Ficheros//PersonasTest.dat",45454545);
		assertEquals("Carnerio",p.getApellido1());
	}
}
