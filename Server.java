import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
	
public class Server implements InterfazServer {
      
    public Server() throws java.rmi.RemoteException{}

    public int nuevaVenta(InterfazVenta venta, String vendedor) throws java.rmi.RemoteException{
		// define the driver to use 
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// the database name  
		String dbName="VentasDB";
		// define the Derby connection URL to use 
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";

		Connection conn = null;
        ResultSet rs = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		ResultSet rs4 = null;
		PreparedStatement pst4 = null;
		
		System.out.println("Retornando monto de la venta...");
					
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connectionURL);
			// Buscamos el proximo identificador de venta
			String sql = "SELECT MAX(IdentificadorVenta) FROM VentasArticulos";
            pst = conn.prepareStatement(sql);
            
            rs = pst.executeQuery();
			
			int identificadorVenta;
    
            if (rs.next()){
                identificadorVenta = rs.getInt(1) + 1; //Obtengo el valor de identificador de venta mas alto y lo incremento para obtener el nuevo identificador de venta
            }
            else{
                identificadorVenta = 0;
			}
			
			// Insertamos la venta
			String sql2 = "INSERT INTO VentasArticulos VALUES (?,?,?,?,?,?,?,?)";
			
			pst2 = conn.prepareStatement(sql2);
			
			pst2.setString(1, vendedor);
			pst2.setString(2, venta.getNombreComprador());
			pst2.setString(3, venta.getApellidoComprador());
			pst2.setString(4, venta.getNumeroDocumentoComprador());
			pst2.setInt(5, venta.getAnioVenta());
			pst2.setInt(6, venta.getMesVenta());
			pst2.setInt(7, venta.getDiaVenta());
			pst2.setInt(8, identificadorVenta);
            
            pst2.execute();
			
			// Ingresamos los articulos de la venta
			int montoTotalVenta = 0;
			ArticuloVenta[] listaArticulosVenta;
			
			listaArticulosVenta = venta.getListaArticulosVenta();
					
			for (int k=0; k<listaArticulosVenta.length; k++){
				String sql3 = "INSERT INTO ArticuloDeVenta VALUES (?,?,?)";
			
				pst3 = conn.prepareStatement(sql3);
				
				pst3.setInt(1, identificadorVenta);
				pst3.setString(2, listaArticulosVenta[k].getNombreArticuloVenta());
				pst3.setInt(3, listaArticulosVenta[k].getCantArticuloVenta());
				
				pst3.execute();
				
				String sql4 = "SELECT PrecioArticulo FROM Articulos WHERE NombreArticulo=?";
				
				pst4 = conn.prepareStatement(sql4);
				pst4.setString(1, listaArticulosVenta[k].getNombreArticuloVenta());
				
				rs4 = pst4.executeQuery();
						
				if (rs4.next()){
					montoTotalVenta += rs4.getInt(1) * listaArticulosVenta[k].getCantArticuloVenta();
				}
			}
			
			return montoTotalVenta;
			
        }catch(Exception e){
			System.out.println("Error al insertar nueva venta en la base de datos.");
			System.out.println("Informacion del error: " + e.toString());
			e.printStackTrace();
			return 0;
        }
    }
    
    public String[] listarArticulos() throws java.rmi.RemoteException{
		// define the driver to use 
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// the database name  
		String dbName="VentasDB";
		// define the Derby connection URL to use 
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet rs2 = null;
		PreparedStatement pst2 = null;
		
        System.out.println("Listando articulos...");
		
		int cantArticulos = 0;
        String sql = "SELECT COUNT(NombreArticulo) FROM Articulos";
		
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connectionURL);
            pst = conn.prepareStatement(sql);
            
            rs = pst.executeQuery();
    
            if (rs.next()){
                cantArticulos = rs.getInt(1);
            }
			
			String[] articulos = new String[cantArticulos];
			String sql2 = "SELECT NombreArticulo FROM Articulos";
			
			pst2 = conn.prepareStatement(sql2);
			
			rs2 = pst2.executeQuery();
			
			int k = 0;
			while (rs2.next()){
				articulos[k] = rs2.getString(1);
				k++;
			}
			
			return articulos;
        }catch(Exception e){
			System.out.println("Error al listar los articulos.");
			System.out.println("Informacion del error: " + e.toString());
			e.printStackTrace();
			return null;
        }
        
    }
    
    public Venta[] listarVentas(String nombreVendedor)throws java.rmi.RemoteException{
		// define the driver to use 
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// the database name  
		String dbName="VentasDB";
		// define the Derby connection URL to use 
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet rs2 = null;
		PreparedStatement pst2 = null;
		ResultSet rs3 = null;
		PreparedStatement pst3 = null;
		ResultSet rs4 = null;
		PreparedStatement pst4 = null;
		ResultSet rs5 = null;
		PreparedStatement pst5 = null;
		
        System.out.println("Listando ventas...");
		
		String sql = "SELECT COUNT(IdentificadorVenta) FROM VentasArticulos WHERE NombreVendedor=?";
		int cantidadVentas = 0;
		
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connectionURL);
            pst = conn.prepareStatement(sql);
			pst.setString(1, nombreVendedor);
            
            rs = pst.executeQuery();
    
            if (rs.next()){
                cantidadVentas = rs.getInt(1);
            }
			
			Venta[] ventas = new Venta[cantidadVentas];
			String sql2 = "SELECT NombreComprador, ApellidoComprador, DocumentoComprador, AnioVenta, MesVenta, DiaVenta, IdentificadorVenta FROM VentasArticulos WHERE NombreVendedor=?";
			
			pst2 = conn.prepareStatement(sql2);
			pst2.setString(1, nombreVendedor);
			
			rs2 = pst2.executeQuery();
			
			int k = 0;
			while (rs2.next()){
				String nombreComprador = rs2.getString(1);
				String apellidoComprador = rs2.getString(2);
				String numeroDocumentoComprador = rs2.getString(3);
				
				int anioVenta = rs2.getInt(4);
				int mesVenta = rs2.getInt(5);
				int diaVenta = rs2.getInt(6);
				
				int identificadorVenta = rs2.getInt(7);
				
				String sql3 = "SELECT COUNT(A_NombreArticulo) FROM ArticuloDeVenta WHERE VA_IdentificadorVenta=?";
				
				pst3 = conn.prepareStatement(sql3);
				pst3.setInt(1, identificadorVenta);
				
				rs3 = pst3.executeQuery(); 
				
				int cantArticulosVenta = 0;
				
				if (rs3.next()){
					cantArticulosVenta = rs3.getInt(1);
				}
				
				ArticuloVenta[] listaArticulosVenta = new ArticuloVenta[cantArticulosVenta];
				String sql4 = "SELECT A_NombreArticulo, Cantidad FROM ArticuloDeVenta WHERE VA_IdentificadorVenta=?";
				
				pst4 = conn.prepareStatement(sql4);
				pst4.setInt(1, identificadorVenta);
				
				rs4 = pst4.executeQuery();
				
				int j=0;
				while (rs4.next()){
					String nombreArticulo = rs4.getString(1);
					int cantidadArticulo = rs4.getInt(2);
					
					ArticuloVenta articulo = new ArticuloVenta(nombreArticulo, cantidadArticulo);
					listaArticulosVenta[j] = articulo;
					j++;
				}
				Venta venta = new Venta(nombreComprador, apellidoComprador, numeroDocumentoComprador, anioVenta, mesVenta, diaVenta, listaArticulosVenta);
				
				ventas[k] = venta;
				
				k++;
			}
			return ventas;
        }catch(Exception e){
			System.out.println("Error al listar las ventas.");
			System.out.println("Informacion del error: " + e.toString());
			e.printStackTrace();
			return null;
        }
    }
	
	public boolean esArticuloValido(String nombreArticulo) throws RemoteException{
		// define the driver to use 
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// the database name  
		String dbName="VentasDB";
		// define the Derby connection URL to use 
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		System.out.println("Verificando existencia de articulo...");
		
		String sql = "SELECT NombreArticulo FROM Articulos WHERE NombreArticulo = ?";
		
		try{
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connectionURL);		 
            pst = conn.prepareStatement(sql);
            pst.setString(1, nombreArticulo);
            
            rs = pst.executeQuery();
    
            if (rs.next()){
                return true;
            }
            else{
                return false;                
			}
        }catch(Exception e){
			System.out.println("Error al verificar si el articulo es valido.");
			System.out.println("Informacion del error: " + e.toString());
			e.printStackTrace();
			return false;
        }		
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
