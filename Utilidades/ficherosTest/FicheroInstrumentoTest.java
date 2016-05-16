package ficherosTest;

import static org.junit.Assert.*;
import datos.*;
import ficheros.*;
import enums.*;
import org.junit.Test;

public class FicheroInstrumentoTest {

	FicheroInstrumento fi=new  FicheroInstrumento();
	String ruta="Ficheros//Tests//PruebaGuardaInstrumento.dat";
	@Test
	public void testGuardaInstrumento() {
		
		InstrumentoImpl generico=new InstrumentoImpl(1400, "Tambor",Marca.Rocio,"Baquetas incluidas","Tronador50", 425);
		fi.guardaInstrumento(ruta, generico);
	}

	@Test
	public void testDevuelveInstrumento() {
		InstrumentoImpl ii=fi.devuelveInstrumento(ruta, 1400);
		assertEquals(Marca.Rocio,ii.getMarca());
	}

	@Test
	public void testGuardaDescripcion() {
		InstrumentoImpl a=new InstrumentoImpl(1400, "Corneta",Marca.Honsuy,"Pedaso de corneta","Tronador50", 125);
		fi.guardaDescripcion("Ficheros//Tests//Descripcion.txt", a);
	}

	@Test
	public void testDescripcionInstrumento() {
		assertEquals("Pedaso de corneta",fi.descripcionInstrumento("Ficheros//Tests//Descripcion.txt", 1400));
	}

}
