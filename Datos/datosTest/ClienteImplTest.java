package datosTest;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;
import datos.*;

public class ClienteImplTest {

	@Test
	public void testToString() {
		Vector<InstrumentoImpl> vector=new Vector<InstrumentoImpl>(5,0);
		
		InstrumentoImpl saxo=new InstrumentoImpl();
		InstrumentoImpl violin=new InstrumentoImpl();
		vector.addElement(saxo);
		vector.addElement(violin);
		ClienteImpl javi=new ClienteImpl(47428157,"Javi","Ruiz","Rodríguez",
										 "pajarrurro@gmail.com","Mi casa",vector);
		String esperado="47428157 Javi Ruiz Rodríguez pajarrurro@gmail.com Mi casa";
		assertEquals(esperado,javi.toString());
	}

	@Test
	public void testClienteImpl() {
		Vector<InstrumentoImpl> vector=new Vector<InstrumentoImpl>(5,0);
		InstrumentoImpl saxo=new InstrumentoImpl();
		InstrumentoImpl violin=new InstrumentoImpl();
		vector.addElement(saxo);
		vector.addElement(violin);
		ClienteImpl javi=new ClienteImpl(47428157,"Javi","Ruiz","Rodríguez",
										 "pajarrurrogmail.com","Mi casa",vector);
		ClienteImpl esperado=new ClienteImpl(47428157,"Javi","Ruiz","Rodríguez",
				 "defecto@defecto.com","Mi casa",vector);
		assertEquals(esperado,javi);
	}

	@Test
	public void testGetCompras() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCorreoe() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDireccion() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCompras() {
		fail("Not yet implemented");
	}

	@Test
	public void testCorreoeValido() {
		fail("Not yet implemented");
	}

}
