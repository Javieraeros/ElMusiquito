package datosTest;

import static org.junit.Assert.*;

import org.junit.Test;

import datos.*;

public class EmpleadoImplTest {

	@Test
	public void testToString() {
		EmpleadoImpl javi=new EmpleadoImpl(47428157, "Javi", "Ruiz", "Rodriguez", "ES123456789112345678", 1000.35, (short) 1);
		String esperado="47428157 Javi Ruiz Rodriguez\nES123456789112345678 1000.35 1";
		assertEquals(esperado,javi.toString());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetCuentaCorriente() {
		EmpleadoImpl javi=new EmpleadoImpl(47428157, "Javi", "Ruiz", "Rodriguez", "ES123456789112345678", 1000.35, (short) 1);
		javi.setCuentaCorriente("E2123456789112345678");
	}

	@Test
	public void testSetSueldo() {
		EmpleadoImpl javi=new EmpleadoImpl(47428157, "Javi", "Ruiz", "Rodriguez", "ES123456789112345678", 1000.35, (short) 1);
		javi.setSueldo(400);
		assertEquals(400.00,javi.getSueldo(),0.001);
	}

	@Test
	public void testSetTienda() {
		EmpleadoImpl javi=new EmpleadoImpl(47428157, "Javi", "Ruiz", "Rodriguez", "ES123456789112345678", 1000.35, (short) 1);
		javi.setTienda((short) 5);
	}

	@Test
	public void testCuentaCorrienteValida() {
		EmpleadoImpl javi=new EmpleadoImpl(47428157, "Javi", "Ruiz", "Rodriguez", "ES123456789112345678", 1000.35, (short) 1);
		boolean resultado=javi.cuentaCorrienteValida("es123456789912345678");
		assertEquals(false,resultado);
	}

}
