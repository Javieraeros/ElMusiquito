/*
 * Restricciones:
 * Tando el nombre como el modelo, no podrá superar los 20 caracteres.
 * Precio mayor que 0.
 * Métodos heredados/añadidos:
 * int tomaId();
 * boolean equals();
 * String toString();
 */
package datos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import compartidas.UtilidadesCompartidas;
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
		id=tomaID("Ficheros//Id.dat");
		nombre="Saxofon";
		marca=Marca.Selmer;
		descripcion="Gran Saxo, mejor persona";
		modelo="Serie 3";
		precioVenta=2500;
	}
	
	public InstrumentoImpl(String nombre,Marca marca,String descripcion,String modelo,double precio){
		this();
		nombre=UtilidadesCompartidas.ajustaLongitud(nombre, 20);
		this.nombre=nombre;    //También podríamos usar setNombre, pero esto me parece más correcto
		
		this.marca=marca;
		
		this.descripcion=descripcion;
		
		modelo=UtilidadesCompartidas.ajustaLongitud(modelo, 20);
		this.modelo=modelo;
		
		if(precio<0){
			System.out.println("El precio no puede ser menor que 0.");
			System.out.println("Capitalista!, Opresor!, Falocentrista!");
			System.out.println("(bien dicho Marta)");
			precio=1;
		}
		this.precioVenta=precio;
	}
	
	/* Se desaconseja el uso de este constructor para nuevos objetos
	 * Solo se usará para crear objetos almacenados en ficheros.
	 */
	public InstrumentoImpl(int id,String nombre,Marca marca,String descripcion,String modelo,double precio){
		this.id=id;
		nombre=UtilidadesCompartidas.ajustaLongitud(nombre, 20);
		this.nombre=nombre;    //También podríamos usar setNombre, pero esto me parece más correcto
		
		this.marca=marca;
		
		this.descripcion=descripcion;
		
		modelo=UtilidadesCompartidas.ajustaLongitud(modelo, 20);
		this.modelo=modelo;
		
		if(precio<0){
			System.out.println("El precio no puede ser menor que 0.");
			System.out.println("Capitalista!, Opresor!, Falocentrista!");
			System.out.println("(bien dicho Marta)");
			precio=1;
		}
		this.precioVenta=precio;
	}
	
	//Copia.No generará nueva id
	public InstrumentoImpl(InstrumentoImpl instrumento){
		this.id=instrumento.getId();
		this.nombre=instrumento.getNombre();
		this.marca=instrumento.getMarca();
		this.descripcion=instrumento.getDescripcion();
		this.modelo=instrumento.getModelo();
		this.precioVenta=instrumento.getPrecioVenta();
	}
	
	
	public int getId() {
		return id;
	}

	
	public int getIdCompartido() {
		return idCompartido;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public Marca getMarca() {
		return marca;
	}

	
	public String getModelo() {
		return modelo;
	}

	
	public double getPrecioVenta() {
		return precioVenta;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setNombre(String nombre) {
		nombre=UtilidadesCompartidas.ajustaLongitud(nombre, 20);
		this.nombre=nombre;
		
	}

	
	public void setMarca(Marca marca) {
		this.marca=marca;
	}

	
	public void setModelo(String modelo) {
		modelo=UtilidadesCompartidas.ajustaLongitud(modelo, 20);
		this.modelo=modelo;
	}

	
	public void setPrecioVenta(double precio) {
		if(precio<0){
			System.out.println("El precio no puede ser menor que 0.");
			System.out.println("Capitalista!, Opresor!, Falocentrista!");
			System.out.println("(bien dicho Marta)");
			precio=1;
		}
		this.precioVenta=precio;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}

	/* 
	 * Interfaz 
	 * Cabecera:public int tomaID(String ruta)
	 * Proceso:Nos tomará un id de un fichero, aumentando el id de ese fichero en 1, y aumentando
	 * 		el atributo estatico IdCompartido en 1
	 * Precondiciones:La clase debe contener un atributo estático llamado idCmopartido.
	 * Entrada:Nada
	 * Salida:1 entero
	 * Entrada/Salida:1 String para la ruta del fichero
	 * Postcondiciones:En caso de no existir ese fichero, lo creará, además siempre que se llama
	 * 					al método, se aumentará en uno el valor de idCompartido,
	 */
	
	public int tomaID(String ruta){
		int id = 1;
		if (idCompartido == 0) {
			try {
				FileInputStream leerID = new FileInputStream(ruta);
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
				System.out.println("Que has hecho que no encuentro el fichero "+ruta);
				System.out.println("Bueno, no pasa nada, lo vuelvo a crear... PERO QUE NO VUELVA A OCURRIR");
			} catch (IOException e) {
				System.out.println(e);
			}
		} else {
			id = idCompartido + 1;
			idCompartido++;
		}
		try {
			FileOutputStream escribeID = new FileOutputStream(ruta);
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
	
	/* 
	 * Interfaz 
	 * Cabecera: boolean equals(Object o)
	 * Proceso: Método que devuelve si un objeto es IGUAL a otro
	 * Precondiciones:Ninguna
	 * Entrada:1 objeto
	 * Salida: 1 booleano
	 * Entrada/Salida:Nada
	 * Postcondiciones:Booleano asociado al nombre, true si los objetos son iguales, false en caso contrario
	 * 					Consideraremos que dos instrumentos son iguales si tienen el mismo id
	 */
	@Override
	public boolean equals(Object o){
		boolean resultado=false;
		if(o!=null && o instanceof InstrumentoImpl){
			InstrumentoImpl ii=(InstrumentoImpl) o;
			resultado=this.id==ii.getId();
		}
		return resultado;
	}
	
	@Override
	public String toString(){
		return (id+" "+nombre+" "+marca+" "+modelo+" "+precioVenta);
	}
	
}
