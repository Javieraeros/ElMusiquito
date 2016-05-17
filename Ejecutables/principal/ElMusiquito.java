/*
 * Pseudocódigo:
 * Inicio
 * 	mostarMenoLeerValidadOpcion
 * 	mientras(opcion diferente de 0)
 * 	segun opcion
 * 		caso1:
 * 			Añadir Instrumento
 * 		caso2:
 * 			ModificarInstrumento
 * 		caso3:
 * 			Eliminar Instrumento
 * 		caso4:
 * 			Añadir Cliente
 * 		caso5:
 * 			Modificar Cliente
 * 		caso6:
 * 			Eliminar Cliente
 * 		caso7:
 * 			Realizar Venta
 * 		caso8:
 * 			Realizar Devolucion
 * 		caso9:
 * 			Mostrar Instrumentos
 * 		caso10:
 * 			Mostrar Clientes
 * 	Fin_Segun
 * 	mostrarMenuLeeryValidarOpcion
 * 	Fin_Mientras
 * Fin
 */

package principal;

import java.util.*;
import datos.*;
import enums.*;
import ficheros.*;

public class ElMusiquito {

	static Scanner teclado=new Scanner(System.in);
	
	public static void menuPrincipal(){
		System.out.println("Seleccione una opción: ");
		System.out.println("1.Añadir Instrumento");
		System.out.println("2.Modificar Instrumento");
		System.out.println("3.Eliminar Instrumento");
		System.out.println("4.Añadir Cliente");
		System.out.println("5.Modificar Cliente");
		System.out.println("6.Eliminar Cliente");
		System.out.println("7.Realizar Venta");
		System.out.println("8.Realizar Devolución");
		System.out.println("9.Mostrar Instrumentos");
		System.out.println("10.Mostrar Clientes");
		System.out.println("0.Salir");
	}
	
	public static void menuInstrumentos(){
		System.out.println("Seleccione una opción: ");
		System.out.println("1.Ver 1 instrumento de viento");
		System.out.println("2.Ver 1 instrumento de percusion");
		System.out.println("3.Ver 1 instrumento de cuerda");
		System.out.println("4.Ver 1 saxofon");
		System.out.println("5.Ver 1 guitarra electrica");
		System.out.println("0.Salir");
	}
	
	/*
	 * Por cuestión de legibilidad creo este método que crea un isntrumento.
	 * No tiene ninguna entrada, y como salida un instrumento
	 * Se que no es lo más correcto, pero es por legibilidad
	 */
	
	public static InstrumentoImpl creaInstrumento() {
		char desc;
		String nombre, modelo;
		String descripcion = ""; // La inicializo a nada por si no quiero
									// escribir descripcion
		int cantidadMarca = Marca.values().length;
		int valorMarca=-1;
		double precio=-1.00;

		// Nombre
		do {
			System.out.println("Introduce el nombre (menos de 20 caracteres)");
			nombre = teclado.nextLine();
		} while (nombre.length() > 20);

		// Marca
		do {
			// Muestra las marcas con un número
			for (int i = 0; i < cantidadMarca; i++) {
				System.out.print(i + " ");
				System.out.println(Marca.values()[i]);
			}

			System.out.println("Introduce el número de la marca");
			try{
				valorMarca = Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número!");
			}
		} while (valorMarca >= cantidadMarca || valorMarca < 0);

		// Descripcion
		System.out.println("Quiere introducir una descripción? (s para si, cualquier otra letra para no)");
		desc = teclado.nextLine().charAt(0);
		while (desc == 's') {
			System.out.println("Escriba su descripcion: ");
			descripcion = descripcion + teclado.nextLine();
			System.out.println("Desea escribir algo más en la descripcion?");
			desc = teclado.nextLine().charAt(0);
		}

		// Modelo
		do {
			System.out.println("Introduce el modelo (menos de 20 caracteres)");
			modelo = teclado.nextLine();
		} while (nombre.length() > 20);

		// Precio
		do {
			System.out.println("Introduce el precio de venta(mayor que 0)");
			try {
				precio = Double.parseDouble(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Introduce un número!");
			}
		} while (precio <= 0);

		InstrumentoImpl devolver = new InstrumentoImpl(nombre, Marca.values()[valorMarca], descripcion, modelo, precio);
		return devolver;
	}
	
	public static void main(String[] args) {
		
		String rutaPersonas="Ficheros//Personas//Personas.dat";
		String rutaClientes="Ficheros//Clientes//Clientes.dat";
		String rutaCompras="Ficheros//Clientes//Compras.dat";
		String rutaEmpleados="Ficheros//Empleados//Empleados.dat";
		
		String rutaInstrumentos="Ficheros//Instrumentos//Instrumentos.dat";
		String rutaDescripcion="Ficheros//Instrumentos//Descripcion.txt";
		String rutaViento="Ficheros//Viento//Viento.dat";
		String rutaSaxofones="Ficheros//Saxofon//Saxofon.dat";
		String rutaPercusion="Ficheros//Percusion//Percusion.dat";
		String rutaCuerda="Ficheros//Cuerda//Cuerda.dat";
		String rutaGuitarra="Ficheros//Guitarra//Guitarra.dat";
		String rutaPastillas="Ficheros//Pastillas//Pastillas.dat";
		String rutaPastillasGuitarras="Ficheros//Guitarra//Relacion.dat";
		String rutaopcional="Ficheros";
		
		PersonaImpl persona;
		ClienteImpl cliente;
		EmpleadoImpl empleado;
		
		InstrumentoImpl instrumento;
		VientoImpl viento;
		PercusionImpl percusion;
		CuerdaImpl cuerda;
		SaxofonImpl saxofon;
		GuitarraElectricaImpl guitarra;
		Vector<InstrumentoImpl> vector;
		
		GestionadoraPrincipal gp=new GestionadoraPrincipal();
		
		FicheroPersona fp=new FicheroPersona();
		FicheroCliente fc=new FicheroCliente();
		FicheroEmpleado fe=new FicheroEmpleado();
		
		FicheroInstrumento fi=new FicheroInstrumento();
		FicheroViento fv =new FicheroViento();
		FicheroPercusion fPer=new FicheroPercusion();
		FicheroCuerda fCuerda=new FicheroCuerda();
		FicheroSaxofon fs=new FicheroSaxofon();
		FicheroGuitarra fg=new FicheroGuitarra();
		FicheroPastillas fPas=new FicheroPastillas();
		
		int opcionMenuPrincipal,opcionMenuInstrumentos;
		int idInstrumento,tipoInstrumento;

		do {
			try {
				menuPrincipal();
				opcionMenuPrincipal = Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Introduzca un número, por favor");
				opcionMenuPrincipal = -1;
			}
		} while (opcionMenuPrincipal < 0 || opcionMenuPrincipal > 9);
		
		while(opcionMenuPrincipal!=0){
			switch (opcionMenuPrincipal) {
			case 1:
				do{
					System.out.println("Qué tipo de instrumento quieres guardar?");
					System.out.println("1.Genérico");
					System.out.println("2.Viento");
					System.out.println("3.Percusion");
					System.out.println("4.Cuerda");
					System.out.println("5.Saxofon");
					System.out.println("6.Guitarra Electrica");
					try{
						tipoInstrumento=Integer.parseInt(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduzca un número, por favor");
						tipoInstrumento=0;
					}
				}while(tipoInstrumento<1 || tipoInstrumento>6);
				instrumento=creaInstrumento();
				fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
				switch (tipoInstrumento){
				case 1:
					break;
				case 2:
					//Viento
					char afinacion;
					String tesitura;
					byte boquilla;
					do{
						System.out.println("Introduce la afinacion: ");
						afinacion=teclado.nextLine().charAt(0);
					}while((int) afinacion < 65 || (int) afinacion > 71 && (int) afinacion < 97 || (int) afinacion > 103);
					System.out.println("Buena condicion");
					
					//Tesitura
					do{
						System.out.println("Introduce la tesitura (menos de 20 caracteres)");
						tesitura=teclado.nextLine();
					}while(tesitura.length()>20);
					
					//boquilla
					do{
						System.out.println("Introduce la boquilla (0 para Madera, 1 para Metal)");
						boquilla=Byte.parseByte(teclado.nextLine());
					}while(boquilla<0 || boquilla>1);
					viento=new VientoImpl(instrumento, afinacion, tesitura, boquilla);
					fv.guardaInstrumentoViento(rutaViento, viento);
					break;
				case 3:
					//Percusion
					break;
				case 4:
					//Cuerda
					break;
				case 5:
					//Saxofon
					break;
				case 6:
					//Guitarra
					break;
				}
				break;
			case 2:
				System.out.println("En construccion");
				break;
			case 3:
				System.out.println("En construccion");
				break;
			case 4:
				System.out.println("En construccion");
				break;
			case 5:
				System.out.println("En construccion");
				break;
			case 6:
				System.out.println("En construccion");
				break;
			case 7:
				System.out.println("En construccion");
				break;
			case 8:
				System.out.println("En construccion");
				break;
			case 9:
				//Muestra Instrumentos
				System.out.println("Id"+"  "+"Nombre     "+"Marca  "+"Modelo  "+"Descripcion"+"Precio de Venta");
				fi.muestraInstrumentos(rutaInstrumentos);
				
				//Descripcion
				System.out.println("Quiere ver la descripción de algún instrumento? (0 para no, id para ver la descripción)");
				idInstrumento=Integer.parseInt(teclado.nextLine());
				if(gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento)){
					System.out.println(fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
				}else{
					System.out.println("Has introducido un instrumento que no tiene descripcion");
				}
				
				do{
					menuInstrumentos();
					opcionMenuInstrumentos=Integer.parseInt(teclado.nextLine());
				}while(opcionMenuInstrumentos<0 || opcionMenuInstrumentos>5);
				if(opcionMenuInstrumentos!=0){
					System.out.println("Introduce la id: ");
					idInstrumento=Integer.parseInt(teclado.nextLine());
				}
				switch (opcionMenuInstrumentos) {
				case 1:
					if (gp.compruebaExistenciaViento(rutaViento, idInstrumento)) {
						System.out.println(fv.devuelveInstrumentoViento(rutaViento, rutaInstrumentos, idInstrumento));
					} else {
						System.out.println("Error,id inválido!");
					}
					break;
				case 2:
					if (gp.compruebaExistenciaPercusion(rutaPercusion, idInstrumento)) {
						System.out.println(fPer.devuelveInstrumentoPercusion(rutaPercusion, rutaInstrumentos, idInstrumento));
					} else {
						System.out.println("Error,id inválido!");
					}
					break;
				case 3:
					if (gp.compruebaExistenciaCuerda(rutaCuerda, idInstrumento)) {
						System.out.println(fCuerda.devuelveInstrumentoCuerda(rutaCuerda, rutaInstrumentos, idInstrumento));
					} else {
						System.out.println("Error,id inválido!");
					}
					break;
				case 4:
					if (gp.compruebaExistenciaSaxofon(rutaSaxofones, idInstrumento)) {
						System.out.println(fs.devuelveInstrumentoSaxofon(rutaSaxofones,rutaViento, rutaInstrumentos, idInstrumento));
					} else {
						System.out.println("Error,id inválido!");
					}
					break;
				case 5:
					if (gp.compruebaExistenciaGuitarra(rutaGuitarra, idInstrumento)) {
						System.out.println(fg.devuelveInstrumentoGuitarra(rutaGuitarra,rutaCuerda,rutaPastillasGuitarras,
								rutaInstrumentos, idInstrumento));
					} else {
						System.out.println("Error,id inválido!");
					}
					break;
				}
				
				break;
			case 10:
				System.out.println("En construccion");
				break;

			}
			do{
				menuPrincipal();
				opcionMenuPrincipal=Integer.parseInt(teclado.nextLine());
			}while(opcionMenuPrincipal<0 || opcionMenuPrincipal>10);
		}
		teclado.close();
	}

}
