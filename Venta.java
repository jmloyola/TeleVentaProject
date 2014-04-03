import java.rmi.RemoteException;



public class Venta implements InterfazVenta {

	private ArticuloVenta[] listaArticulosVenta;
		
	private String nombreComprador;
	private String apellidoComprador;
	private String numeroDocumentoComprador;
	
	private int añoVenta;
	private int mesVenta;
	private int diaVenta;

    public Venta(String nombreComprador, String apellidoComprador, String numeroDocumentoComprador, int añoVenta, int mesVenta, int diaVenta, ArticuloVenta[] listaArticulosVenta) throws RemoteException {
    	this.nombreComprador = nombreComprador;
        this.apellidoComprador = apellidoComprador;
        this.numeroDocumentoComprador = numeroDocumentoComprador;
        this.añoVenta = añoVenta;
        this.mesVenta = mesVenta;
        this.diaVenta = diaVenta;
        this.listaArticulosVenta = listaArticulosVenta;
    }

    public ArticuloVenta[] getListaArticulosVenta() {
        return listaArticulosVenta;
    }

    public void setListaArticulosVenta(ArticuloVenta[] listaArticulosVenta) {
        this.listaArticulosVenta = listaArticulosVenta;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getApellidoComprador() {
        return apellidoComprador;
    }

    public void setApellidoComprador(String apellidoComprador) {
        this.apellidoComprador = apellidoComprador;
    }

    public String getNumeroDocumentoComprador() {
        return numeroDocumentoComprador;
    }

    public void setNumeroDocumentoComprador(String numeroDocumentoComprador) {
        this.numeroDocumentoComprador = numeroDocumentoComprador;
    }

    public int getAñoVenta() {
        return añoVenta;
    }

    public void setAñoVenta(int añoVenta) {
        this.añoVenta = añoVenta;
    }

    public int getMesVenta() {
        return mesVenta;
    }

    public void setMesVenta(int mesVenta) {
        this.mesVenta = mesVenta;
    }

    public int getDiaVenta() {
        return diaVenta;
    }

    public void setDiaVenta(int diaVenta) {
        this.diaVenta = diaVenta;
    }
    
    
	
    public void imprimir () throws RemoteException{
        System.out.println("Nombre comprador: " + this.nombreComprador);
        System.out.println("Apellido comprador: " + this.apellidoComprador);
        System.out.println("Numero documento comprador: " + this.numeroDocumentoComprador);
        System.out.println("Dia de la venta: " + this.diaVenta);
        System.out.println("Mes de la venta: " + this.mesVenta);
        System.out.println("Año de la venta: " + this.añoVenta);
        
        for (int i=0; i < listaArticulosVenta.length; i++){
            listaArticulosVenta[i].imprimir();
        }

    }	
	
}