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
	    InterfazServer stub = (InterfazServer) registry.lookup("Server");
	    
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
					
					for (int i=0; i < listaArticulos.length; i++){
						System.out.println(" > " + listaArticulos[i]);
					}
					break;
				}
				case 1:{
					System.out.println("Nueva venta.");
					System.out.println();
					System.out.println("Ingrese la información relativa a la nueva venta:");
					
					ArticuloVenta[] listaArticulosVenta;
					
					LinkedList lista = new LinkedList();
					
					ArticuloVenta articuloVentaAuxiliar;
					
					String nombreArticulo;
					int cantArticulos;
					
					String nombreComprador;
					String apellidoComprador;
					String numeroDocumentoComprador;
					
					Calendar fechaActual = new GregorianCalendar();
					int anioVenta = fechaActual.get(Calendar.YEAR);
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
					cantArticulos = Integer.parseInt(scanner.nextLine()); ///http://stackoverflow.com/questions/13102045/skipping-nextline-after-use-nextint
					
					articuloVentaAuxiliar = new ArticuloVenta(nombreArticulo, cantArticulos);
					lista.add(articuloVentaAuxiliar);
					
					System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -");
					System.out.println("Desea seguir ingresando articulos a la venta (ingrese 's'): ");
					String caracter = scanner.nextLine();
					
					while (caracter.equals("s")){
						System.out.println("Nombre Articulo: ");
						nombreArticulo = scanner.nextLine();
						System.out.println("Cantidad Deseada: ");
						cantArticulos = Integer.parseInt(scanner.nextLine());
						
						articuloVentaAuxiliar = new ArticuloVenta(nombreArticulo, cantArticulos);
						lista.add(articuloVentaAuxiliar);
						
						System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -");
						System.out.println("Desea seguir ingresando articulos a la venta (ingrese 's'): ");
						caracter = scanner.nextLine();
					}					
					System.out.println();
					
					listaArticulosVenta = new ArticuloVenta[lista.size()];
					
					for (int j=0; j < lista.size(); j++){
						listaArticulosVenta[j] = (ArticuloVenta)lista.peek();
						lista.remove();
					}
										
					Venta nuevaVenta = new Venta(nombreComprador, apellidoComprador, numeroDocumentoComprador, anioVenta, mesVenta, diaVenta, listaArticulosVenta);
					
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
					
					for (int i=0; i < ventas.length; i++){
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
