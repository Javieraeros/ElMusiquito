package datosTest;

import static org.junit.Assert.*;

import datos.InstrumentoImpl;
import datos.VientoImpl;
import enums.Marca;

import org.junit.Test;

public class VientoImplTest {
	
	VientoImpl defecto=new VientoImpl();
	@Test
	public void testVientoImpl() {
		VientoImpl trompeta=new VientoImpl("Trompeta", Marca.Honsuy,"Trompeta de calidad", 
				"300-RT", 450.15, 'b', "Lab3-Sib6",(byte) 1);
		
	}

	@Test
	public void testVientoImplInstrumento(){
		InstrumentoImpl instrumentoDefecto=new InstrumentoImpl();
		VientoImpl copia=new VientoImpl(defecto, 'b', "Lab3-Sib6",(byte) 1);
		assertEquals(instrumentoDefecto.getNombre(),copia.getNombre());
	}
	
	@Test
	public void testSetAfinacion() {
		defecto.setAfinacion('y');
		assertEquals('C',defecto.getAfinacion());
	}


	@Test
	public void testSetBoquilla() {
		defecto.setBoquilla((byte) 2);
		assertEquals((byte)0,defecto.getBoquilla());
	}

	@Test
	public void testAfinacionValida() {
		assertTrue(defecto.afinacionValida('a'));
		assertTrue(defecto.afinacionValida('b'));
		assertTrue(defecto.afinacionValida('c'));
		assertTrue(defecto.afinacionValida('d'));
		assertTrue(defecto.afinacionValida('e'));
		assertTrue(defecto.afinacionValida('f'));
		assertTrue(defecto.afinacionValida('g'));
		assertTrue(defecto.afinacionValida('A'));
		assertTrue(defecto.afinacionValida('B'));
		assertTrue(defecto.afinacionValida('C'));
		assertTrue(defecto.afinacionValida('D'));
		assertTrue(defecto.afinacionValida('E'));
		assertTrue(defecto.afinacionValida('F'));
		assertTrue(defecto.afinacionValida('G'));
		assertFalse(defecto.afinacionValida('0'));
		assertFalse(defecto.afinacionValida('.'));
		assertFalse(defecto.afinacionValida('*'));
		assertFalse(defecto.afinacionValida('¨'));
	}

}
