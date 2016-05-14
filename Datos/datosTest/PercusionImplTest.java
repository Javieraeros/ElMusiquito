package datosTest;

import static org.junit.Assert.*;
import datos.PercusionImpl;
import org.junit.Test;
import enums.Marca;

public class PercusionImplTest {
	PercusionImpl defecto=new PercusionImpl();
	
	@Test
	public void testPercusionImpl() {
		PercusionImpl tambor=new PercusionImpl("Tambor",Marca.Jupiter,"Muy bueno,baquetas incluidas", 
				"Tronador 3000", 120, 'F', "Compuesto por muchas cositas nazis", true);
		assertNotEquals(1,tambor.getId());
	}

	@Test
	public void testPercusionImplId() {
		PercusionImpl tambor=new PercusionImpl(5,"Tambor",Marca.Jupiter,"Muy bueno,baquetas incluidas", 
				"Tronador 3000", 120, 'F', "Compuesto por muchas cositas nazis", true);
		assertEquals(5,tambor.getId());
		
	}

	@Test
	public void testSetAfinacion() {
		defecto.setAfinacion('H');
		assertEquals('C',defecto.getAfinacion());
	}

	@Test
	public void testSetMaterial() {
		defecto.setMaterial("El mejor material qu; puedas tener");
		assertEquals("El mejor material qu",defecto.getMaterial());
	}


}
