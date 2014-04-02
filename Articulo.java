import java.rmi.RemoteException;



public class Articulo implements InterfazArticulo {

    private String nombre;

    public OtraPersona(String nombre) throws RemoteException {
    	this.nombre=nombre;
    }	

    public String getNombre() throws RemoteException{
	return (this.nombre);
    }


    public void setNombre(String nombre) throws RemoteException{
	this.nombre=nombre;    
    }
	
}