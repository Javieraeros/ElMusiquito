package datosTest;

import static org.junit.Assert.*;
import datos.*;
import org.junit.Test;
import enums.*;

public class CuerdaImplTest {
	CuerdaImpl defecto=new CuerdaImpl();
	@Test
	public void testCuerdaImpl() {
		CuerdaImpl chelo=new CuerdaImpl("Violonchelo", Marca.Stradivarious, 
				"Bueno,bueno", "Strad400", 900.50, 4, "Mi2-Sol4",(byte) 0);
		assertNotEquals(10,chelo.getId());
	}

	@Test
	public void testCuerdaImplId() {
		CuerdaImpl chelo=new CuerdaImpl(50248,"Violonchelo", Marca.Stradivarious, 
				"Bueno,bueno", "Strad400", 900.50, 4, "Mi2-Sol4",(byte) 0);
		assertEquals(50248,chelo.getId());
	}

	@Test
	public void testSetCuerdas() {
		defecto.setCuerdas(-100);
		assertEquals(1,defecto.getCuerdas());
	}

	@Test
	public void testSetRegistro() {
		defecto.setRegistro("De donde yo quiera a donde yo quiera.");
		assertEquals("De donde yo quiera a",defecto.getRegistro());
	}

	@Test
	public void testSetTipoCuerda() {
		defecto.setTipoCuerda((byte)2);
		assertEquals(1,defecto.getTipoCuerda());
	}

}
