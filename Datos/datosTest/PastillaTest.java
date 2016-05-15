package datosTest;

import static org.junit.Assert.*;

import org.junit.Test;

import datos.Pastilla;

public class PastillaTest {
	Pastilla defecto=new Pastilla();
	@Test
	public void testPastillaStringStringInt() {
		Pastilla a=new Pastilla("Hambucker","3000",2);
		//De esta forma comprobamos que efectivamente está cogiendo el último id
		assertEquals(a.tomaID("Ficheros//IdPastillas.dat")-1,a.getId());
	}

	@Test
	public void testPastillaId() {
		Pastilla b=new Pastilla(3,"Hambucker","3000",2);
		assertEquals(3,b.getId());
	}

	@Test
	public void testSetMarca() {
		defecto.setMarca("MaRkAa ReEsChuLo0NaH pa tu body");
		assertEquals("MaRkAa ReEsChuLo0NaH",defecto.getMarca());
	}

	@Test
	public void testSetModelo() {
		defecto.setModelo("Tara Lynn (buscar en google)");
		assertEquals("Tara Lynn (buscar en",defecto.getModelo());
	}

	@Test
	public void testSetBobinas() {
		defecto.setBobinas(6);
		assertEquals(1,defecto.getBobinas());
		defecto.setBobinas(-8);
		assertEquals(1,defecto.getBobinas());
	}

}
