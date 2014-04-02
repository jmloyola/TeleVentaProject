

	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	
public class Server implements InterfazServer {
    
    int valor;    
	
    public Server() throws java.rmi.RemoteException{}

    public String sayHello() {
	return "HOLA MUNDO EN RMI ;) ";
    }

    public int sumar(int a , int b)throws java.rmi.RemoteException{
    	return (a+b);
    }	
    
    public void incrementarValor()throws java.rmi.RemoteException{
		valor++;
		
    }	
    
    public int getValor()throws java.rmi.RemoteException{
		return(valor);
    }


    public void noCambiarObjeto(InterfazOtraPersona op)throws java.rmi.RemoteException{
    	System.out.println("CAMBIANDO NOMBRE DE (" + op.getNombre() + ") a (JHON VON NEUMANN)");
	op.setNombre("JHON VON NEUMANN");
    }	
     

    public void cambiarObjeto (InterfazPersona p)throws java.rmi.RemoteException{
    	System.out.println("CAMBIANDO NOMBRE DE (" + p.getNombre() + ") a (ALAN TURING)");
	p.setNombre("ALAN TURING");
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
