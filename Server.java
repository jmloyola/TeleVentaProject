

	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class Server implements InterfazServer {
    
    int valor;    
	
    public Server() throws java.rmi.RemoteException{}

    public float nuevaVenta(InterfazVenta venta) throws java.rmi.RemoteException{
        System.out.println("Retornando monto de la venta...");
        return 390;
    }
    
    public String[] listarArticulos() throws java.rmi.RemoteException{
        System.out.println("Listando articulos...");
        String[] retorno = {"articulo 1", "articulo 2", "articulo 3"};
        return retorno;
    }
    
    public Venta[] listarVentas()throws java.rmi.RemoteException{
        System.out.println("Listando ventas...");
        ArticuloVenta articulo = new ArticuloVenta("auto coleccionable",3);
        ArticuloVenta[] articuloAux = {articulo};
        Venta ventaAux = new Venta("Juan", "Perez", "3535353", 2014, 4, 3, articuloAux);
        Venta[] retornoVentas = {ventaAux};
        return retornoVentas;
    }
    public static void main(String args[]) {
	
	try {
	    Server obj = new Server();
	    InterfazServer stub = (InterfazServer) UnicastRemoteObject.exportObject(obj, 0);

	    // Bind the remote object's stub in the registry
	    Registry registry = LocateRegistry.getRegistry("localhost",3001);
		registry.bind("Server", stub);

	    System.err.println("SERVIDOR LISTO Y ESPERANDO ...");
	} catch (Exception e) {
	    System.err.println("Server exception ERROR: " + e.toString());
	    e.printStackTrace();
	}
    }
}
