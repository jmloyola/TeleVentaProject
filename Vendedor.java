import java.io.*;
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
			System.out.println("Recuerde que debe ejecutar la aplicación vendedor, indicandole su nombre.");
			System.exit(0);
		}
		
		System.out.println("Bienvenido/a: " + nombreVendedor);
		System.out.println();
		
		for (;;){
			System.out.println("=========================================");
			System.out.println("=========   Ingrese una opción   ========");
			System.out.println();
			System.out.println("   (1) Nueva venta");
			System.out.println("   (2) Listar mis ventas");
			System.out.println("   (3) Salir");
			System.out.println();
			System.out.println("Ha ingresado la opción: ");
			
			Scanner sc = new Scanner(System.in);
			int opcion = sc.nextInt();
			
			
			System.out.println("=========================================");
			
			switch (opcion){
				case 1:{
					System.out.println("Nueva venta.");
					System.out.println();
					System.out.println("Ingrese la información relativa a la nueva venta:");
					
					String nombre;
					break;
				}
				case 2:{
					System.out.println("Las ventas que usted ha realizado son:");
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
		
		/*
	    String response = stub.sayHello();
	    System.out.println("response: " + response);
	    
	    System.out.println("");

	    System.out.println("LA SUMA DE 2 + 3 ES:" + stub.sumar(2,3));
	    stub.incrementarValor();	
	    
            System.out.println("");
            System.out.println("SUMO A ESTADO Y AHORA VALGO:" +  stub.getValor());

            System.out.println("");
	    System.out.println("-------------------------------------------");


	    InterfazOtraPersona op = new OtraPersona("JHONY");
	    System.out.println("EL NOMBRE ANTES DE ENVIAR PETICION DE CAMBIO ES:" + op.getNombre());	
            stub.noCambiarObjeto(op);
	    System.out.println("NUEVO NOMBRE:" + op.getNombre());

	    System.out.println("");	
	    System.out.println("--------------------------------------------");	
	    System.out.println("");*/    
	
	    	 		
	} catch (Exception e) {
	    System.err.println("Client exception: " + e.toString());
	    e.printStackTrace();
	}
    }
}
