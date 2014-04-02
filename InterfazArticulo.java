import java.rmi.RemoteException;
import java.io.Serializable;

public interface InterfazArticulo extends Serializable {
    
    public String getNombre() throws RemoteException;
    public void setNombre (String nombre) throws RemoteException;		
	
}