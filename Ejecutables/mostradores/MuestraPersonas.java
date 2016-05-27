package mostradores;
import ficheros.FicheroPersona;
public class MuestraPersonas {

	public static void main(String[] args) {
		FicheroPersona fp=new FicheroPersona();
		if(args.length>0){
			fp.muestraPersonas(args[0]);
		}else{
			fp.muestraPersonas("Ficheros//Personas//Personas.dat");
			System.out.println("Error,introduce una ruta");
		}
	}

}
