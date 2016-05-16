/* ***********
* Propiedades
* ***********
* Basicas:
* Id. entero, consultable
* Marca: cadena, consultable y modificable
* Modelo:cadena,consultable y modificable
* Bobinas:entero,consultable y modificable
* 
* Derivadas:
* 
* 
* Compartidas:
*  idCompartido: entero consultable
* 
* 
* Restricciones:
* Tanto marca como modelo tendrá que ser de máximo 20 caracteres
* Bobinas tomará como valor 1 ó 2;
* 
* 
* ********
* Interfaz
* ********
* Consultores:
* string getMarca();
* string getModelo();
* int getBobinas();
* 
* Modificadores:
* void setMarca(String marca)
* void setModelo(String modelo)
* void setBobinas(int bobinas)
* 
* M�todos a�adidos:
* int tomaId();
* 
* 
* M�todos Sobreescritos:
* String toString();
* int comparteTo(Pastilla p)
*/


package datos;

import java.io.*;

import compartidas.UtilidadesCompartidas;

public class Pastilla implements Serializable,Comparable<Pastilla> {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String marca;
	private String modelo;
	private int bobinas;
	private static int idCompartido=0;
	
	public Pastilla(){
		this.id=tomaID("Ficheros//IdPastillas.dat");
		this.marca="Defecto";
		this.modelo="Defecto";
		this.bobinas=1;
	}
	
	public Pastilla(String marca,String modelo,int bobinas){
		this();
		
		marca=UtilidadesCompartidas.ajustaLongitud(marca, 20);
		this.marca=marca;
		
		modelo=UtilidadesCompartidas.ajustaLongitud(modelo, 20);
		this.modelo=modelo;
		
		if(bobinas<1 || bobinas>3){
			System.out.println("Error,no puedes tener menos de 1 bobina o más de 3");
			bobinas=1;
			System.out.println("se guadará: "+bobinas);
		}
		this.bobinas=bobinas;
	}
	
	public Pastilla(int id,String marca,String modelo,int bobinas){
		this.id=id;
		
		marca=UtilidadesCompartidas.ajustaLongitud(marca, 20);
		this.marca=marca;
		
		modelo=UtilidadesCompartidas.ajustaLongitud(modelo, 20);
		this.modelo=modelo;
		
		if(bobinas<1 || bobinas>3){
			System.out.println("Error,no puedes tener menos de 1 bobina o más de 3");
			bobinas=1;
			System.out.println("se guadará: "+bobinas);
		}
		this.bobinas=bobinas;
	}
	
	public int getId(){
		return id;
	}
	
	public String getMarca(){
		return marca;
	}
	
	public String getModelo(){
		return modelo;
	}
	
	public int getBobinas(){
		return bobinas;
	}
	
	public void setMarca(String marca){
		marca=UtilidadesCompartidas.ajustaLongitud(marca, 20);
		this.marca=marca;
	}
	
	public void setModelo(String modelo){
		modelo=UtilidadesCompartidas.ajustaLongitud(modelo, 20);
		this.modelo=modelo;
	}
	
	public void setBobinas(int bobinas){
		if(bobinas<1 || bobinas>3){
			System.out.println("Error,no puedes tener menos de 1 bobina o más de 3");
			bobinas=1;
			System.out.println("se guadará: "+bobinas);
		}
		this.bobinas=bobinas;
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
	
	public String toString(){
		return (this.id+" "+this.marca+" "+this.modelo+" "+this.bobinas);
	}

	/* 
	 * Interfaz 
	 * Cabecera: int compareTo(pastilla comparada)
	 * Proceso Método que compara si un dos pastillas son IGUALES
	 * Precondiciones:Nada
	 * Entrada:1 pastilla
	 * Salida:1 Entero
	 * Entrada/Salida:Nada
	 * Postcondiciones:Devuelve 1 si la pastilla que usa el método es mayor que la pastilla que paso por parametro
	 * 0 si son iguales, -1 en caso contrario.Una pastilla será mayor que otra si su id es mayor.
	 */
	
	@Override
	public int compareTo(Pastilla p) {
		int devolver=0;
		if(this.id>p.getId()){
			devolver=1;
		}else{
			if(this.id<p.getId()){
				devolver=-1;
			}
		}
		return devolver;
	}
}
