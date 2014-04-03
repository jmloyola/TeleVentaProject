import java.io.*;
import java.util.*;
import java.util.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Vendedor {

    private Vendedor() {}

    public static void main(String[] args) {
	try {
	    
	    Registry registry = LocateRegistry.getRegistry("localhost",3001);
	    InterfazServer stub = (InterfazServer) registry.lookup("Servidor");
	    
		String nombreVendedor = (args.length < 1) ? null : args[0];
		
		if (nombreVendedor == null){
			System.out.println("Recuerde que debe ejecutar la aplicacion vendedor, indicandole su nombre.");
			System.exit(0);
		}
		
		System.out.println("Bienvenido/a: " + nombreVendedor);
		System.out.println();
		
		for (;;){
			System.out.println("=========================================");
			System.out.println("=========   Ingrese una opcion   ========");
			System.out.println();
			System.out.println("   (0) Listar articulos");
			System.out.println("   (1) Nueva venta");
			System.out.println("   (2) Listar mis ventas");
			System.out.println("   (3) Salir");
			System.out.println();
			System.out.println("Ha ingresado la opción: ");
			
			Scanner sc = new Scanner(System.in);
			int opcion = sc.nextInt();
			
			
			System.out.println("=========================================");
			
			switch (opcion){
				case 0:{
					System.out.println("Listado de articulos:");
					System.out.println();
					String[] listaArticulos = stub.listarArticulos();
					
					for (int i=0; i < listarArticulos.length(); i++){
						System.out.println(" > " + listarArticulos[i]);
					}
					break;
				}
				case 1:{
					System.out.println("Nueva venta.");
					System.out.println();
					System.out.println("Ingrese la información relativa a la nueva venta:");
					
					Articulo[] listaArticulosVenta;
					
					LinkedList lista = new LinkedList();
					
					Articulo articuloAuxiliar;
					
					String nombreArticulo;
					int cantArticulos;
					
					String nombreComprador;
					String apellidoComprador;
					String numeroDocumentoComprador;
					
					Calendar fechaActual = new GregorianCalendar();
					int añoVenta = fechaActual.get(Calendar.YEAR);
					int mesVenta = fechaActual.get(Calendar.MONTH);
					int diaVenta = fechaActual.get(Calendar.DAY_OF_MONTH);
					
					Scanner scanner = new Scanner(System.in);
					System.out.println("Nombre del comprador: ");
					nombreComprador = scanner.nextLine();
					System.out.println("Apellido del comprador: ");
					apellidoComprador = scanner.nextLine();
					System.out.println("Numero de documento del comprador: ");
					numeroDocumentoComprador = scanner.nextLine();
					
					System.out.println("Nombre Articulo: ");
					nombreArticulo = scanner.nextLine();
					System.out.println("Cantidad Deseada: ");
					cantArticulos = scanner.nextInt();
					
					articuloAuxiliar = new Articulo(nombreArticulo, cantArticulos);
					lista.add(articuloAuxiliar);
					
					System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -");
					System.out.println("Desea seguir ingresando articulos a la venta (ingrese 's'): ");
					char caracter = (char) System.in.read();
					
					while (caracter == 's'){
						System.out.println("Nombre Articulo: ");
						nombreArticulo = scanner.nextLine();
						System.out.println("Cantidad Deseada: ");
						cantArticulos = scanner.nextInt();
						
						articuloAuxiliar = new Articulo(nombreArticulo, cantArticulos);
						lista.add(articuloAuxiliar);
						
						System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -");
						System.out.println("Desea seguir ingresando articulos a la venta (ingrese 's'): ");
						char caracter = (char) System.in.read();
					}					
					System.out.println();
					listaArticulosVenta = lista.toArray();
					
					Venta nuevaVenta = new Venta(nombreComprador, apellidoComprador, numeroDocumentoComprador, añoVenta, mesVenta, diaVenta, listaArticulosVenta);
					
					float montoTotalVenta = stub.nuevaVenta(nuevaVenta);
					
					System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||");
					System.out.println("El monto total de la venta es: " + montoTotalVenta);
					System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||");
					System.out.println();
					
					break;
				}
				case 2:{
					System.out.println("Las ventas que usted ha realizado son:");
					Venta[] ventas = stub.listarVentas();
					
					for (int i=0; i < ventas.length(); i++){
						System.out.println("------------------------------------------");
						ventas[i].imprimir();
					}
					break;
				}
				case 3:{
					System.exit(0);
				}
				default:{
					System.out.println();
					System.out.println("#########################################");
					System.out.println("Opción invalida.");
					System.out.println("#########################################");
					System.out.println();
				}
			}
		}	
	    	 		
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
