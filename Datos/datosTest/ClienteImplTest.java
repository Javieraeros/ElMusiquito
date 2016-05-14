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
		String esperado="47428157 Javi Ruiz Rodríguez\npajarrurro@gmail.com Mi casa";
		System.out.println(esperado);
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

	

	@Test(expected=IllegalArgumentException.class)
	public void testSetCorreoe() {
		Vector<InstrumentoImpl> vector=new Vector<InstrumentoImpl>(5,0);
		InstrumentoImpl saxo=new InstrumentoImpl();
		InstrumentoImpl violin=new InstrumentoImpl();
		vector.addElement(saxo);
		vector.addElement(violin);
		ClienteImpl javi=new ClienteImpl(47428157,"Javi","Ruiz","Rodríguez",
										 "pajarrurrogmail.com","Mi casa",vector);
		javi.setCorreoe("@gmail.com");
	}

	@Test
	public void testSetDireccion() {
		Vector<InstrumentoImpl> vector=new Vector<InstrumentoImpl>(5,0);
		InstrumentoImpl saxo=new InstrumentoImpl();
		InstrumentoImpl violin=new InstrumentoImpl();
		vector.addElement(saxo);
		vector.addElement(violin);
		ClienteImpl javi=new ClienteImpl(47428157,"Javi","Ruiz","Rodríguez",
										 "pajarrurrogmail.com","Mi casa",vector);
		javi.setDireccion("mi casaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}

	@Test
	public void testSetCompras() {
		Vector<InstrumentoImpl> vector=new Vector<InstrumentoImpl>(5,0);
		InstrumentoImpl saxo=new InstrumentoImpl();
		InstrumentoImpl violin=new InstrumentoImpl();
		vector.addElement(saxo);
		vector.addElement(violin);
		ClienteImpl javi=new ClienteImpl(47428157,"Javi","Ruiz","Rodríguez",
										 "pajarrurrogmail.com","Mi casa",vector);
		
		Vector<InstrumentoImpl> v=new Vector<InstrumentoImpl>(5,0);
		InstrumentoImpl violonchelo=new InstrumentoImpl();
		InstrumentoImpl viopolin=new InstrumentoImpl();
		vector.addElement(violonchelo);
		vector.addElement(viopolin);
		javi.setCompras(v);
		assertEquals(v,javi.getCompras());
	}

	
	@Test
	public void testCorreoeValido() {
		ClienteImpl defecto=new ClienteImpl();
		assertFalse(defecto.correoeValido("javi@.com"));
	}
}
