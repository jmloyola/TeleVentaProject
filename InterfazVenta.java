import java.rmi.RemoteException;
import java.io.Serializable;

public interface InterfazVenta extends Serializable {
    
    public void imprimir ();		
	
	public String getNombreComprador();

    public String getApellidoComprador();

    public String getNumeroDocumentoComprador();

    public int getAnioVenta();

    public int getMesVenta();

    public int getDiaVenta();

    public ArticuloVenta[] getListaArticulosVenta();
}