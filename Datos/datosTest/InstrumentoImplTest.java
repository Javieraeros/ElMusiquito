package datosTest;

import static org.junit.Assert.*;
import datos.InstrumentoImpl;
import enums.Marca;

import org.junit.Test;

public class InstrumentoImplTest {
	/*Objeto para toda la clase*/
	InstrumentoImpl defecto=new InstrumentoImpl();
	
	@Test
	public void testInstrumentoImpl() {
		InstrumentoImpl Viopolin=new InstrumentoImpl("Violin", Marca.Stradivarious, 
							"Gran violin, mejor profesional", "VT-500", -800.32);
		assertEquals("Violin",Viopolin.getNombre());
		assertEquals(Marca.Stradivarious,Viopolin.getMarca());
		assertEquals("Gran violin, mejor profesional",Viopolin.getDescripcion());
		assertEquals("VT-500",Viopolin.getModelo());
		assertEquals(1,Viopolin.getPrecioVenta(),0.0001);
	}

	@Test
	public void testInstrumentoImplCopia(){
		InstrumentoImpl copiaDefecto=new InstrumentoImpl(defecto);
		assertEquals(defecto.getId(),copiaDefecto.getId());
	}
	@Test
	public void testSetNombre() {
		defecto.setNombre("Tuba grande y hermosa como una osa");
		assertEquals("Tuba grande y hermos",defecto.getNombre());
	}

	@Test
	public void testSetMarca() {
		Marca javi=Marca.Rocio;
		defecto.setMarca(javi);
		assertEquals(Marca.Rocio,defecto.getMarca());
	}

	@Test
	public void testSetModelo() {
		defecto.setModelo("DRAAAAAAAAAAAAAAAAAAAAAAVEN");
		assertEquals("DRAAAAAAAAAAAAAAAAAA",defecto.getModelo());
	}

	@Test
	public void testSetPrecioVenta() {
		defecto.setPrecioVenta(50);
		assertEquals(50.00,defecto.getPrecioVenta(),0.001);
	}

	@Test
	public void testSetDescripcion() {
		defecto.setDescripcion("Voy a poner todo lo que aquí se me ocurra para demostrar"
				+ " que efectivamente puedo poner lo que me de la gana en la descripción,"
				+ "independientemente de todos los carácteres que ponga, haciendo así"
				+ " que la memoria del objeto sea enorme.");
		String esperado="Voy a poner todo lo que aquí se me ocurra para demostrar que efectivamente puedo poner lo que me de la gana en la descripción,";
		esperado=esperado+"independientemente de todos los carácteres que ponga, haciendo así que la memoria del objeto sea enorme.";
		assertEquals(esperado,defecto.getDescripcion());
	}
	
	@Test
	public void testEquals(){
		InstrumentoImpl compara=new InstrumentoImpl();
		assertNotEquals(defecto,compara);
		compara=new InstrumentoImpl(defecto.getId(),"Saxofon",Marca.Selmer,"Gran Saxo, mejor persona",
						"Serie 3",2500);
		assertEquals(defecto,compara);
	}

}
