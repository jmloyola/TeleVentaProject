import java.rmi.RemoteException;
import java.io.Serializable;

public interface InterfazVenta extends Serializable {
    
    public String getNombre() throws RemoteException;
    public void setNombre (String nombre) throws RemoteException;		
	
}