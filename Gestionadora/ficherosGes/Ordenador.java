package ficherosGes;

import java.io.*;
import datos.*;
import ficheros.*;
import compartidas.*;

public class Ordenador {
	
	/* 
	 * Interfaz 
	 * Cabecera:public void ordenaFicheroPersona(String ruta)
	 * Proceso:Ordena un fichero de personas. Si el fichero ocupa más de 10 megas lo hará de forma externa.
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:El fichero ordenado
	 * Postcondiciones:El fichero qeudará ordenado según el dni de la persona.
	 */
	
	public void ordenaFicheroPersona(String ruta){
		File fichero=new File(ruta);
		if(fichero.length()>1){
			ordenaPersonaExterna(ruta);
		}else{
			ordenaPersonaHibrido(ruta);
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:private void ordenaPersonaHibrido(String ruta)
	 * Proceso:Ordena un fichero de personas de forma híbrida
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:El fichero ordenado
	 * Postcondiciones:El fichero qeudará ordenado según el dni de la persona.
	 */
	
	private void ordenaPersonaHibrido(String ruta){
		FicheroPersona fp=new FicheroPersona();
		int numeroPersonas=fp.cuentaPersonas(ruta);
		PersonaImpl[] personas=new PersonaImpl[numeroPersonas];
		PersonaImpl aux = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		
		//Relleno de array
		int contador = 0;
		try {
			fis = new FileInputStream(ruta);
			ois = new ObjectInputStream(fis);
			for (contador=0;contador < numeroPersonas;contador++) {
				aux = (PersonaImpl) ois.readObject();
				personas[contador]=aux;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		}
		
		//Ordenacion hibrida
		int i,j;
		for(i=1;i<numeroPersonas;i++){
			for(j=i;j>0 && personas[j-1].compareTo(personas[j])>0;j--){
				aux=personas[j];
				personas[j]=personas[j-1];
				personas[j-1]=aux;
			}
		}
		
		//Relleno de fichero
		try {
			fos=new FileOutputStream(ruta);
			oos=new ObjectOutputStream(fos);
			for (contador=0;contador < numeroPersonas;contador++) {
				oos.writeObject(personas[contador]);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:private void ordenaPersonaExterna(String ruta)
	 * Proceso:Ordena un fichero de personas de forma externa,,guardará una copia llamado ****c del orignial
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:El fichero ordenado
	 * Postcondiciones:El fichero qeudará ordenado según el dni de la persona.
	 */
	
	private void ordenaPersonaExterna(String original){
		File fOriginal=new File(original);
		File fCopia=new File(original+"c");
		
		FicheroPersona fp=new FicheroPersona();
		
		FileOutputStream originalFOS=null;
		ObjectOutputStream originalOOS=null;
		
		String aux1=original+"aux1";
		String aux2=original+"aux2";
		
		FileOutputStream copiaFOS=null;
		ObjectOutputStream copiaOOS=null;
		
		FileInputStream originalFIS=null;
		ObjectInputStream originalOIS=null;
		
		PersonaImpl a;
		int tamanyoOriginal;
		
		//Para ordenar:
		File fAux1=new File(aux1);
		File fAux2=new File(aux2);
		FileOutputStream aux1FOS=null;
		ObjectOutputStream aux1OOS=null;
		FileOutputStream aux2FOS=null;
		ObjectOutputStream aux2OOS=null;
		
		//creaFicheroCopia (para evitar stream header malo)
		try{
			copiaFOS=new FileOutputStream(fCopia);
			copiaOOS=new ObjectOutputStream(copiaFOS);
			aux1FOS=new FileOutputStream(fAux1);
			aux1OOS=new ObjectOutputStream(aux1FOS);
			aux2FOS=new FileOutputStream(fAux2);
			aux2OOS=new ObjectOutputStream(aux2FOS);
		}catch(FileNotFoundException e){
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(copiaOOS!=null){
				try {
					copiaOOS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(copiaFOS!=null){
				try {
					copiaFOS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(aux1OOS!=null){
				try {
					aux1OOS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(aux1FOS!=null){
				try {
					aux1FOS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(aux2OOS!=null){
				try {
					aux2OOS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(aux2FOS!=null){
				try {
					aux2FOS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		
		//CopiaFichero
		try{
			originalFIS=new FileInputStream(fOriginal);
			originalOIS=new ObjectInputStream(originalFIS);
			a=(PersonaImpl) originalOIS.readObject();
			while(a!=null){
				fp.guardaPersona(original+"c", a);
				a=(PersonaImpl) originalOIS.readObject();
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}catch(EOFException e){
			
		}catch (IOException e) {
			System.out.println(e);
		}finally{
			if(originalOIS!=null){
				try {
					originalOIS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(originalFIS!=null){
				try {
					originalFIS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		//ordenaFichero
		tamanyoOriginal=fp.cuentaPersonas(original);
		for(int i=1;i<tamanyoOriginal;i=i*2){
			parteFicheroSecuencias(original,aux1,aux2,i);
			
			//reescribeOriginal
			try {
				originalFOS = new FileOutputStream(fOriginal);
				originalOOS=new ObjectOutputStream(originalFOS);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}finally{
				if(originalOOS!=null){
					try {
						originalOOS.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
				if(originalFOS!=null){
					try {
						originalFOS.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}
			
			mezclaFicheroSecuencia(original, aux1, aux2, i);
			
			//reescribeAuxiliares
			try {
				aux1FOS = new FileOutputStream(aux1);
				aux1OOS=new ObjectOutputStream(aux1FOS);
				aux2FOS = new FileOutputStream(aux2);
				aux2OOS=new ObjectOutputStream(aux2FOS);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				System.out.println(e);
			}finally{
				if(aux1OOS!=null){
					try {
						aux1OOS.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
				if(aux1FOS!=null){
					try {
						aux1FOS.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
				if(aux2OOS!=null){
					try {
						aux2OOS.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
				if(aux2FOS!=null){
					try {
						aux1FOS.close();
					} catch (IOException e) {
						System.out.println(e);
					}
				}
			}
		}
		fAux1.delete();
		fAux2.delete();
	}
	
	/* 
	 * Interfaz 
	 * Cabecera: private void parteFicheroSecuencias(String original,String part1,string part2,int secuencia)
	 * Proceso:método que divide un fichero de objetos personas en 2,siguiendo una secuencia,es decir,
	 * 			creando ficheros en los que mete las "secuencia" primeras,primero en uno y después en otro.
	 * Precondiciones:ARCHIVO DE TIPO OBJETO
	 * Entrada:1 cadena para el archivo original
	 * 		   2 cadenas para lso archivos de la partición
	 * 		   1 entero para el número de registros por secuencia
	 * Salida:los 2 archivos particionados
	 * Postcondiciones:El archivo quedará partido en dos, sin ser destruido o modificado
	 */
		
	private void parteFicheroSecuencias(String original, String part1, String part2, int secuencia) {
		File fOriginal = new File(original);
		FicheroPersona fp = new FicheroPersona();
		FileInputStream leer = null;
		ObjectInputStream in = null;
		int i, j;
		int numPersonas=fp.cuentaPersonas(original);
		PersonaImpl p;
		try {
			leer = new FileInputStream(fOriginal);
			in = new ObjectInputStream(leer);
			while (numPersonas>0) {
				for (i = 0;numPersonas>0 && i < secuencia; i++) {
					p = (PersonaImpl) in.readObject();
					fp.guardaPersona(part1, p);
					numPersonas--;
				}
				for (j = 0; numPersonas>0 && j < secuencia; j++) {
					p = (PersonaImpl) in.readObject();
					fp.guardaPersona(part2, p);
					numPersonas--;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (leer != null) {
				try {
					leer.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	/* 
	 * Interfaz 
	 * Cabecera:void mezclaFicheroSecuencia(String nuevo,String fusiona1,String fusiona2,int secuencia)
	 * Proceso:método que fusiona dos ficheros, guardandolo en uno nuevo, de forma ORDENADA de menor a mayor
	 * Precondiciones:Entero mayor que 1
	 * Entrada:1 cadena para el nuevo fichero
	 * 			2 cadenas para cada uno de los ficheros que quieres fusionar
	 * 			1 entero para saber el número de secuencias que vas a mezclar en cada iteración
	 * Salida:El fichero
	 * Entrada/Salida:Ninguna
	 * Postcondiciones:El fichero quedará guardado en la carpeta indicada por la cadena "original"
	 */
	
	public void mezclaFicheroSecuencia(String original, String fusiona1, String fusiona2, int secuencia) {
		File fOriginal = new File(original);
		File fusiona_l = new File(fusiona1);
		File fusiona_2 = new File(fusiona2);
		
		FicheroPersona fp=new FicheroPersona();
		
		FileInputStream leer1 = null;
		ObjectInputStream in1 = null;
		FileInputStream leer2 = null;
		ObjectInputStream in2 = null;
		FileOutputStream escribir = null;
		MiOOS out = null;
		PersonaImpl a, b;
		int cont1, cont2, numRegistro1, numRegistro2;
		
		
		try {
			leer1 = new FileInputStream(fusiona_l);
			in1 = new ObjectInputStream(leer1);
			leer2 = new FileInputStream(fusiona_2);
			in2 = new ObjectInputStream(leer2);
			escribir = new FileOutputStream(fOriginal, true);
			out = new MiOOS(escribir);
			numRegistro1 = fp.cuentaPersonas(fusiona1);
			numRegistro2 = fp.cuentaPersonas(fusiona2);
			a = (PersonaImpl) in1.readObject();
			b = (PersonaImpl) in2.readObject();

			while (numRegistro1 > 0 || numRegistro2 > 0) { // Mientras quede
															// algo pro leer en
															// un fichero

				// Introducimos dependiendo de la secuencia
				for (cont1 = 0, cont2 = 0; cont1 < secuencia && cont2 < secuencia && numRegistro1 > 0 && numRegistro2 > 0;) {
					if (a.getDNI() < b.getDNI()) {
						out.writeObject(a);
						numRegistro1--;
						if (numRegistro1 > 0) {
							a = (PersonaImpl) in1.readObject();
						} else {
							a = null;
						}
						cont1++;
					} else {
						out.writeObject(b);
						numRegistro2--;
						if (numRegistro2 > 0) {
							b = (PersonaImpl) in2.readObject();
						} else {
							b = null;
						}
						cont2++;

					}
				}

				// Si uno de los dos contadores ha llegado al valor de la
				// secuencia
				if (cont1 < secuencia) {
					while (a != null && cont1 < secuencia) {
						out.writeObject(a);
						if (numRegistro1 > 0) {
							a = (PersonaImpl) in1.readObject();
						} else {
							a = null;
						}
						cont1++;
						numRegistro1--;
					}
				} else {
					while (b != null && cont2 < secuencia) {
						out.writeObject(b);
						numRegistro2--;
						if (numRegistro2 > 0) {
							b = (PersonaImpl) in2.readObject();
						} else {
							b = null;
						}
						cont2++;
					}
				}
				if (numRegistro1 == 0) {
					while (numRegistro2 > 0) {
						out.writeObject(b);
						numRegistro2--;
						if (numRegistro2 > 0) {
							b = (PersonaImpl) in2.readObject();
						} else {
							b = null;
						}
					}
				}
				if (numRegistro2 == 0) {
					while (numRegistro1 > 0) {
						out.writeObject(a);
						numRegistro1--;
						if (numRegistro1 > 0) {
							a = (PersonaImpl) in1.readObject();
						} else {
							a = null;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in1 != null) {
				try {
					in1.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (in2 != null) {
				try {
					in2.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (leer1 != null) {
				try {
					leer1.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (leer2 != null) {
				try {
					leer2.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (escribir != null) {
				try {
					escribir.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	/* 
	 * Interfaz 
	 * Cabecera:public void ordenaFicheroCliente(String ruta)
	 * Proceso:Ordena un fichero de clientes.
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:El fichero ordenado
	 * Postcondiciones:El fichero qeudará ordenado según el dni del cliente.
	 */
	
	public void ordenaFicheroCliente(String original){
		File fOriginal=new File(original);
		File fCopia=new File(original+"c");
		
		FicheroCliente fc=new FicheroCliente();
		
		String aux1=original+"aux1";
		String aux2=original+"aux2";
		
		FileInputStream originalFIS=null;
		DataInputStream originalDIS=null;
		
		PersonaImpl p;
		ClienteImpl c;
		int tamanyoOriginal;
		long dni;
		String correo="",direccion="";
		
		//Para ordenar:
		File fAux1=new File(aux1);
		File fAux2=new File(aux2);
		
		//creaFicheroCopia
		fCopia.delete();
		try {
			fCopia.createNewFile();
		} catch (IOException e1) {
			System.out.println(e1);
		}
		
		//CopiaFichero
		try{
			originalFIS=new FileInputStream(fOriginal);
			originalDIS=new DataInputStream(originalFIS);
			
			
			while(originalDIS.available()>0){
				//Recuperamos cliente del fichero original
				dni =originalDIS.readLong();
				correo="";
				for(int i=0;i<30;i++){
					correo=correo+originalDIS.readChar();
				}
				correo=UtilidadesCompartidas.quitaAsterisco(correo);
				direccion="";
				for(int i=0;i<20;i++){
					direccion=direccion+originalDIS.readChar();
				}
				direccion=UtilidadesCompartidas.quitaAsterisco(direccion);
				
				//Creamos cliente a
				p=new PersonaImpl(dni, "", "", "");
				c=new ClienteImpl(p, correo, direccion,null);
				fc.guardaClienteSinCompras(original+"c", c);
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(EOFException e){
			
		}catch (IOException e) {
			System.out.println(e);
		}finally{
			if(originalDIS!=null){
				try {
					originalDIS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(originalFIS!=null){
				try {
					originalFIS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		//ordenaFichero
		tamanyoOriginal=fc.cuentaClientes(original);
		for(int i=1;i<tamanyoOriginal;i=i*2){
			parteFicheroClienteSecuencias(original,aux1,aux2,i);
			
			//reescribeOriginal
			fOriginal.delete();
			try {
				fOriginal.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
			
			mezclaFicheroClienteSecuencia(original, aux1, aux2, i);
			
			//reescribeAuxiliares
			fAux1.delete();
			fAux2.delete();
			try {
				fAux1.createNewFile();
				fAux2.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
			
		}
		fAux1.delete();
		fAux2.delete();
		System.out.println("Ordenado externamente");
	}
	
	/* 
	 * Interfaz 
	 * Cabecera: private void parteFicheroClienteSecuencias(String original,String part1,string part2,int secuencia)
	 * Proceso:método que divide un fichero de cñiemtes en 2,siguiendo una secuencia,es decir,
	 * 			creando ficheros en los que mete las "secuencia" primeras,primero en uno y después en otro.
	 * Precondiciones:ARCHIVO DE TIPO OBJETO
	 * Entrada:1 cadena para el archivo original
	 * 		   2 cadenas para lso archivos de la partición
	 * 		   1 entero para el número de registros por secuencia
	 * Salida:los 2 archivos particionados
	 * Postcondiciones:El archivo quedará partido en dos, sin ser destruido o modificado
	 */
		
	private void parteFicheroClienteSecuencias(String original, String part1, String part2, int secuencia) {
		File fOriginal = new File(original);
		FicheroCliente fc = new FicheroCliente();
		FileInputStream fis = null;
		DataInputStream dis = null;
		long dniLeido;
		String direccion="",correoe="";
		int i, j,letra;
		//Uasremos una persona genérica,puesto que no nos interesa la información que se guarda ahí
		PersonaImpl p;
		ClienteImpl c;
		
		
		try {
			fis = new FileInputStream(fOriginal);
			dis = new DataInputStream(fis);
			while (dis.available()>0) {
				for (i = 0;i < secuencia; i++) {
					dniLeido=dis.readLong();
					//Lee correo
					correoe="";
					for(letra=0;letra<30;letra++){
						correoe=correoe+dis.readChar();
					}
					correoe=UtilidadesCompartidas.quitaAsterisco(correoe);
					//lee direccion
					direccion="";
					for(letra=0;letra<20;letra++){
						direccion=direccion+dis.readChar();
					}
					direccion=UtilidadesCompartidas.quitaAsterisco(direccion);

					//Creamos y guardamos
					p=new PersonaImpl(dniLeido, "a", "a", "a");
					c=new ClienteImpl(p, correoe, direccion, null);
					fc.guardaClienteSinCompras(part1, c);
				}
				for (j = 0;j < secuencia; j++) {
					dniLeido=dis.readLong();
					//Lee correo
					correoe="";
					for(letra=0;letra<30;letra++){
						correoe=correoe+dis.readChar();
					}
					correoe=UtilidadesCompartidas.quitaAsterisco(correoe);
					//lee direccion
					direccion="";
					for(letra=0;letra<20;letra++){
						direccion=direccion+dis.readChar();
					}
					direccion=UtilidadesCompartidas.quitaAsterisco(direccion);
					
					//Creamos y guardamos
					p=new PersonaImpl(dniLeido, "a", "a", "a");
					c=new ClienteImpl(p, correoe, direccion, null);
					fc.guardaClienteSinCompras(part2, c);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:void mezclaFicheroClienteSecuencia(String nuevo,String fusiona1,String fusiona2,int secuencia)
	 * Proceso:método que fusiona dos ficheros, guardandolo en uno nuevo, de forma ORDENADA de menor a mayor
	 * Precondiciones:Entero mayor que 1
	 * Entrada:1 cadena para el nuevo fichero
	 * 			2 cadenas para cada uno de los ficheros que quieres fusionar
	 * 			1 entero para saber el número de secuencias que vas a mezclar en cada iteración
	 * Salida:El fichero
	 * Entrada/Salida:Ninguna
	 * Postcondiciones:El fichero quedará guardado en la carpeta indicada por la cadena "original"
	 */
	
	private void mezclaFicheroClienteSecuencia(String original, String fusiona1, String fusiona2, int secuencia) {
		File fOriginal = new File(original);
		File fusiona_l = new File(fusiona1);
		File fusiona_2 = new File(fusiona2);
		
		FicheroCliente fc=new FicheroCliente();
		
		FileInputStream leer1 = null;
		DataInputStream in1 = null;
		FileInputStream leer2 = null;
		DataInputStream in2 = null;
		PersonaImpl p;
		ClienteImpl a=null,b=null;
		int cont1, cont2;
		
		long dniA,dniB;
		String correoA="",direccionA="";
		String correoB="",direccionB="";
		
		try {
			leer1 = new FileInputStream(fusiona_l);
			in1 = new DataInputStream(leer1);
			leer2 = new FileInputStream(fusiona_2);
			in2 = new DataInputStream(leer2);
			

			//Recuperamos cliente del fichero fusiona1
			dniA =in1.readLong();
			correoA="";
			for(int i=0;i<30;i++){
				correoA=correoA+in1.readChar();
			}
			correoA=UtilidadesCompartidas.quitaAsterisco(correoA);
			direccionA="";
			for(int i=0;i<20;i++){
				direccionA=direccionA+in1.readChar();
			}
			direccionA=UtilidadesCompartidas.quitaAsterisco(direccionA);
			
			//Creamos cliente a
			p=new PersonaImpl(dniA, "", "", "");
			a=new ClienteImpl(p, correoA, direccionA,null);
			
			
			
			//Recuperamos cliente del fichero fusiona2
			dniB = in2.readLong();
			correoB="";
			for(int i=0;i<30;i++){
				correoB=correoA+in2.readChar();
			}
			correoB=UtilidadesCompartidas.quitaAsterisco(correoB);
			direccionB="";
			for(int i=0;i<20;i++){
				direccionB=direccionB+in2.readChar();
			}
			direccionB=UtilidadesCompartidas.quitaAsterisco(direccionB);
			
			//Creamos cliente b
			p=new PersonaImpl(dniB, "", "", "");
			b=new ClienteImpl(p, correoB, direccionB,null);
			
			

			while (a!=null || b!=null) { // Mientras quede
															// algo pro leer en
															// un fichero

				// Introducimos dependiendo de la secuencia
				for (cont1 = 0, cont2 = 0; cont1 < secuencia && cont2 < secuencia && 
						a!=null && b!=null;) {
					
					//Procedemos a guardar de forma ordenada
					if (dniA < dniB) {
						fc.guardaClienteSinCompras(original, a);
						if (in1.available() > 0) {
							//Recuperamos cliente del fichero fusiona1
							dniA =in1.readLong();
							correoA="";
							for(int i=0;i<30;i++){
								correoA=correoA+in1.readChar();
							}
							correoA=UtilidadesCompartidas.quitaAsterisco(correoA);
							direccionA="";
							for(int i=0;i<20;i++){
								direccionA=direccionA+in1.readChar();
							}
							direccionA=UtilidadesCompartidas.quitaAsterisco(direccionA);
							
							//Creamos cliente a
							p=new PersonaImpl(dniA, "", "", "");
							a=new ClienteImpl(p, correoA, direccionA,null);
						} else {
							a = null;
						}
						cont1++;
					} else {
						fc.guardaClienteSinCompras(original, b);
						if (in2.available() > 0) {
							//Recuperamos cliente del fichero fusiona2
							dniB = in2.readLong();
							correoB="";
							for(int i=0;i<30;i++){
								correoB=correoA+in2.readChar();
							}
							correoB=UtilidadesCompartidas.quitaAsterisco(correoB);
							direccionB="";
							for(int i=0;i<20;i++){
								direccionB=direccionB+in2.readChar();
							}
							direccionB=UtilidadesCompartidas.quitaAsterisco(direccionB);
							
							//Creamos cliente b
							p=new PersonaImpl(dniB, "", "", "");
							b=new ClienteImpl(p, correoB, direccionB,null);
						} else {
							b = null;
						}
						cont2++;

					}
				}

				// Si uno de los dos contadores ha llegado al valor de la
				// secuencia
				if (cont1 < secuencia) {
					while (a!= null && cont1 < secuencia) {
						fc.guardaClienteSinCompras(original, a);
						if (in1.available() > 0) {
							//Recuperamos cliente del fichero fusiona1
							dniA =in1.readLong();
							correoA="";
							for(int i=0;i<30;i++){
								correoA=correoA+in1.readChar();
							}
							correoA=UtilidadesCompartidas.quitaAsterisco(correoA);
							direccionA="";
							for(int i=0;i<20;i++){
								direccionA=direccionA+in1.readChar();
							}
							direccionA=UtilidadesCompartidas.quitaAsterisco(direccionA);
							
							//Creamos cliente a
							p=new PersonaImpl(dniA, "", "", "");
							a=new ClienteImpl(p, correoA, direccionA,null);
						} else {
							a = null;
						}
						cont1++;
					}
				} else {
					while (b != null && cont2 < secuencia) {
						fc.guardaClienteSinCompras(original, b);
						if (in2.available() > 0) {
							//Recuperamos cliente del fichero fusiona2
							dniB = in2.readLong();
							correoB="";
							for(int i=0;i<30;i++){
								correoB=correoA+in2.readChar();
							}
							correoB=UtilidadesCompartidas.quitaAsterisco(correoB);
							direccionB="";
							for(int i=0;i<20;i++){
								direccionB=direccionB+in2.readChar();
							}
							direccionB=UtilidadesCompartidas.quitaAsterisco(direccionB);
							
							//Creamos cliente b
							p=new PersonaImpl(dniB, "", "", "");
							b=new ClienteImpl(p, correoB, direccionB,null);
						} else {
							b = null;
						}
						cont2++;
					}
				}
				if (a==null) {
					while (b!=null) {
						fc.guardaClienteSinCompras(original, b);
						if (in2.available() > 0) {
							//Recuperamos cliente del fichero fusiona2
							dniB = in2.readLong();
							correoB="";
							for(int i=0;i<30;i++){
								correoB=correoA+in2.readChar();
							}
							correoB=UtilidadesCompartidas.quitaAsterisco(correoB);
							direccionB="";
							for(int i=0;i<20;i++){
								direccionB=direccionB+in2.readChar();
							}
							direccionB=UtilidadesCompartidas.quitaAsterisco(direccionB);
							
							//Creamos cliente b
							p=new PersonaImpl(dniB, "", "", "");
							b=new ClienteImpl(p, correoB, direccionB,null);
						} else {
							b = null;
						}
					}
				}
				if (b==null) {
					while (a!=null) {
						fc.guardaClienteSinCompras(original, a);
						if (in1.available() > 0) {
							//Recuperamos cliente del fichero fusiona1
							dniA =in1.readLong();
							correoA="";
							for(int i=0;i<30;i++){
								correoA=correoA+in1.readChar();
							}
							correoA=UtilidadesCompartidas.quitaAsterisco(correoA);
							direccionA="";
							for(int i=0;i<20;i++){
								direccionA=direccionA+in1.readChar();
							}
							direccionA=UtilidadesCompartidas.quitaAsterisco(direccionA);
							
							//Creamos cliente a
							p=new PersonaImpl(dniA, "", "", "");
							a=new ClienteImpl(p, correoA, direccionA,null);
						} else {
							a = null;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in1 != null) {
				try {
					in1.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (in2 != null) {
				try {
					in2.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (leer1 != null) {
				try {
					leer1.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (leer2 != null) {
				try {
					leer2.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:public void ordenaFicheroEmpleado(String ruta)
	 * Proceso:Ordena un fichero de empleados.
	 * Precondiciones:Ninguna
	 * Entrada:Nada
	 * Salida:Nada
	 * Entrada/Salida:El fichero ordenado
	 * Postcondiciones:El fichero qeudará ordenado según el dni del empleado.
	 */
	
	public void ordenaFicheroEmpleado(String original){
		File fOriginal=new File(original);
		File fCopia=new File(original+"c");
		
		FicheroEmpleado fe=new FicheroEmpleado();
		
		String aux1=original+"aux1";
		String aux2=original+"aux2";
		
		FileInputStream originalFIS=null;
		DataInputStream originalDIS=null;
		
		PersonaImpl p;
		EmpleadoImpl emp;
		int tamanyoOriginal;
		long dni;
		String cuenta="";
		double sueldo;
		short tienda;
		
		//Para ordenar:
		File fAux1=new File(aux1);
		File fAux2=new File(aux2);
		
		//creaFicheroCopia
		fCopia.delete();
		try {
			fCopia.createNewFile();
		} catch (IOException e1) {
			System.out.println(e1);
		}
		
		//CopiaFichero
		try{
			originalFIS=new FileInputStream(fOriginal);
			originalDIS=new DataInputStream(originalFIS);
			
			
			while(originalDIS.available()>0){
				//Recuperamos cliente del fichero original
				dni =originalDIS.readLong();
				cuenta="";
				for(int i=0;i<20;i++){
					cuenta=cuenta+originalDIS.readChar();
				}
				sueldo=originalDIS.readDouble();
				
				tienda=originalDIS.readShort();
				//Creamos cliente a
				p=new PersonaImpl(dni, "", "", "");
				emp=new EmpleadoImpl(p,cuenta,sueldo,tienda);
				fe.guardaEmpleado(original+"c", emp);
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		}catch(EOFException e){
			
		}catch (IOException e) {
			System.out.println(e);
		}finally{
			if(originalDIS!=null){
				try {
					originalDIS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if(originalFIS!=null){
				try {
					originalFIS.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		//ordenaFichero
		tamanyoOriginal=fe.cuentaEmpleados(original);
		for(int i=1;i<tamanyoOriginal;i=i*2){
			parteFicheroEmpleadoSecuencias(original,aux1,aux2,i);
			
			//reescribeOriginal
			fOriginal.delete();
			try {
				fOriginal.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
			
			mezclaFicheroEmpleadoSecuencia(original, aux1, aux2, i);
			
			//reescribeAuxiliares
			fAux1.delete();
			fAux2.delete();
			try {
				fAux1.createNewFile();
				fAux2.createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
			
		}
		fAux1.delete();
		fAux2.delete();
		System.out.println("Ordenado externamente");
	}
	
	/* 
	 * Interfaz 
	 * Cabecera: private void parteFicheroEmpleadoSecuencias(String original,String part1,string part2,int secuencia)
	 * Proceso:método que divide un fichero de empleados en 2,siguiendo una secuencia,es decir,
	 * 			creando ficheros en los que mete las "secuencia" primeras,primero en uno y después en otro.
	 * Precondiciones:ARCHIVO DE TIPO OBJETO
	 * Entrada:1 cadena para el archivo original
	 * 		   2 cadenas para lso archivos de la partición
	 * 		   1 entero para el número de registros por secuencia
	 * Salida:los 2 archivos particionados
	 * Postcondiciones:El archivo quedará partido en dos, sin ser destruido o modificado
	 */
		
	private void parteFicheroEmpleadoSecuencias(String original, String part1, String part2, int secuencia) {
		File fOriginal = new File(original);
		FicheroEmpleado fe = new FicheroEmpleado();
		FileInputStream fis = null;
		DataInputStream dis = null;
		long dni;
		String cuenta;
		double sueldo;
		short tienda;
		int i, j,letra;
		//Uasremos una persona genérica,puesto que no nos interesa la información que se guarda ahí
		PersonaImpl p;
		EmpleadoImpl emp;
		
		
		try {
			fis = new FileInputStream(fOriginal);
			dis = new DataInputStream(fis);
			while (dis.available()>0) {
				for (i = 0;i < secuencia; i++) {
					//Recuperamos empleado del fichero original
					dni =dis.readLong();
					cuenta="";
					for(letra=0;letra<20;letra++){
						cuenta=cuenta+dis.readChar();
					}
					sueldo=dis.readDouble();
					
					tienda=dis.readShort();

					//Creamos y guardamos
					p=new PersonaImpl(dni, "a", "a", "a");
					emp=new EmpleadoImpl(p, cuenta,sueldo,tienda);
					fe.guardaEmpleado(part1, emp);
				}
				for (j = 0;j < secuencia; j++) {
					//Recuperamos empleado del fichero original
					dni =dis.readLong();
					cuenta="";
					for(letra=0;letra<20;letra++){
						cuenta=cuenta+dis.readChar();
					}
					sueldo=dis.readDouble();
					
					tienda=dis.readShort();

					//Creamos y guardamos
					p=new PersonaImpl(dni, "a", "a", "a");
					emp=new EmpleadoImpl(p, cuenta,sueldo,tienda);
					fe.guardaEmpleado(part2, emp);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	/* 
	 * Interfaz 
	 * Cabecera:void mezclaFicheroEmpleadoSecuencia(String nuevo,String fusiona1,String fusiona2,int secuencia)
	 * Proceso:método que fusiona dos ficheros, guardandolo en uno nuevo, de forma ORDENADA de menor a mayor
	 * Precondiciones:Entero mayor que 1
	 * Entrada:1 cadena para el nuevo fichero
	 * 			2 cadenas para cada uno de los ficheros que quieres fusionar
	 * 			1 entero para saber el número de secuencias que vas a mezclar en cada iteración
	 * Salida:El fichero
	 * Entrada/Salida:Ninguna
	 * Postcondiciones:El fichero quedará guardado en la carpeta indicada por la cadena "original"
	 */
	
	private void mezclaFicheroEmpleadoSecuencia(String original, String fusiona1, String fusiona2, int secuencia) {
		File fOriginal = new File(original);
		File fusiona_l = new File(fusiona1);
		File fusiona_2 = new File(fusiona2);
		
		FicheroEmpleado fe=new FicheroEmpleado();
		
		FileInputStream leer1 = null;
		DataInputStream in1 = null;
		FileInputStream leer2 = null;
		DataInputStream in2 = null;
		PersonaImpl p;
		EmpleadoImpl a=null,b=null;
		int cont1, cont2;
		
		long dniA,dniB;
		String cuentaA="",cuentaB="";
		double sueldoA,sueldoB;
		short tiendaA,tiendaB;
		
		try {
			leer1 = new FileInputStream(fusiona_l);
			in1 = new DataInputStream(leer1);
			leer2 = new FileInputStream(fusiona_2);
			in2 = new DataInputStream(leer2);
			

			//Recuperamos empleado del fichero fusiona1
			dniA =in1.readLong();
			cuentaA="";
			for(int i=0;i<20;i++){
				cuentaA=cuentaA+in1.readChar();
			}
			sueldoA=in1.readDouble();
			tiendaA=in1.readShort();
			
			//Creamos empleado a
			p=new PersonaImpl(dniA, "", "", "");
			a=new EmpleadoImpl(p, cuentaA, sueldoA, tiendaA);
			
			
			
			//Recuperamos empleado del fichero fusiona2
			dniB = in2.readLong();
			cuentaB="";
			for(int i=0;i<20;i++){
				cuentaB=cuentaB+in2.readChar();
			}
			sueldoB=in2.readDouble();
			tiendaB=in2.readShort();
			
			//Creamos empleado a
			p=new PersonaImpl(dniB, "", "", "");
			b=new EmpleadoImpl(p, cuentaB, sueldoB, tiendaB);
			
			

			while (a!=null || b!=null) { // Mientras quede
															// algo pro leer en
															// un fichero

				// Introducimos dependiendo de la secuencia
				for (cont1 = 0, cont2 = 0; cont1 < secuencia && cont2 < secuencia && 
						a!=null && b!=null;) {
					
					//Procedemos a guardar de forma ordenada
					if (dniA < dniB) {
						fe.guardaEmpleado(original, a);
						if (in1.available() > 0) {
							//Recuperamos empleado del fichero fusiona1
							dniA =in1.readLong();
							cuentaA="";
							for(int i=0;i<20;i++){
								cuentaA=cuentaA+in1.readChar();
							}
							sueldoA=in1.readDouble();
							tiendaA=in1.readShort();
							
							//Creamos empleado a
							p=new PersonaImpl(dniA, "", "", "");
							a=new EmpleadoImpl(p, cuentaA, sueldoA, tiendaA);
						} else {
							a = null;
						}
						cont1++;
					} else {
						fe.guardaEmpleado(original, b);
						if (in2.available() > 0) {
							//Recuperamos empleado del fichero fusiona2
							dniB = in2.readLong();
							cuentaB="";
							for(int i=0;i<20;i++){
								cuentaB=cuentaB+in2.readChar();
							}
							sueldoB=in2.readDouble();
							tiendaB=in2.readShort();
							
							//Creamos empleado a
							p=new PersonaImpl(dniB, "", "", "");
							b=new EmpleadoImpl(p, cuentaB, sueldoB, tiendaB);
						} else {
							b = null;
						}
						cont2++;

					}
				}

				// Si uno de los dos contadores ha llegado al valor de la
				// secuencia
				if (cont1 < secuencia) {
					while (a!= null && cont1 < secuencia) {
						fe.guardaEmpleado(original, a);
						if (in1.available() > 0) {
							//Recuperamos empleado del fichero fusiona1
							dniA =in1.readLong();
							cuentaA="";
							for(int i=0;i<20;i++){
								cuentaA=cuentaA+in1.readChar();
							}
							sueldoA=in1.readDouble();
							tiendaA=in1.readShort();
							
							//Creamos empleado a
							p=new PersonaImpl(dniA, "", "", "");
							a=new EmpleadoImpl(p, cuentaA, sueldoA, tiendaA);
						} else {
							a = null;
						}
						cont1++;
					}
				} else {
					while (b != null && cont2 < secuencia) {
						fe.guardaEmpleado(original, b);
						if (in2.available() > 0) {
							//Recuperamos empleado del fichero fusiona2
							dniB = in2.readLong();
							cuentaB="";
							for(int i=0;i<20;i++){
								cuentaB=cuentaB+in2.readChar();
							}
							sueldoB=in2.readDouble();
							tiendaB=in2.readShort();
							
							//Creamos empleado a
							p=new PersonaImpl(dniB, "", "", "");
							b=new EmpleadoImpl(p, cuentaB, sueldoB, tiendaB);
						} else {
							b = null;
						}
						cont2++;
					}
				}
				if (a==null) {
					while (b!=null) {
						fe.guardaEmpleado(original, b);
						if (in2.available() > 0) {
							//Recuperamos empleado del fichero fusiona2
							dniB = in2.readLong();
							cuentaB="";
							for(int i=0;i<20;i++){
								cuentaB=cuentaB+in2.readChar();
							}
							sueldoB=in2.readDouble();
							tiendaB=in2.readShort();
							
							//Creamos empleado a
							p=new PersonaImpl(dniB, "", "", "");
							b=new EmpleadoImpl(p, cuentaB, sueldoB, tiendaB);
						} else {
							b = null;
						}
					}
				}
				if (b==null) {
					while (a!=null) {
						fe.guardaEmpleado(original, a);
						if (in1.available() > 0) {
							//Recuperamos empleado del fichero fusiona1
							dniA =in1.readLong();
							cuentaA="";
							for(int i=0;i<20;i++){
								cuentaA=cuentaA+in1.readChar();
							}
							sueldoA=in1.readDouble();
							tiendaA=in1.readShort();
							
							//Creamos empleado a
							p=new PersonaImpl(dniA, "", "", "");
							a=new EmpleadoImpl(p, cuentaA, sueldoA, tiendaA);
						} else {
							a = null;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (EOFException e) {

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (in1 != null) {
				try {
					in1.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (in2 != null) {
				try {
					in2.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (leer1 != null) {
				try {
					leer1.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			if (leer2 != null) {
				try {
					leer2.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
}
