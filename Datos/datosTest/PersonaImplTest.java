package datosTest;

import static org.junit.Assert.*;

import org.junit.Test;

import datos.PersonaImpl;

public class PersonaImplTest {

	PersonaImpl defecto=new PersonaImpl();

	@Test
	public void testPersonaImpl() {
		PersonaImpl javi=new PersonaImpl(47428157,"Francisco Javier","Ruiz","Rodríguez");
		assertNotEquals(javi,defecto);
	}
	
	@Test
	public void testPersonaImplInvalido() {
		PersonaImpl javi=new PersonaImpl(47428157,"Francisco Javier*****","Ruiz","Rodríguez");
		PersonaImpl esperado=new PersonaImpl(47428157,"Francisco Javier****","Ruiz","Rodríguez");
		assertEquals(esperado,javi);
	}

	@Test
	public void testGetDNI() {
		PersonaImpl a=new PersonaImpl(1,"a","b","c");
		assertEquals(1,a.getDNI());
	}

	@Test
	public void testGetNombre() {
		PersonaImpl b=new PersonaImpl(50,"Pepito  el  Moreno","b","c");
		assertEquals("Pepito"+"  el  "+"Moreno",b.getNombre());
	}

	@Test
	public void testGetApellido1() {
		PersonaImpl b=new PersonaImpl(50,"Pepi","Seluuuuu","c");
		assertEquals("Seluuuuu",b.getApellido1());
	}

	@Test
	public void testGetApellido2() {
		PersonaImpl b=new PersonaImpl(50,"Pepito  el  Moreno","b","c");
		assertEquals("c",b.getApellido2());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetDNI() {
		defecto.setDNI(-5);
		//assertEquals(-5,defecto.getDNI());
	}

	@Test
	public void testSetDNIValido() {
		defecto.setDNI(55986235);
		assertEquals(55986235,defecto.getDNI());
	}
	@Test
	public void testSetNombre() {
		defecto.setNombre("Silvia");
		assertEquals("Silvia",defecto.getNombre());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetApellido1() {
		defecto.setApellido1("Carneiro Muñiz de la Sierra");
		assertEquals("Carneiro Muñiz de la Sierra",defecto.getApellido1());
	}

	@Test
	public void testSetApellido2() {
		defecto.setApellido2("Muñiz");
		assertEquals("Muñiz",defecto.getApellido2());
	}

	@Test
	public void testCompareTo(){
		PersonaImpl javi=new PersonaImpl(47428157,"Francisco Javier","Ruiz","Rodríguez");
		assertEquals(1,javi.compareTo(defecto));
		defecto.setDNI(56892321);
		assertEquals(-1,javi.compareTo(defecto));
	}
	
}
