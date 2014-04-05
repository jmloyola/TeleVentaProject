import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazServer extends Remote {
    int nuevaVenta(InterfazVenta venta, String vendedor) throws RemoteException;
    String[] listarArticulos() throws RemoteException;
    Venta[] listarVentas()throws RemoteException;
	boolean esArticuloValido(String nombreArticulo) throws RemoteException;
}
