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

	private static Scanner teclado=new Scanner(System.in);
	
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
		System.out.println("10.Mostrar Menú Clientes");
		System.out.println("11.Actualiza base de Datos");
		System.out.println("12.Muestra Menú Empleados");
		System.out.println("0.Salir");
	}
	
	public static void menuInstrumentos(){
		System.out.println("Desea ver un instrumento en concreto?: ");
		System.out.println("1.Ver instrumento de viento");
		System.out.println("2.Ver instrumento de percusion");
		System.out.println("3.Ver instrumento de cuerda");
		System.out.println("4.Ver saxofon");
		System.out.println("5.Ver guitarra electrica");
		System.out.println("0.Salir");
	}
	
	/*
	 * Por cuestión de legibilidad creo este método que crea un isntrumento.
	 * No tiene ninguna entrada, y como salida un instrumento
	 * Se que no es lo más correcto, pero es por legibilidad
	 */
	
	public static void menuClientes(){
		System.out.println("Seleccione una opción: ");
		System.out.println("1.Muestra la información de todos los clientes");
		System.out.println("2.Muestra la información de un cliente(insertando dni)");
		System.out.println("3.Muestra las compras de un cliente(insertando dni)");
		System.out.println("0.Salir");
	}
	
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
	
	/* 
	 * Interfaz 
	 * Cabecera:public static VientoImpl creaViento(IntrumentoImpl i)
	 * Proceso:Método que crea lee de teclado un instrumento de viento y lo crea
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento
	 * Salida:1 instrumento de viento
	 * Entrada/Salida:Nada
	 * Postcondiciones:Método para facilitar la legibilidad!
	 */
	
	public static VientoImpl creaViento(InstrumentoImpl i){
		char afinacion;
		String tesitura;
		byte boquilla;
		VientoImpl viento;
		do{
			System.out.println("Introduce la afinacion: ");
			afinacion=teclado.nextLine().charAt(0);
		}while((int) afinacion < 65 || (int) afinacion > 71 && (int) afinacion < 97 || (int) afinacion > 103);
		
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
		viento=new VientoImpl(i, afinacion, tesitura, boquilla);
		
		return viento;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static PercusionImpl creaPercusion(IntrumentoImpl i)
	 * Proceso:Método que crea lee de teclado un instrumento de percusion y lo crea
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento
	 * Salida:1 instrumento de percusion
	 * Entrada/Salida:Nada
	 * Postcondiciones:Método para facilitar la legibilidad!
	 */
	
	public static PercusionImpl creaPercusion(InstrumentoImpl i){
		char afinacion;
		String material;
		boolean accesorio;
		char acces;
		PercusionImpl percusion;
		do{
			System.out.println("Introduce la afinacion: ");
			afinacion=teclado.nextLine().charAt(0);
		}while((int) afinacion < 65 || (int) afinacion > 71 && (int) afinacion < 97 || (int) afinacion > 103);
		
		//material
		do{
			System.out.println("Introduce el material (menos de 20 caracteres)");
			material=teclado.nextLine();
		}while(material.length()>20);
		
		//Accesorios
		do{
			System.out.println("Introduce si tiene accesorio (s para si, n para no)");
			acces=teclado.nextLine().charAt(0);
		}while(acces!='s' && acces!='n');
			accesorio=acces=='s';
		percusion=new PercusionImpl(i, afinacion,material,accesorio);
		
		return percusion;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static CuerdaImpl creaCuerda(IntrumentoImpl i)
	 * Proceso:Método que crea lee de teclado un instrumento de cuerda y lo crea
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento
	 * Salida:1 instrumento de cuerda
	 * Entrada/Salida:Nada
	 * Postcondiciones:Método para facilitar la legibilidad!
	 */
	
	public static CuerdaImpl creaCuerda(InstrumentoImpl i){
		int numeroCuerdas;
		String registro;
		byte tipoCuerda;
		CuerdaImpl cuerda;
		//Numero de cuerdas
		do{
			System.out.println("Introduce el número de cuerdas: ");
			numeroCuerdas=Integer.parseInt(teclado.nextLine());
		}while(numeroCuerdas<1);
		System.out.println("Buena condicion");
		
		//Registro
		do{
			System.out.println("Introduce el registro (menos de 20 caracteres)");
			registro=teclado.nextLine();
		}while(registro.length()>20);
		
		//Tipo de cuerda
		do{
			System.out.println("Introduce el tipo de cuerda (0 para cuerda Frotada, 1 para Cuerda Percutida)");
			tipoCuerda=Byte.parseByte(teclado.nextLine());
		}while(tipoCuerda<0 || tipoCuerda>1);
		cuerda=new CuerdaImpl(i, numeroCuerdas,registro, tipoCuerda);
		
		return cuerda;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static SaxofonImpl creaSaxofon(VientoImpl v)
	 * Proceso:Método que lee de teclado un instrumento de saxofon y lo crea
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento de viento
	 * Salida:1 instrumento de saxofon
	 * Entrada/Salida:Nada
	 * Postcondiciones:Método para facilitar la legibilidad!
	 */
	
	public static SaxofonImpl creaSaxofon(VientoImpl v) {
		String boquillaSaxo, material;
		int cantidadFamilia, cantidadAcabado;
		int valorFamilia, valorAcabado;
		SaxofonImpl saxofon;
		//Familia
		cantidadFamilia=FamiliaSaxo.values().length;
		do {
			//Muestra la familia con un número
			for (int i = 0; i < cantidadFamilia; i++) {
				System.out.print(i + " ");
				System.out.println(FamiliaSaxo.values()[i]);
			}

			System.out.println("Introduce el número de la familia");
			valorFamilia = Integer.parseInt(teclado.nextLine());
		} while (valorFamilia > cantidadFamilia || valorFamilia < 0);

		//Boquilla saxo
		do {
			System.out.println("Introduce la boquilla del saxo (menos de 20 caracteres)");
			boquillaSaxo = teclado.nextLine();
		} while (boquillaSaxo.length() > 20);

		//material
		do {
			System.out.println("Introduce el material (0 para Madera, 1 para Metal)");
			material = teclado.nextLine();
		} while (material.length() > 20);

		//Acabado
		cantidadAcabado=Acabado.values().length;
		do {
			// Muestra los acabados con un número
			for (int i = 0; i < cantidadAcabado; i++) {
				System.out.print(i + " ");
				System.out.println(Acabado.values()[i]);
			}

			System.out.println("Introduce el número del acabado");
			valorAcabado = Integer.parseInt(teclado.nextLine());
		} while (valorAcabado > cantidadAcabado || valorAcabado < 0);

		saxofon = new SaxofonImpl(v, FamiliaSaxo.values()[valorFamilia],boquillaSaxo,material,Acabado.values()[valorAcabado]);

		return saxofon;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static GuitarraElectricaImpl creaGuitarraElectrica(CuerdaImpl c,String rutaPastillas)
	 * Proceso:Método que lee de teclado una guitarra y la crea
	 * Precondiciones:Ninguna
	 * Entrada:1 instrumento de cuerda
	 * 			1 cadena para la ruta de las pastillas
	 * Salida:1 guitarra electrica
	 * Entrada/Salida:Nada
	 * Postcondiciones:Método para facilitar la legibilidad!
	 */
	
	public static GuitarraElectricaImpl creaGuitarraElectrica(CuerdaImpl c,String rutaPastillas){
		int cantidadTipo,valorTipo;
		int controles,numPastillas,idPastilla;
		int[] arrayPastillas;
		char puenteC;
		boolean puente;
		GestionadoraPrincipal gp=new GestionadoraPrincipal();
		GuitarraElectricaImpl guitarra;
		FicheroPastillas fp=new FicheroPastillas();
		//Tipo
				cantidadTipo=Tipo.values().length;
				do {
					//Muestra la familia con un número
					for (int i = 0; i < cantidadTipo; i++) {
						System.out.print(i + " ");
						System.out.println(Tipo.values()[i]);
					}

					System.out.println("Introduce el número del tipo");
					valorTipo = Integer.parseInt(teclado.nextLine());
				} while (valorTipo > cantidadTipo || valorTipo < 0);
		
		//Pastillas
		//Pedimos el número de pastillas que va a tener la guitarra
		do{
			System.out.println("Introduce el número de Pastillas (entre 1 y 3)");
			numPastillas=Integer.parseInt(teclado.nextLine());
		}while(numPastillas<1 || numPastillas>3);
		arrayPastillas=new int[numPastillas];
		
		
		for(int i=0;i<numPastillas;i++){
			fp.muestraPastillas(rutaPastillas);
			do{
				System.out.println("Introduce el id de la Pastilla");
				idPastilla=Integer.parseInt(teclado.nextLine());
				//Comprobamos la existencia de la pastilla:
				if(!gp.compruebaExistenciaPastilla(rutaPastillas, idPastilla)){
					System.out.println("Error, introduce un id válido");
				}
			}while(!gp.compruebaExistenciaPastilla(rutaPastillas, idPastilla));
			arrayPastillas[i]=idPastilla;
		}
		//puente
		do{
			System.out.println("Introduce si tiene puente flotante (s para si, n para no)");
			puenteC=teclado.nextLine().charAt(0);
		}while(puenteC!='s' && puenteC!='n');
		puente=puenteC=='s';
			
		//controles
		do{
			System.out.println("Introduce la boquilla (0 para Madera, 1 para Metal)");
			controles=Integer.parseInt(teclado.nextLine());
		}while(controles<1 || controles>4);
			
		guitarra=new GuitarraElectricaImpl(c,Tipo.values()[valorTipo],arrayPastillas,puente,controles);
		
		return guitarra;
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
		boolean estadoBaseDatos=false;

		do {
			try {
				menuPrincipal();
				opcionMenuPrincipal = Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Introduzca un número, por favor");
				opcionMenuPrincipal = -1;
			}
		} while (opcionMenuPrincipal < 0 || opcionMenuPrincipal > 12);
		
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
					System.out.println("0.Salir");
					try{
						tipoInstrumento=Integer.parseInt(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduzca un número, por favor");
						tipoInstrumento=0;
					}
				}while(tipoInstrumento<0 || tipoInstrumento>6);
				
				while(tipoInstrumento!=0){
					instrumento=creaInstrumento();
					fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
					//No hay forma de no repetir la creación de cuerda y/o viento. Explicar!
					switch (tipoInstrumento){
					case 1:
						break;
					case 2:
						//Viento
						viento=creaViento(instrumento);
						fv.guardaInstrumentoViento(rutaViento, viento);
						break;
					case 3:
						//Percusion
						percusion=creaPercusion(instrumento);
						fPer.guardaInstrumentoPerc(rutaPercusion, percusion);
						break;
					case 4:
						//Cuerda
						cuerda=creaCuerda(instrumento);
						fCuerda.guardaInstrumentoCuerda(rutaCuerda, cuerda);
						break;
					case 5:
						//Saxofon
						viento=creaViento(instrumento);
						fv.guardaInstrumentoViento(rutaViento, viento);
						saxofon=creaSaxofon(viento);
						fs.guardaInstrumentoSaxofon(rutaSaxofones, saxofon);
						break;
					case 6:
						//Guitarra
						cuerda=creaCuerda(instrumento);
						fCuerda.guardaInstrumentoCuerda(rutaCuerda, cuerda);
						guitarra=creaGuitarraElectrica(cuerda, rutaPastillasGuitarras);
						fg.guardaInstrumentoGuitarra(rutaGuitarra, rutaPastillasGuitarras, guitarra);
						break;
					}
					
					do{
						System.out.println("Qué tipo de instrumento quieres guardar?");
						System.out.println("1.Genérico");
						System.out.println("2.Viento");
						System.out.println("3.Percusion");
						System.out.println("4.Cuerda");
						System.out.println("5.Saxofon");
						System.out.println("6.Guitarra Electrica");
						System.out.println("0.Salir");
						try{
							tipoInstrumento=Integer.parseInt(teclado.nextLine());
						}catch(NumberFormatException e){
							System.out.println("Introduzca un número, por favor");
							tipoInstrumento=0;
						}
					}while(tipoInstrumento<0 || tipoInstrumento>6);
				}
				break;
			case 2:
				//Modificar Instrumento
				System.out.println("En construccion");
				break;
			case 3:
				//Eliminar Instrumento
				System.out.println("En construccion");
				break;
			case 4:
				//Añadir Cliente
				System.out.println("En construccion");
				break;
			case 5:
				//Modificar Cliente
				System.out.println("En construccion");
				break;
			case 6:
				//Eliminar Cliente
				System.out.println("En construccion");
				break;
			case 7:
				//Realizar Venta
				System.out.println("En construccion");
				break;
			case 8:
				//Realizar Devolución
				System.out.println("En construccion");
				break;
			case 9:
				//Muestra Instrumentos
				System.out.println("Id"+"  "+"Nombre     "+"Marca  "+"Modelo  "+"Precio de Venta");
				fi.muestraInstrumentos(rutaInstrumentos);
				
				//Muestra menú instrumentos
				do{
					menuInstrumentos();
					opcionMenuInstrumentos=Integer.parseInt(teclado.nextLine());
				}while(opcionMenuInstrumentos<0 || opcionMenuInstrumentos>5);
				
				while(opcionMenuInstrumentos!=0){
					System.out.println("Introduce la id: ");
					idInstrumento=Integer.parseInt(teclado.nextLine());
					switch (opcionMenuInstrumentos) {
					case 1:
						if (gp.compruebaExistenciaViento(rutaViento, idInstrumento)) {
							System.out.println(fv.devuelveInstrumentoViento(rutaViento, rutaInstrumentos, idInstrumento));
								System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 2:
						if (gp.compruebaExistenciaPercusion(rutaPercusion, idInstrumento)) {
							System.out.println(fPer.devuelveInstrumentoPercusion(rutaPercusion, rutaInstrumentos, idInstrumento));
							System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 3:
						if (gp.compruebaExistenciaCuerda(rutaCuerda, idInstrumento)) {
							System.out.println(fCuerda.devuelveInstrumentoCuerda(rutaCuerda, rutaInstrumentos, idInstrumento));
							System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 4:
						if (gp.compruebaExistenciaSaxofon(rutaSaxofones, idInstrumento)) {
							System.out.println(fs.devuelveInstrumentoSaxofon(rutaSaxofones, rutaViento,
									rutaInstrumentos, idInstrumento));
							System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 5:
						if (gp.compruebaExistenciaGuitarra(rutaGuitarra, idInstrumento)) {
							System.out.println(fg.devuelveInstrumentoGuitarra(rutaGuitarra, rutaCuerda,
									rutaPastillasGuitarras, rutaInstrumentos, idInstrumento));
							System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					}
					//Muestra menú instrumentos
					do{
						menuInstrumentos();
						opcionMenuInstrumentos=Integer.parseInt(teclado.nextLine());
					}while(opcionMenuInstrumentos<0 || opcionMenuInstrumentos>5);
				}
				break;
			case 10:
				//Mostrar Clientes
				System.out.println();
				if(!estadoBaseDatos){
					System.out.println("Atención,la información de los siguientes clientes no están actualizados");
					fc.muestraClientes(rutaClientes,rutaCompras);
					System.out.println();
					System.out.println("Si desea ver las compras de un cliente, introduzca el dni del cliente");
					System.out.println("En caso contrario");
				}
				break;
			case 11:
				//Actualizar Base de datos
				System.out.println("En construcción");
				break;
			case 12:
				//Mostrar Menú Empleados
				/*
				 * Este lo hago aparte porque modificar empleados es menos común
				 */
				System.out.println("En construcción");
				break;
			}
			do{
				menuPrincipal();
				opcionMenuPrincipal=Integer.parseInt(teclado.nextLine());
			}while(opcionMenuPrincipal<0 || opcionMenuPrincipal>12);
		}
		teclado.close();
	}

}
