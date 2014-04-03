import java.rmi.RemoteException;
import java.io.Serializable;

public interface InterfazVenta extends Serializable {
    
    public void imprimir () throws RemoteException;		
	
}