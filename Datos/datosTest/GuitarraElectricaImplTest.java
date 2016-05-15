package datosTest;

import static org.junit.Assert.*;
import datos.*;
import enums.*;
import org.junit.Test;

public class GuitarraElectricaImplTest {
	GuitarraElectricaImpl defecto=new GuitarraElectricaImpl();
	@Test
	public void testGuitarraElectricaImplCuerda() {
		CuerdaImpl cuerda=new CuerdaImpl();
		int[] aPastillas={1,1,1};
		GuitarraElectricaImpl guitarra=new GuitarraElectricaImpl(cuerda, Tipo.ES, aPastillas, false, 5);
		assertEquals(cuerda.getId(),guitarra.getId());
	}

	@Test
	public void testSetTipo() {
		defecto.setTipo(Tipo.Flying_V);
		assertEquals(Tipo.Flying_V,defecto.getTipo());
	}

	@Test
	public void testSetPastillas() {
		int[] pastillas={0,8,5,3,4};
		int[] esperado={0,8,5};
		CuerdaImpl cuerda=new CuerdaImpl();
		GuitarraElectricaImpl guitarra=new GuitarraElectricaImpl(cuerda, Tipo.ES, pastillas, false, 5);
		assertArrayEquals(esperado,guitarra.getIdPastillas());
	}


	@Test
	public void testSetControles() {
		defecto.setControles(-5);
		assertEquals(1,defecto.getControles());
	}

}
