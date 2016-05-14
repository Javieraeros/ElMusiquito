package datosTest;

import static org.junit.Assert.*;
import datos.*;
import enums.*;
import org.junit.Test;

public class SaxofonImplTest {
	SaxofonImpl defecto=new SaxofonImpl();
	@Test
	public void testSaxofonImpl() {
		SaxofonImpl javi=new SaxofonImpl("Saxo Alto", Marca.Selmer, "Bueno", "AL-1080", 1800,
				'e', "Reb4-La6", (byte) 1,
				FamiliaSaxo.Alto, "S80 C*","Laton",Acabado.DoradoB);
	}

	@Test
	public void testSaxofonImplId() {
		SaxofonImpl javi=new SaxofonImpl(500,"Saxo Alto", Marca.Selmer, "Bueno", "AL-1080", 1800,
				'e', "Reb4-La6", (byte) 1,
				FamiliaSaxo.Alto, "S80 C*","Laton",Acabado.DoradoB);
		assertEquals(500,javi.getId());
	}

	@Test
	public void testSaxofonImplViento(){
		InstrumentoImpl saxoTrompeta=new InstrumentoImpl(9000, "SaxoTrompeta", Marca.Selmer, "Nuevo saxo-trompeta", "Nuevo", 8000);
		VientoImpl trompeta=new VientoImpl(saxoTrompeta,'b', "Lab3-Sib6",(byte) 1);
		SaxofonImpl saxo=new SaxofonImpl(trompeta,FamiliaSaxo.Tenor, "Tronadora", "Oro puro", Acabado.DoradoB);
		assertEquals(9000,saxo.getId());
		assertEquals("SaxoTrompeta",saxo.getNombre());
		assertEquals('b',saxo.getAfinacion());
		assertEquals(1,saxo.getBoquilla());
		assertEquals("Oro puro",saxo.getMaterial());
		
	}
	

	@Test
	public void testSetBoquillaSaxo() {
		defecto.setBoquillaSaxo("Peeaasso de bokiya looco");
		assertEquals("Peeaasso de bokiya l",defecto.getBoquillaSaxo());
	}

	@Test
	public void testSetMaterial() {
		defecto.setMaterial("Material bueno");
		assertEquals("Material bueno",defecto.getMaterial());	
		}


}
