import java.rmi.RemoteException;



public class Venta implements InterfazVenta {

	private Articulo[] listaArticulosVenta;
		
	private String nombreComprador;
	private String apellidoComprador;
	private String numeroDocumentoComprador;
	
	private int añoVenta;
	private int mesVenta;
	private int diaVenta;

    public Venta(String nombreComprador, String apellidoComprador, String numeroDocumentoComprador, int añoVenta, int mesVenta, int diaVenta, Articulo[] listaArticulosVenta); throws RemoteException {
    	this.nombreComprador = nombreComprador;
		this.apellidoComprador = apellidoComprador;
		this.numeroDocumentoComprador = numeroDocumentoComprador;
		this.añoVenta = añoVenta;
		this.mesVenta = mesVenta;
		this.diaVenta = diaVenta;
		this.listaArticulosVenta = listaArticulosVenta;
    }	
	
	public void imprimir () throws RemoteException{
	}	

    public String getNombre() throws RemoteException{
	return (this.nombre);
    }


    public void setNombre(String nombre) throws RemoteException{
	this.nombre=nombre;    
    }
	
}