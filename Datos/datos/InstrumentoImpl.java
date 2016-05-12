/*
 * Restricciones:
 * 
 * Métodos heredados/añadidos:
 * int tomaId();
 * 
 */
package datos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import enums.Marca;
import interfaces.Instrumento;

public class InstrumentoImpl implements Instrumento,Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private static int idCompartido=0;
	private String nombre;
	private Marca marca;
	private String descripcion;
	private String modelo;
	private double precioVenta;
	
	public InstrumentoImpl(){
		id=tomaID();
	}
	
	public int getId() {
		return id;
	}

	
	public int getIdCompartido() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Marca getMarca() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getModelo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public double getPrecioVenta() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub

	}

	
	public void setMarca(Marca marca) {
		// TODO Auto-generated method stub

	}

	
	public void setModelo(String modelo) {
		// TODO Auto-generated method stub

	}

	
	public void setPrecioVenta(String precioVenta) {
		// TODO Auto-generated method stub

	}

	
	public void setDescripcion(String descripcion) {
		// TODO Auto-generated method stub

	}

	public int tomaID(){
		int id = 1;
		if (idCompartido == 0) {
			try {
				FileInputStream leerID = new FileInputStream("Ficheros//Id.dat");
				DataInputStream in = new DataInputStream(leerID);
				/*
				 * Puesto que sabesmos que, si el fichero existe,solo tiene
				 * escrito un entero,
				 * no hace falta saber si hay algo que leer,ni cuanto hay
				 * puesto que solo habrá un entero.
				 */
				// Leo el ID del archivo y se lo asigno a IDTodos
				id = in.readInt();
				id = id + 1;
				idCompartido = id;

				// Cerramos y escribimos el nuevo ID
				in.close();
				leerID.close();
			} catch (FileNotFoundException e1) {
				System.out.println("Que has hecho que no encuentro el fichero Alumnos.dat??");
				System.out.println("Bueno, no pasa nada, lo vuelvo a crear... PERO QUE NO VUELVA A OCURRIR");
			} catch (IOException e) {
				System.out.println(e);
			}
		} else {
			id = idCompartido + 1;
			idCompartido++;
		}
		try {
			FileOutputStream escribeID = new FileOutputStream("Ficheros//Id.dat");
			DataOutputStream out = new DataOutputStream(escribeID);
			out.writeInt(id);
			escribeID.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return id;
	}
}
