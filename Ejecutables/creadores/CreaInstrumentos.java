package creadores;
import java.util.Scanner;

import compartidas.UtilidadesCompartidas;
import datos.InstrumentoImpl;
import enums.Marca;
import ficheros.FicheroInstrumento;
public class CreaInstrumentos {

	public static void main(String[] args) {
		FicheroInstrumento fi=new FicheroInstrumento();
		Scanner teclado=new Scanner(System.in);
		String ruta="Ficheros//Instrumentos//Isntrumentos.dat";
		String rutaDescripcion="Ficheros//Instrumentos//Descripcion.txt";
		
		char continuar,desc;
		String nombre,modelo;
		String descripcion="";  //La inicializo a nada por si no quiero escribir descripcion
		int cantidadMarca=Marca.values().length;
		int valorMarca;
		double precio;
		
		System.out.println("Quiere introducir un instrumento? (s para si,cualquier otra letra,no)");
		continuar=teclado.nextLine().charAt(0);
		while(continuar=='s'){
			//Nombre
			do{
				System.out.println("Introduce el nombre (menos de 20 caracteres)");
				nombre=teclado.nextLine();
			}while(nombre.length()>20);
			
			//Marca
			do{
				//Muestra las marcas con un número
				for(int i=0;i<cantidadMarca;i++){
					System.out.print(i+" ");
					System.out.println(Marca.values()[i]);
				}
				
				System.out.println("Introduce el número de la marca");
				valorMarca=Integer.parseInt(teclado.nextLine());
			}while(valorMarca>cantidadMarca || valorMarca<0);
			
			//Descripcion
			System.out.println("Quiere introducir una descripción? (s para si, cualquier otra letra para no)");
			desc=teclado.nextLine().charAt(0);
			while(desc=='s'){
				System.out.println("Escriba su descripcion: ");
				descripcion=descripcion+teclado.nextLine();
				System.out.println("Desea escribir algo más en la descripcion?");
				desc=teclado.nextLine().charAt(0);
			}
			
			//Modelo
			do{
				System.out.println("Introduce el modelo (menos de 20 caracteres)");
				modelo=teclado.nextLine();
			}while(nombre.length()>20);
			
			//Precio
			do{
				System.out.println("Introduce el precio de venta(mayor que 0)");
				precio=Double.parseDouble(teclado.nextLine());
			}while(precio<0);
			
			InstrumentoImpl generico=new InstrumentoImpl(nombre,Marca.values()[valorMarca], 
					descripcion, modelo, precio);
			fi.guardaInstrumento(ruta,rutaDescripcion, generico);
			
			System.out.println("Quiere introducir un instrumento? (s para si,cualquier otra letra,no)");
			continuar=teclado.nextLine().charAt(0);
		}

	}

}
