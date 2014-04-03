import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServer extends Remote {
    float nuevaVenta(InterfazVenta venta) throws RemoteException;
	String[] listarArticulos() throws RemoteException;
    Venta[] listarVentas()throws RemoteException;
	int controlarStockArticulo(String nombreArticulo) throws RemoteException;	
}
