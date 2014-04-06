import java.rmi.RemoteException;



public class ArticuloVenta implements InterfazArticuloVenta {

    private String nombreArticuloVenta;
    private int cantArticuloVenta;

    public ArticuloVenta(String nombreArticuloVenta, int cantArticuloVenta) {
        this.nombreArticuloVenta = nombreArticuloVenta;
        this.cantArticuloVenta = cantArticuloVenta;
    }
    
    public String getNombreArticuloVenta() {
        return nombreArticuloVenta;
    }

    public void setNombreArticuloVenta(String nombreArticuloVenta) {
        this.nombreArticuloVenta = nombreArticuloVenta;
    }

    public int getCantArticuloVenta() {
        return cantArticuloVenta;
    }

    public void setCantArticuloVenta(int cantArticuloVenta) {
        this.cantArticuloVenta = cantArticuloVenta;
    }
       
    public void imprimir(){
	System.out.format("%20s ----- %d", this.nombreArticuloVenta, this.cantArticuloVenta);    
    }
	
}