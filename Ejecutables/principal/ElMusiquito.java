/*
 * Persona eliminada= nombre vacío
 * Cliente eliminado= Dirección vacía
 * Empleado eliminado= Tienda 0
 * 
 * 
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
import ficherosGes.*;

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
		System.out.println("11.Actualizar base de Datos");
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
		System.out.println("6.Ver descripción genérico");
		System.out.println("7.Ver Pastillas");
		System.out.println("0.Salir");
	}
	
	public static void guardaInstrumentoMenu(){
		System.out.println("Qué tipo de instrumento quieres guardar?");
		System.out.println("1.Genérico");
		System.out.println("2.Viento");
		System.out.println("3.Percusion");
		System.out.println("4.Cuerda");
		System.out.println("5.Saxofon");
		System.out.println("6.Guitarra Electrica");
		System.out.println("7.Pastilla Guitarra");
		System.out.println("0.Salir");
	}
	
	public static void modificaInstrumentoMenu(){
		System.out.println("Qué tipo de instrumento quieres modificar?");
		System.out.println("1.Genérico");
		System.out.println("2.Viento");
		System.out.println("3.Percusion");
		System.out.println("4.Cuerda");
		System.out.println("5.Saxofon");
		System.out.println("6.Guitarra Electrica");
		System.out.println("7.Pastilla Guitarra");
		System.out.println("0.Salir");
	}

	public static void menuClientes(){
		System.out.println("Seleccione una opción: ");
		System.out.println("1.Muestra la información de todos los clientes");
		System.out.println("2.Muestra la información de un cliente(insertando dni)");
		System.out.println("3.Muestra las compras de un cliente(insertando dni)");
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
		int valorMarca;
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
				valorMarca=-1;
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
			if(desc=='s'){
				descripcion=descripcion+"\n";
			}
		}

		// Modelo
		do {
			System.out.println("Introduce el modelo (menos de 20 caracteres)");
			modelo = teclado.nextLine();
		} while (modelo.length() > 20);

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
			System.out.println("Introduce el material (máximo 20 caracteres)");
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
			System.out.println("Introduce el número de controles(1/4)");
			controles=Integer.parseInt(teclado.nextLine());
		}while(controles<1 || controles>4);
			
		guitarra=new GuitarraElectricaImpl(c,Tipo.values()[valorTipo],arrayPastillas,puente,controles);
		
		return guitarra;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static Pastilla creaPastilla()
	 * Proceso:Método que lee de teclado los datos de uan pastilla y la crea
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:1 pastilla
	 * Entrada/Salida:Nada
	 * Postcondiciones:Método para facilitar la legibilidad!
	 */
	
	public static Pastilla creaPastilla(){
		Pastilla pas=null;
		String marca,modelo;
		int bobinas;
		
		//Marca
		do{
			System.out.println("Introduce la marca (menos de 20 caracteres)");
			marca=teclado.nextLine();
		}while(marca.length()>20);
		
		//Modelo
		do{
			System.out.println("Introduce el modelo (menos de 20 caracteres)");
			modelo=teclado.nextLine();
		}while(modelo.length()>20);
		
		//Bobinas
		do{
			System.out.println("Introduce el número de bobinas(de 1 a 3)");
			bobinas=Integer.parseInt(teclado.nextLine());
		}while(bobinas<1 || bobinas>3);
		
		pas=new Pastilla(marca, modelo, bobinas);
		return pas;
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaInstrumento(IsntrumentoImpl modificar)
	 * Proceso:Método que modifica algúno de los datos de un instrumento
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 Instrumento
	 * Postcondiciones:Método para facilitar la legibilidad!, el instrumento quedará modificado
	 */
	
	public static void modificaInstrumento(InstrumentoImpl modificar){
		int opcion,cantidadMarca,valorMarca;
		String nombre,descripcion,modelo;
		char desc;
		double precio;
		do{	
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Nombre");
			System.out.println("2.Marca");
			System.out.println("3.Descripcion");
			System.out.println("4.Modelo");
			System.out.println("5.Precio de Venta");
			System.out.println("0.Salir");
			try{
				opcion=Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número, por favor");
				opcion=-1;
			}
		}while(opcion<0 || opcion>5);
		while(opcion!=0){
			switch (opcion){
			case 1:
				// Nombre
				do {
					System.out.println("Introduce el nombre (menos de 20 caracteres)");
					nombre = teclado.nextLine();
				} while (nombre.length() > 20);
				
				modificar.setNombre(nombre);
				break;
			case 2:
				// Marca
				cantidadMarca=Marca.values().length;
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
						valorMarca=-1;
					}
				} while (valorMarca >= cantidadMarca || valorMarca < 0);
				modificar.setMarca(Marca.values()[valorMarca]);
				break;
			case 3:
				// Descripcion
				descripcion="";
				System.out.println("Quiere introducir una descripción? (s para si, cualquier otra letra para no)");
				desc = teclado.nextLine().charAt(0);
				while (desc == 's') {
					System.out.println("Escriba su descripcion: ");
					descripcion = descripcion + teclado.nextLine();
					System.out.println("Desea escribir algo más en la descripcion?");
					desc = teclado.nextLine().charAt(0);
				}
				modificar.setDescripcion(descripcion);
				break;
			case 4:
				// Modelo
				do {
					System.out.println("Introduce el modelo (menos de 20 caracteres)");
					modelo = teclado.nextLine();
				} while (modelo.length() > 20);
				modificar.setModelo(modelo);
				break;
			case 5:
				// Precio
				do {
					System.out.println("Introduce el precio de venta(mayor que 0)");
					try {
						precio = Double.parseDouble(teclado.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Introduce un número!");
						precio=-1;
					}
				} while (precio <= 0);
				modificar.setPrecioVenta(precio);
				break;
			}
			do{	
				System.out.println("¿Qué desea modificar?");
				System.out.println("1.Nombre");
				System.out.println("2.Marca");
				System.out.println("3.Descripcion");
				System.out.println("4.Modelo");
				System.out.println("5.Precio de Venta");
				System.out.println("0.Salir");
				try{
					opcion=Integer.parseInt(teclado.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Introduce un número, por favor");
					opcion=-1;
				}
			}while(opcion<0 || opcion>5);
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaViento(VientoImpl viento)
	 * Proceso:Método que modifica algúno de los datos de un instrumento de viento
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 Instrumento de viento
	 * Postcondiciones:Método para facilitar la legibilidad!, el instrumento quedará modificado
	 */
	
	public static void modificaViento(VientoImpl modificar){
		int opcion;
		char afinacion;
		String tesitura;
		byte boquilla;
		
		do{	
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Afinación");
			System.out.println("2.Tesitura");
			System.out.println("3.Boquilla");
			System.out.println("0.Salir");
			try{
				opcion=Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número, por favor");
				opcion=-1;
			}
		}while(opcion<0 || opcion>3);
		
		while(opcion!=0){
			switch (opcion){
			case 1:
				//Afinación
				do{
					System.out.println("Introduce la afinacion: ");
					afinacion=teclado.nextLine().charAt(0);
				}while((int) afinacion < 65 || (int) afinacion > 71 && (int) afinacion < 97 || (int) afinacion > 103);
				
				modificar.setAfinacion(afinacion);
				break;
			case 2:
				//Tesitura
				do{
					System.out.println("Introduce la tesitura (menos de 20 caracteres)");
					tesitura=teclado.nextLine();
				}while(tesitura.length()>20);
				
				modificar.setTesitura(tesitura);
				break;
			case 3:
				///boquilla
				do{
					System.out.println("Introduce la boquilla (0 para Madera, 1 para Metal)");
					try{
						boquilla=Byte.parseByte(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduce 0 ó 1, por favor");
						boquilla=(short) -1;
					}
				}while(boquilla<0 || boquilla>1);
				
				modificar.setBoquilla(boquilla);
				break;
			}
			do{	
				System.out.println("¿Qué desea modificar?");
				System.out.println("1.Afinación");
				System.out.println("2.Tesitura");
				System.out.println("3.Boquilla");
				System.out.println("0.Salir");
				try{
					opcion=Integer.parseInt(teclado.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Introduce un número, por favor");
					opcion=-1;
				}
			}while(opcion<0 || opcion>3);
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaPercusion(PercusionImpl percusion)
	 * Proceso:Método que modifica algúno de los datos de un instrumento de percusion
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 Instrumento de percusion
	 * Postcondiciones:Método para facilitar la legibilidad!, el instrumento quedará modificado
	 */
	
	public static void modificaPercusion(PercusionImpl modificar){
		int opcion;
		char afinacion;
		String material;
		boolean accesorio;
		char acces;
		
		do{	
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Afinación");
			System.out.println("2.Material");
			System.out.println("3.Accesorios");
			System.out.println("0.Salir");
			try{
				opcion=Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número, por favor");
				opcion=-1;
			}
		}while(opcion<0 || opcion>3);
		
		while(opcion!=0){
			switch (opcion){
			case 1:
				//Afinación
				do{
					System.out.println("Introduce la afinacion: ");
					afinacion=teclado.nextLine().charAt(0);
				}while((int) afinacion < 65 || (int) afinacion > 71 && (int) afinacion < 97 || (int) afinacion > 103);
				
				modificar.setAfinacion(afinacion);
				break;
			case 2:
				//material
				do{
					System.out.println("Introduce el material (menos de 20 caracteres)");
					material=teclado.nextLine();
				}while(material.length()>20);
				
				
				modificar.setMaterial(material);
				break;
			case 3:
				//Accesorios
				do{
					System.out.println("Introduce si tiene accesorio (s para si, n para no)");
					acces=teclado.nextLine().charAt(0);
				}while(acces!='s' && acces!='n');
					accesorio=acces=='s';
				
				modificar.setAccesorios(accesorio);
				break;
			}
			do{	
				System.out.println("¿Qué desea modificar?");
				System.out.println("1.Afinación");
				System.out.println("2.Material");
				System.out.println("3.Accesorios");
				System.out.println("0.Salir");
				try{
					opcion=Integer.parseInt(teclado.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Introduce un número, por favor");
					opcion=-1;
				}
			}while(opcion<0 || opcion>3);
		}
	}

	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaCuerda(CuerdaImpl modificar)
	 * Proceso:Método que modifica algúno de los datos de un instrumento de cuerda
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 Instrumento de cuerda
	 * Postcondiciones:Método para facilitar la legibilidad!, el instrumento quedará modificado
	 */
	
	public static void modificaCuerda(CuerdaImpl modificar){
		int opcion;
		int numeroCuerdas;
		String registro;
		byte tipoCuerda;
		
		do{	
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Número de Cuerdas");
			System.out.println("2.Registro");
			System.out.println("3.Tipo de Cuerda");
			System.out.println("0.Salir");
			try{
				opcion=Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número, por favor");
				opcion=-1;
			}
		}while(opcion<0 || opcion>3);
		while(opcion!=0){
			switch (opcion){
			case 1:
				//Numero de cuerdas
				do{
					System.out.println("Introduce el número de cuerdas: ");
					numeroCuerdas=Integer.parseInt(teclado.nextLine());
				}while(numeroCuerdas<1);
				System.out.println("Buena condicion");
				
				modificar.setCuerdas(numeroCuerdas);
				break;
			case 2:
				//Registro
				do{
					System.out.println("Introduce el registro (menos de 20 caracteres)");
					registro=teclado.nextLine();
				}while(registro.length()>20);
				
				modificar.setRegistro(registro);
				break;
			case 3:
				//Tipo de cuerda
				do{
					System.out.println("Introduce el tipo de cuerda (0 para cuerda Frotada, 1 para Cuerda Percutida)");
					tipoCuerda=Byte.parseByte(teclado.nextLine());
				}while(tipoCuerda<0 || tipoCuerda>1);
				
				
				modificar.setTipoCuerda(tipoCuerda);
				break;
			}
			do{	
				System.out.println("¿Qué desea modificar?");
				System.out.println("1.Número de Cuerdas");
				System.out.println("2.Registro");
				System.out.println("3.Tipo de Cuerda");
				System.out.println("0.Salir");
				try{
					opcion=Integer.parseInt(teclado.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Introduce un número, por favor");
					opcion=-1;
				}
			}while(opcion<0 || opcion>3);
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaSaxo(SaxofonImpl modificar)
	 * Proceso:Método que modifica algúno de los datos de un saxofon
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 saxofon
	 * Postcondiciones:Método para facilitar la legibilidad!, el instrumento quedará modificado
	 */
	
	public static void modificaSaxo(SaxofonImpl modificar){
		int opcion;
		String boquillaSaxo, material;
		int cantidadFamilia, cantidadAcabado;
		int valorFamilia, valorAcabado;
		
		do{	
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Familia");
			System.out.println("2.Boquilla Saxo");
			System.out.println("3.Material");
			System.out.println("4.Acabado");
			System.out.println("0.Salir");
			try{
				opcion=Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número, por favor");
				opcion=-1;
			}
		}while(opcion<0 || opcion>4);
		while(opcion!=0){
			switch (opcion){
			case 1:
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
				
				modificar.setFamilia(FamiliaSaxo.values()[valorFamilia]);
				break;
			case 2:
				//Boquilla saxo
				do {
					System.out.println("Introduce la boquilla del saxo (menos de 20 caracteres)");
					boquillaSaxo = teclado.nextLine();
				} while (boquillaSaxo.length() > 20);
				
				modificar.setBoquillaSaxo(boquillaSaxo);
				break;
			case 3:
				//material
				do {
					System.out.println("Introduce el material (máximo 20 caracteres)");
					material = teclado.nextLine();
				} while (material.length() > 20);
				
				modificar.setMaterial(material);
				break;
			case 4:
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
				
				modificar.setAcabado(Acabado.values()[valorAcabado]);
				break;
			}
	
			do{	
				System.out.println("¿Qué desea modificar?");
				System.out.println("1.Familia");
				System.out.println("2.Boquilla Saxo");
				System.out.println("3.Material");
				System.out.println("4.Acabado");
				System.out.println("0.Salir");
				try{
					opcion=Integer.parseInt(teclado.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Introduce un número, por favor");
					opcion=-1;
				}
			}while(opcion<0 || opcion>4);
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaguitarra(GuitarraelectricaImpl modificar,String rutaPastillas)
	 * Proceso:Método que modifica algúno de los datos de una guitarra
	 * Precondiciones:Las pastillas que queremos añadir deben de estar en el fichero de pastillas
	 * Entrada:1 cadena para la ruta de las pastillas
	 * Salida:Nada
	 * Entrada/Salida:1 guitarra
	 * Postcondiciones:Método para facilitar la legibilidad!, la guitarra quedará modificada.
	 */
	
	public static void modificaGuitarra(GuitarraElectricaImpl modificar, String rutaPastillas) {
		int opcion;
		int cantidadTipo, valorTipo, numPastillas, controles, idPastilla;
		char puenteC;
		boolean puente;
		int[] arrayPastillas;

		FicheroPastillas fp = new FicheroPastillas();
		GestionadoraPrincipal gp = new GestionadoraPrincipal();

		do {
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Tipo");
			System.out.println("2.Pastillas");
			System.out.println("3.Puente");
			System.out.println("4.Controles");
			System.out.println("0.Salir");
			try {
				opcion = Integer.parseInt(teclado.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Introduce un número, por favor");
				opcion = -1;
			}
		} while (opcion < 0 || opcion > 4);
		while (opcion != 0) {
			switch (opcion) {
			case 1:
				// Tipo
				cantidadTipo = Tipo.values().length;
				do {
					// Muestra la familia con un número
					for (int i = 0; i < cantidadTipo; i++) {
						System.out.print(i + " ");
						System.out.println(Tipo.values()[i]);
					}

					System.out.println("Introduce el número del tipo");
					valorTipo = Integer.parseInt(teclado.nextLine());
				} while (valorTipo > cantidadTipo || valorTipo < 0);

				modificar.setTipo(Tipo.values()[valorTipo]);
				break;
			case 2:
				// Pastillas
				// Pedimos el número de pastillas que va a tener la guitarra
				do {
					System.out.println("Introduce el número de Pastillas (entre 1 y 3)");
					numPastillas = Integer.parseInt(teclado.nextLine());
				} while (numPastillas < 1 || numPastillas > 3);
				arrayPastillas = new int[numPastillas];

				// Pedimos los id de las pastillas
				for (int i = 0; i < numPastillas; i++) {
					fp.muestraPastillas(rutaPastillas);
					do {
						System.out.println("Introduce el id de la Pastilla");
						idPastilla = Integer.parseInt(teclado.nextLine());
						// Comprobamos la existencia de la pastilla:
						if (!gp.compruebaExistenciaPastilla(rutaPastillas, idPastilla)) {
							System.out.println("Error, introduce un id válido");
						}
					} while (!gp.compruebaExistenciaPastilla(rutaPastillas, idPastilla));
					arrayPastillas[i] = idPastilla;
				}
				modificar.setIdPastillas(arrayPastillas);
				break;
			case 3:
				// puente
				do {
					System.out.println("Introduce si tiene puente flotante (s para si, n para no)");
					puenteC = teclado.nextLine().charAt(0);
				} while (puenteC != 's' && puenteC != 'n');
				puente = puenteC == 's';

				modificar.setPuenteFlotante(puente);
				;
				break;
			case 4:
				// controles
				do {
					System.out.println("Introduce el número de controles(1/4)");
					controles = Integer.parseInt(teclado.nextLine());
				} while (controles < 1 || controles > 4);

				modificar.setControles(controles);
				;
				break;
			}
			do {
				System.out.println("¿Qué desea modificar?");
				System.out.println("1.Tipo");
				System.out.println("2.Pastillas");
				System.out.println("3.Puente");
				System.out.println("4.Controles");
				System.out.println("0.Salir");
				try {
					opcion = Integer.parseInt(teclado.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Introduce un número, por favor");
					opcion = -1;
				}
			} while (opcion < 0 || opcion > 4);
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaPastilla(Pastilla modificar)
	 * Proceso:Método que modifica algúno de los datos de una pastilla
	 * Precondiciones:
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 Pastilla
	 * Postcondiciones:Método para facilitar la legibilidad!, el instrumento quedará modificado
	 */
	
	public static void modificaPastilla(Pastilla modificar){
		int opcion;
		String marca,modelo;
		int bobinas;
		
		do{	
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Marca");
			System.out.println("2.Modelo");
			System.out.println("3.Bobinas");
			System.out.println("0.Salir");
			try{
				opcion=Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número, por favor");
				opcion=-1;
			}
		}while(opcion<0 || opcion>3);
		switch (opcion){
		case 1:
			//Marca
			do{
				System.out.println("Introduce la marca (menos de 20 caracteres)");
				marca=teclado.nextLine();
			}while(marca.length()>20);
			
			modificar.setMarca(marca);;
			break;
		case 2:
			//Modelo
			do{
				System.out.println("Introduce el modelo (menos de 20 caracteres)");
				modelo=teclado.nextLine();
			}while(modelo.length()>20);
			
			modificar.setModelo(modelo);
			break;
		case 3:
			//Bobinas
			do{
				System.out.println("Introduce el número de bobinas(de 1 a 3)");
				bobinas=Integer.parseInt(teclado.nextLine());
			}while(bobinas<1 || bobinas>3);
				
			modificar.setBobinas(bobinas);
			break;
		}
	}
	
	
	/* 
	 * Interfaz 
	 * Cabecera:public static void modificaCliente(ClienteImpl modificar)
	 * Proceso:Método que modifica algúno de los datos de un cliente
	 * Precondiciones:
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:1 Cliente
	 * Postcondiciones:Método para facilitar la legibilidad!, el cliente quedará modificado
	 */
	
	public static void modificaCliente(ClienteImpl modificar){
		int opcion;
		String nombre,apellido1,apellido2,correoe,direccion;
		
		do{	
			System.out.println("¿Qué desea modificar?");
			System.out.println("1.Nombre");
			System.out.println("2.Primer Apellido");
			System.out.println("3.Segundo apellido");
			System.out.println("4.Correo electrónico");
			System.out.println("5.Dirección");
			System.out.println("0.Salir");
			try{
				opcion=Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Introduce un número, por favor");
				opcion=-1;
			}
		}while(opcion<0 || opcion>5);
		while(opcion!=0){
			switch (opcion){
			case 1:
				//Nombre
				do {
					System.out.println("Introduce el nombre(menos de 20 caracteres)");
					nombre=teclado.nextLine();
				} while (nombre.length() > 20);
				
				modificar.setNombre(nombre);
				break;
			case 2:
				//Apellido1
				do {
					System.out.println("Introduce el primer apellido(menos de 20 caracteres)");
					apellido1=teclado.nextLine();
				} while (apellido1.length() > 20);
				
				modificar.setApellido1(apellido1);
				break;
			case 3:
				//Apellido2
				do {
					System.out.println("Introduce el segundo apellido(menos de 20 caracteres)");
					apellido2=teclado.nextLine();
				} while (apellido2.length() > 20);
					
				modificar.setApellido2(apellido2);
				break;
			case 4:
				//Correoe
				do {
					System.out.println("Introduce el correo-e(menos de 30 caracteres)");
					correoe=teclado.nextLine();
					if(!modificar.correoeValido(correoe)){
						System.out.println("Error, el correo debe ser: ***@***.**");
					}
				} while (correoe.length() > 30 && !modificar.correoeValido(correoe));
				
				modificar.setCorreoe(correoe);
				break;
			case 5:
				//Direccion
				do {
					System.out.println("Introduce la direccion(menos de 20 caracteres)");
					direccion=teclado.nextLine();
				} while (direccion.length() > 20);
				
				modificar.setDireccion(direccion);
				break;
			}
			do{	
				System.out.println("¿Qué desea modificar?");
				System.out.println("1.Nombre");
				System.out.println("2.Primer Apellido");
				System.out.println("3.Segundo apellido");
				System.out.println("4.Correo electrónico");
				System.out.println("5.Dirección");
				System.out.println("0.Salir");
				try{
					opcion=Integer.parseInt(teclado.nextLine());
				}catch(NumberFormatException e){
					System.out.println("Introduce un número, por favor");
					opcion=-1;
				}
			}while(opcion<0 || opcion>5);	
		}
	}

	
	public static void main(String[] args) {
		
		String rutaPersonas="Ficheros//Personas//Personas.dat";
		String rutaPersonasTemp="Ficheros//Personas//PersonasTemp.dat";
		String rutaClientes="Ficheros//Clientes//Clientes.dat";
		String rutaClientesTemp="Ficheros//Clientes//ClientesTemp.dat";
		String rutaCompras="Ficheros//Clientes//Compras.dat";
		String rutaComprasTemp="Ficheros//Clientes//ComprasTemp.dat";
		
		String rutaInstrumentos="Ficheros//Instrumentos//Instrumentos.dat";
		String rutaDescripcion="Ficheros//Instrumentos//Descripcion.txt";
		String rutaViento="Ficheros//Viento//Viento.dat";
		String rutaSaxofones="Ficheros//Saxofon//Saxofon.dat";
		String rutaPercusion="Ficheros//Percusion//Percusion.dat";
		String rutaCuerda="Ficheros//Cuerda//Cuerda.dat";
		String rutaGuitarra="Ficheros//Guitarra//Guitarra.dat";
		String rutaPastillas="Ficheros//Pastillas//Pastillas.dat";
		String rutaPastillasTemp="Ficheros//Pastillas//PastillasTemp.dat";
		String rutaPastillasGuitarras="Ficheros//Guitarra//Relacion.dat";
		String rutaPastillasGuitarrasTemp="Ficheros//Guitarra//RelacionTemp.dat";
		
		PersonaImpl persona;
		ClienteImpl cliente;
		
		InstrumentoImpl instrumento;
		VientoImpl viento;
		PercusionImpl percusion;
		CuerdaImpl cuerda;
		SaxofonImpl saxofon;
		GuitarraElectricaImpl guitarra;
		Pastilla pas;
		
		GestionadoraPrincipal gp=new GestionadoraPrincipal();
		Ordenador ord=new Ordenador();
		Actualizador act=new Actualizador();
		
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
		long dniGenerico;
		int idInstrumento,tipoInstrumento,idPastilla;
		boolean estadoBaseDatos=false;
		long dni;
		String nombre, apellido1, apellido2,correoe,direccion;
		Vector<InstrumentoImpl> v=new Vector<InstrumentoImpl>(0,1);

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
			//Añadir instrumento
			case 1:
				estadoBaseDatos=false;
				do{
					guardaInstrumentoMenu();
					try{
						tipoInstrumento=Integer.parseInt(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduzca un número, por favor");
						tipoInstrumento=0;
					}
				}while(tipoInstrumento<0 || tipoInstrumento>7);
				
				while(tipoInstrumento!=0){
					//No hay forma de no repetir la creación de cuerda y/o viento. Explicar!
					switch (tipoInstrumento){
					case 1:
						instrumento=creaInstrumento();
						fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
						break;
					case 2:
						//Viento
						instrumento=creaInstrumento();
						fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
						viento=creaViento(instrumento);
						fv.guardaInstrumentoViento(rutaViento, viento);
						break;
					case 3:
						//Percusion
						instrumento=creaInstrumento();
						fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
						percusion=creaPercusion(instrumento);
						fPer.guardaInstrumentoPerc(rutaPercusion, percusion);
						break;
					case 4:
						//Cuerda
						instrumento=creaInstrumento();
						fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
						cuerda=creaCuerda(instrumento);
						fCuerda.guardaInstrumentoCuerda(rutaCuerda, cuerda);
						break;
					case 5:
						//Saxofon
						instrumento=creaInstrumento();
						fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
						viento=creaViento(instrumento);
						fv.guardaInstrumentoViento(rutaViento, viento);
						saxofon=creaSaxofon(viento);
						fs.guardaInstrumentoSaxofon(rutaSaxofones, saxofon);
						break;
					case 6:
						//Guitarra
						instrumento=creaInstrumento();
						fi.guardaInstrumento(rutaInstrumentos,rutaDescripcion,instrumento);
						cuerda=creaCuerda(instrumento);
						fCuerda.guardaInstrumentoCuerda(rutaCuerda, cuerda);
						guitarra=creaGuitarraElectrica(cuerda, rutaPastillas);
						fg.guardaInstrumentoGuitarra(rutaGuitarra, guitarra);
						fg.guardaRelacionPastilla(rutaPastillasGuitarras, guitarra);
						break;
					case 7:
						//Pastilla
						pas=creaPastilla();
						fPas.guardaPastilla(rutaPastillasTemp, pas);
						
						break;
					}
					
					do{
						guardaInstrumentoMenu();
						try{
							tipoInstrumento=Integer.parseInt(teclado.nextLine());
						}catch(NumberFormatException e){
							System.out.println("Introduzca un número, por favor");
							tipoInstrumento=0;
						}
					}while(tipoInstrumento<0 || tipoInstrumento>7);
				}
				break;
			case 2:
				estadoBaseDatos=false;
				//Modificar Instrumento
				do{
					modificaInstrumentoMenu();
					try{
						tipoInstrumento=Integer.parseInt(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduzca un número, por favor");
						tipoInstrumento=0;
					}
				}while(tipoInstrumento<0 || tipoInstrumento>7);
				while (tipoInstrumento != 0) {
					switch (tipoInstrumento) {
					case 1:
						//Genérico
						do{
							fi.muestraInstrumentos(rutaInstrumentos);
							System.out.println("Introduzca el id del instrumento que quiera modificar");
							try{
							idInstrumento=Integer.parseInt(teclado.nextLine());
							}catch(NumberFormatException e){
								System.out.println(e);
								idInstrumento=-1;
							}
						}while(!gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento));
						//Recuperamos el instrumento
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
						modificaInstrumento(instrumento);
						fi.guardaInstrumento(rutaInstrumentos, rutaDescripcion, instrumento);
						break;
					case 2:
						//Viento
						do{
							fi.muestraInstrumentos(rutaInstrumentos);
							System.out.println("Introduzca el id del instrumento de viento que quiera modificar");
							try{
							idInstrumento=Integer.parseInt(teclado.nextLine());
							}catch(NumberFormatException e){
								System.out.println(e);
								idInstrumento=-1;
							}
						}while(!gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento) &&
								!gp.compruebaExistenciaViento(rutaViento, idInstrumento));
						//Recuperamos el instrumento
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
						modificaInstrumento(instrumento);
						fi.guardaInstrumento(rutaInstrumentos, rutaDescripcion, instrumento);
						
						viento=fv.devuelveInstrumentoViento(rutaViento, rutaInstrumentos, idInstrumento);
						modificaViento(viento);
						fv.guardaInstrumentoViento(rutaViento, viento);
						System.out.println("Cambios guardados con éxito");
						
						break;
					case 3:
						//Percusion
						do{
							fi.muestraInstrumentos(rutaInstrumentos);
							System.out.println("Introduzca el id del instrumento de percuisón que quiera modificar");
							try{
							idInstrumento=Integer.parseInt(teclado.nextLine());
							}catch(NumberFormatException e){
								System.out.println(e);
								idInstrumento=-1;
							}
						}while(!gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento) &&
								!gp.compruebaExistenciaPercusion(rutaPercusion, idInstrumento));
						//Recuperamos el instrumento
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
						modificaInstrumento(instrumento);
						fi.guardaInstrumento(rutaInstrumentos, rutaDescripcion, instrumento);
						
						percusion=fPer.devuelveInstrumentoPercusion(rutaPercusion, rutaInstrumentos, idInstrumento);
						modificaPercusion(percusion);
						fPer.guardaInstrumentoPerc(rutaPercusion, percusion);
						System.out.println("Cambios guardados con éxito");
						break;

					case 4:
						//Cuerda
						do{
							fi.muestraInstrumentos(rutaInstrumentos);
							System.out.println("Introduzca el id del instrumento de cuerda que quiera modificar");
							try{
							idInstrumento=Integer.parseInt(teclado.nextLine());
							}catch(NumberFormatException e){
								System.out.println(e);
								idInstrumento=-1;
							}
						}while(!gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento) &&
								!gp.compruebaExistenciaCuerda(rutaCuerda,idInstrumento));
						//Recuperamos el instrumento
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
						modificaInstrumento(instrumento);
						fi.guardaInstrumento(rutaInstrumentos, rutaDescripcion, instrumento);
						
						cuerda=fCuerda.devuelveInstrumentoCuerda(rutaCuerda, rutaInstrumentos, idInstrumento);
						modificaCuerda(cuerda);
						fCuerda.guardaInstrumentoCuerda(rutaCuerda, cuerda);
						System.out.println("Cambios guardados con éxito");
						break;

					case 5:
						//Saxofon
						do{
							fi.muestraInstrumentos(rutaInstrumentos);
							System.out.println("Introduzca el id del instrumento de viento que quiera modificar");
							try{
							idInstrumento=Integer.parseInt(teclado.nextLine());
							}catch(NumberFormatException e){
								System.out.println(e);
								idInstrumento=-1;
							}
						}while(!gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento) &&
								!gp.compruebaExistenciaViento(rutaViento, idInstrumento) &&
								!gp.compruebaExistenciaSaxofon(rutaSaxofones, idInstrumento));
						//Recuperamos el instrumento
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
						modificaInstrumento(instrumento);
						fi.guardaInstrumento(rutaInstrumentos, rutaDescripcion, instrumento);
						
						viento=fv.devuelveInstrumentoViento(rutaViento, rutaInstrumentos, idInstrumento);
						modificaViento(viento);
						fv.guardaInstrumentoViento(rutaViento, viento);
						
						saxofon=fs.devuelveInstrumentoSaxofon(rutaSaxofones, rutaViento, rutaInstrumentos, idInstrumento);
						modificaSaxo(saxofon);
						fs.guardaInstrumentoSaxofon(rutaSaxofones, saxofon);
						System.out.println("Cambios guardados con éxito");
						break;

					case 6:
						//Guitarra
						do{
							fi.muestraInstrumentos(rutaInstrumentos);
							System.out.println("Introduzca el id del instrumento de cuerda que quiera modificar");
							try{
							idInstrumento=Integer.parseInt(teclado.nextLine());
							}catch(NumberFormatException e){
								System.out.println(e);
								idInstrumento=-1;
							}
						}while(!gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento) &&
								!gp.compruebaExistenciaCuerda(rutaCuerda,idInstrumento));
						//Recuperamos el instrumento
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
						modificaInstrumento(instrumento);
						fi.guardaInstrumento(rutaInstrumentos, rutaDescripcion, instrumento);
						
						cuerda=fCuerda.devuelveInstrumentoCuerda(rutaCuerda, rutaInstrumentos, idInstrumento);
						modificaCuerda(cuerda);
						fCuerda.guardaInstrumentoCuerda(rutaCuerda, cuerda);
						
						guitarra=fg.devuelveInstrumentoGuitarra(rutaGuitarra, rutaCuerda, 
																rutaPastillasGuitarras, rutaInstrumentos, idInstrumento);
						/*
						 * Puesto que no sabemos si las pastillas van a ser modificadas (o no), guardamos las pastillas
						 * en el fichero temporal( por si van a ser eliminadas) y después volvemos a escribirlas.
						 * No es la mejor solución, pero a día de hoy no se me ocurre otra
						 */
						fg.guardaRelacionPastilla(rutaPastillasGuitarrasTemp, guitarra);
						
						modificaGuitarra(guitarra, rutaPastillasGuitarras);
						
						//Guardamos lso cambios
						fg.guardaInstrumentoGuitarra(rutaGuitarra, guitarra);
						fg.guardaRelacionPastilla(rutaPastillasGuitarrasTemp, guitarra);
						
						System.out.println("Cambios guardados con éxito");
						break;

					case 7:
						//Pastilla
						do{
							fPas.muestraPastillas(rutaPastillas);
							System.out.println("Introduce el id de la pastilla que quieres modificar");
							try{
								idPastilla=Integer.parseInt(teclado.nextLine());
							}catch(NumberFormatException e){
								System.out.println("Introduce un número, por favor");
								idPastilla=-1;
							}
							if(gp.compruebaExistenciaPastilla(rutaPastillasTemp, idPastilla)){
								System.out.println("Error, la pastilla ya ha sido modificada, actualice, por favor");
							}
						}while(!gp.compruebaExistenciaPastilla(rutaPastillas, idPastilla) &&
								gp.compruebaExistenciaPastilla(rutaPastillasTemp, idPastilla));
						
						pas=fPas.devuelvePastilla(rutaPastillas, idPastilla);
						modificaPastilla(pas);
						fPas.guardaPastilla(rutaPastillasTemp, pas);
						System.out.println("Pastilla guardada con éxito");
						break;
					}
					
					do{
						modificaInstrumentoMenu();
						try{
							tipoInstrumento=Integer.parseInt(teclado.nextLine());
						}catch(NumberFormatException e){
							System.out.println("Introduzca un número, por favor");
							tipoInstrumento=0;
						}
					}while(tipoInstrumento<0 || tipoInstrumento>7);
				}
				break;
			case 3:
				//Eliminar Instrumento
				estadoBaseDatos=false;
				do{
					System.out.println("Introduce el id del instrumento que desea eliminar: ");
					System.out.println("(0 para no eliminar ninguno)");
					idInstrumento=Integer.parseInt(teclado.nextLine());
				}while(idInstrumento<0 );
				
				if(!gp.compruebaExistenciaInstrumentoComprado(rutaCompras, idInstrumento)){
					//Sobreescribimos los datos del instrumento con dicha id
					instrumento=new InstrumentoImpl(idInstrumento, "",Marca.Fender, "", "", 0);
					fi.borraInstrumento(rutaInstrumentos, rutaDescripcion, instrumento);
					
					/*Dependiendo del tipo de instrumento que sea, borramos sus datos de los 
					diferentes ficheros*/
					
					//Viento y Saxofon
					if(gp.compruebaExistenciaViento(rutaViento, idInstrumento)){
						viento=new VientoImpl(instrumento, 'b', "",(byte) 0);
						fv.guardaInstrumentoViento(rutaViento, viento);
						if(gp.compruebaExistenciaSaxofon(rutaSaxofones, idInstrumento)){
							saxofon=new SaxofonImpl(viento, FamiliaSaxo.Alto, "", "", Acabado.BronceB);
							fs.guardaInstrumentoSaxofon(rutaSaxofones, saxofon);
						}
					}else{
						//Cuerda y guitarra
						if(gp.compruebaExistenciaCuerda(rutaCuerda, idInstrumento)){
							cuerda=new CuerdaImpl(instrumento, 1, "", (byte) 0);
							fCuerda.guardaInstrumentoCuerda(rutaCuerda, cuerda);
							if(gp.compruebaExistenciaGuitarra(rutaGuitarra, idInstrumento)){
								//Recupero las pastillas
								int[] pastillas=fg.devuelveRelacionesPastillas(rutaPastillasGuitarras, idInstrumento);
								guitarra=new GuitarraElectricaImpl(cuerda, Tipo.ES, pastillas, false, 3);
								fg.guardaInstrumentoGuitarra(rutaGuitarra,guitarra);
								fg.guardaRelacionPastilla(rutaPastillasGuitarrasTemp, guitarra);
								
							}
						}else{
							//Percusion
							if(gp.compruebaExistenciaPercusion(rutaPercusion, idInstrumento)){
								percusion=new PercusionImpl(instrumento, 'b', "", false);
								fPer.guardaInstrumentoPerc(rutaPercusion, percusion);
							}
						}
					}
				}else{
					System.out.println("Error, el instrumento ha sido comprado por alguien,no puede eliminarse");
				}
				break;
			case 4:
				//Añadir Cliente
				estadoBaseDatos=false;
				do {
					System.out.println("Introduce el dni(sin letra)");
					dni = Long.parseLong(teclado.nextLine());
					if(gp.compruebaExistenciaCliente(rutaClientes, dni) || gp.compruebaExistenciaCliente(rutaClientesTemp, dni)){
						System.out.println("Error, el cliente ya se encuentra en la base de datos");
					}
				} while (dni < 0 || dni > 99999999 && gp.compruebaExistenciaCliente(rutaClientes, dni) &&
						gp.compruebaExistenciaCliente(rutaClientesTemp, dni));
					
				do {
					System.out.println("Introduce el nombre(menos de 20 caracteres)");
					nombre=teclado.nextLine();
				} while (nombre.length() > 20);

				do {
					System.out.println("Introduce el primer apellido(menos de 20 caracteres)");
					apellido1=teclado.nextLine();
				} while (apellido1.length() > 20);

				do {
					System.out.println("Introduce el segundo apellido(menos de 20 caracteres)");
					apellido2=teclado.nextLine();
				} while (apellido2.length() > 20);

				do {
					System.out.println("Introduce el correo-e(menos de 30 caracteres)");
					correoe=teclado.nextLine();
				} while (correoe.length() > 30);
				
				do {
					System.out.println("Introduce la direccion(menos de 20 caracteres)");
					direccion=teclado.nextLine();
				} while (direccion.length() > 20);
				
				persona=new PersonaImpl(dni, nombre, apellido1, apellido2);
				fp.guardaPersona(rutaPersonasTemp, persona);
				cliente=new ClienteImpl(persona,correoe,direccion,v);
				fc.guardaClienteSinCompras(rutaClientesTemp, cliente);
				break;
			case 5:
				//Modificar Cliente
				estadoBaseDatos=false;
				do {
					System.out.println("Introduce el dni(sin letra)");
					dni = Long.parseLong(teclado.nextLine());
					if(!gp.compruebaExistenciaCliente(rutaClientes, dni) ){
						System.out.println("Error, el cliente no existe");
					}else{
						if(gp.compruebaExistenciaCliente(rutaClientesTemp, dni)){
							System.out.println("Error, los datos del cliente no están actualizados");
						}
					}
				} while (dni < 0 || dni > 99999999 && !gp.compruebaExistenciaCliente(rutaClientes, dni) &&
						gp.compruebaExistenciaCliente(rutaClientesTemp, dni));
				cliente=fc.devuelveCliente(rutaClientes, rutaPersonas, dni, rutaInstrumentos, rutaCompras);
				modificaCliente(cliente);
				fc.guardaCliente(rutaClientesTemp, rutaComprasTemp, cliente);
				System.out.println("Cliente guardado con éxito");
				break;
			case 6:
				//Eliminar Cliente
				estadoBaseDatos=false;
				do{
					System.out.println("Introduzca el dni del cliente que desea eliminar: ");
					dniGenerico=Long.parseLong(teclado.nextLine());
				}while(dniGenerico<=0);
				//Guardamos en PersonaTemp
				if(dniGenerico!=0 &&
						gp.compruebaExistenciaPersona(rutaPersonas, dniGenerico) && 
						!gp.compruebaExistenciaPersona(rutaPersonasTemp, dniGenerico)){
					
					persona=new PersonaImpl(dniGenerico,"","","");
					fp.guardaPersona(rutaPersonasTemp,persona);
					
					//Guardamos en ClienteTemp
					if(gp.compruebaExistenciaCliente(rutaClientes, dniGenerico) && 
							!gp.compruebaExistenciaCliente(rutaClientesTemp, dniGenerico)){
						//Guardamos la dirección como # para saber que la vamos a borrar
						cliente=new ClienteImpl(persona,"generico@generico.com","#",null);
						fc.guardaClienteSinCompras(rutaClientesTemp,cliente);
					}
				}
				break;
			case 7:
				//Realizar Venta
				estadoBaseDatos=false;
				//Recuperamos el instrumento
				do{
					System.out.println("Introduzca el id del instrumento que quieras vender: ");
					try{
						idInstrumento=Integer.parseInt(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduzca un número por favor");
						idInstrumento=-1;
					}
				}while(!gp.compruebaExistenciaInstrumento(rutaInstrumentos, idInstrumento));
				instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
				
				//Recuperamos al cliente
				do{
					System.out.println("Introduzca el dni de la persona que lo va a comprar:(sin letra) ");
					try{
						dni=Long.parseLong(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduzca un número por favor");
						dni=-1;
					}
				}while(!gp.compruebaExistenciaCliente(rutaClientes, dni));
				cliente=fc.devuelveCliente(rutaClientes, rutaPersonas, dni, rutaInstrumentos, rutaCompras);
				//añadimos la compra
				v=cliente.getCompras();
				v.add(instrumento);
				cliente.setCompras(v);
				
				//Guardamos en fichero
				fc.guardaCompras(rutaComprasTemp, cliente,v.size()-1);
				break;
			case 8:
				//Realizar Devolución
				estadoBaseDatos=false;
				do{
					System.out.println("Introduzca el dni de la persona que va a devolver la compra:(sin letra) ");
					try{
						dni=Long.parseLong(teclado.nextLine());
					}catch(NumberFormatException e){
						System.out.println("Introduzca un número por favor");
						dni=-1;
					}
				}while(!gp.compruebaExistenciaCliente(rutaClientes, dni));
				cliente=fc.devuelveCliente(rutaClientes, rutaPersonas, dni, rutaInstrumentos, rutaCompras);
				v=cliente.getCompras();
				
				do{
					System.out.println("Instrumentos vendidos: ");
					for(int i=0;i<v.size();i++){
						System.out.println(v.elementAt(i));
					}
					System.out.println("Introduce el id del instrumento que quieres devolver: ");
					try{
						idInstrumento=Integer.parseInt(teclado.nextLine());
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
					}catch(NumberFormatException e){
						System.out.println("Introduce un número, por favor");
						idInstrumento=-1;
						instrumento=new InstrumentoImpl(idInstrumento,"no",Marca.Fender,"","",10);
					}
				}while(!v.contains(instrumento));
				fc.guardaCompras(rutaComprasTemp, cliente, v.indexOf(instrumento));
				System.out.println("Devolución realizada con éxito");
				break;
			case 9:
				//Muestra Instrumentos
				System.out.println("Id"+"  "+"Nombre     "+"Marca  "+"Modelo  "+"Precio de Venta");
				fi.muestraInstrumentos(rutaInstrumentos);
				
				//Muestra menú instrumentos
				do{
					menuInstrumentos();
					opcionMenuInstrumentos=Integer.parseInt(teclado.nextLine());
				}while(opcionMenuInstrumentos<0 || opcionMenuInstrumentos>7);
				
				while(opcionMenuInstrumentos!=0){
					if(opcionMenuInstrumentos!=7){
						System.out.println("Introduce la id: ");
						idInstrumento=Integer.parseInt(teclado.nextLine());
					}else{
						idInstrumento=-1;
					}
					switch (opcionMenuInstrumentos) {
					
					case 1:
						if (gp.compruebaExistenciaViento(rutaViento, idInstrumento)) {
							System.out.println(viento=fv.devuelveInstrumentoViento(rutaViento, rutaInstrumentos, idInstrumento));
							if(viento.getDescripcion().equals("s")){
								System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
							}
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 2:
						if (gp.compruebaExistenciaPercusion(rutaPercusion, idInstrumento)) {
							System.out.println(percusion=fPer.devuelveInstrumentoPercusion(rutaPercusion, rutaInstrumentos, idInstrumento));
							if(percusion.getDescripcion().equals("s"))
								System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 3:
						if (gp.compruebaExistenciaCuerda(rutaCuerda, idInstrumento)) {
							System.out.println(cuerda=fCuerda.devuelveInstrumentoCuerda(rutaCuerda, rutaInstrumentos, idInstrumento));
							if(cuerda.getDescripcion().equals("s"))
								System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 4:
						if (gp.compruebaExistenciaSaxofon(rutaSaxofones, idInstrumento)) {
							System.out.println(saxofon=fs.devuelveInstrumentoSaxofon(rutaSaxofones, rutaViento,
									rutaInstrumentos, idInstrumento));
							if(saxofon.getDescripcion().equals("s"))
								System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 5:
						if (gp.compruebaExistenciaGuitarra(rutaGuitarra, idInstrumento)) {
							System.out.println(guitarra=fg.devuelveInstrumentoGuitarra(rutaGuitarra, rutaCuerda,
									rutaPastillasGuitarras, rutaInstrumentos, idInstrumento));
							System.out.println("Pastillas de la guitarra: ");
							for(int i=0;i<guitarra.getIdPastillas().length;i++){
								System.out.println(fPas.devuelvePastilla(rutaPastillas, guitarra.getIdPastillas()[i]));
							}
							if(guitarra.getDescripcion().equals("s")){
								System.out.println("Descripcion: "+fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
							}
						} else {
							System.out.println("Error,id inválido!");
						}
						break;
					case 6:
						instrumento=fi.devuelveInstrumento(rutaInstrumentos, idInstrumento);
						if(instrumento.getDescripcion().equals("s")){
							instrumento.setDescripcion(fi.descripcionInstrumento(rutaDescripcion, idInstrumento));
							System.out.println(instrumento.getDescripcion());
						}
						break;
					case 7:
						fPas.muestraPastillas(rutaPastillas);
						break;
					}
					//Muestra menú instrumentos
					do{
						menuInstrumentos();
						opcionMenuInstrumentos=Integer.parseInt(teclado.nextLine());
					}while(opcionMenuInstrumentos<0 || opcionMenuInstrumentos>7);
				}
				break;
			case 10:
				//Mostrar Clientes
				System.out.println();
				//Si la "base de datos no está actualizada"
				if(!estadoBaseDatos){
					System.out.println("Atención,la información de los siguientes clientes no están actualizada");
					
				}else{
					System.out.println("Los datos están actualizados!");
				}
				fc.muestraClientes(rutaClientes,rutaPersonas);
				System.out.println();
				do{
					System.out.println("Si desea ver las compras de un cliente, introduzca el dni del cliente");
					System.out.println("En caso contrario,introduzca 0");
					dniGenerico=Long.parseLong(teclado.nextLine());
				}while(dniGenerico<0);
				
				 v=fc.devuelveCompras(rutaCompras, rutaInstrumentos, dniGenerico);
				
				if(dniGenerico!=0 && v.size()>0){
					for(int i=0;i<v.size();i++){
						//Muestro el instrumento sin descripción
						v.get(i).toString();
						//Muestro la descripción (si existe)
						if(v.get(i).getDescripcion().equals("s")){
							v.get(i).setDescripcion(fi.descripcionInstrumento(rutaDescripcion, i));
							System.out.println(v.get(i).getDescripcion());
						}
					}
				}
				break;
			case 11:
				//Actualizar Base de datos
				System.out.println("Atención, este proceso puede durar varios minutos...");
				ord.ordenaFicheroPersona(rutaPersonas);
				ord.ordenaFicheroPersona(rutaPersonasTemp);
				ord.ordenaFicheroCliente(rutaClientes);
				ord.ordenaFicheroCliente(rutaClientesTemp);
				
				ord.ordenaFicheroCompras(rutaCompras);
				ord.ordenaFicheroCompras(rutaComprasTemp);
				
				ord.ordenaFicheroPastillas(rutaPastillas);
				ord.ordenaFicheroPastillas(rutaPastillasTemp);
				
				ord.ordenaFicheroRelaciones(rutaPastillasGuitarras);
				ord.ordenaFicheroRelaciones(rutaPastillasGuitarrasTemp);
				
				act.actualizaPersonas(rutaPersonas, rutaPersonasTemp);
				act.actualizaClientes(rutaClientes, rutaClientes);
				act.actualizaCompras(rutaCompras, rutaComprasTemp);
				act.actualizaPastillas(rutaPastillas, rutaPastillasTemp);
				act.actualizaRelaciones(rutaPastillasGuitarras, rutaPastillasGuitarrasTemp);
				estadoBaseDatos=true;
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
