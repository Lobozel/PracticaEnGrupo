
//Crear BDOO Matisse
//https://drive.google.com/open?id=1Kna91QCuYFlwrarA0wxTh-kmXPuhJzZE
//Consultas OQL Matisse
//https://drive.google.com/file/d/1oAxj-m7ndfJfGf57g8QD66fpR0VRD42V/view?usp=sharing


import com.matisse.MtDatabase;
import com.matisse.MtException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MiguelAngel
 */
public class Matisse {

    public static void main(String[] args) {
        //Crea el objeto de conexión
        MtDatabase db = new MtDatabase("localhost", "holamundo");

        try {
            //Abre la conexión
            db.open();
            //Inicia la trnasacción de datos
            db.startTransaction();
            //ejecuta el método insertar objetos en la BD
            //insertarObjetos(db);
            consulta(db);
        } catch (MtException mte) {
            System.out.println(mte.getMessage());
        } finally {
            //comprueba si la transacción aún está en proceso
            if (db.isTransactionInProgress()) {
                db.commit();
            }
            //cierra la conexión            
            db.close();
        }

    }

    public static void insertarPerfume(MtDatabase db, int idPerf, String nombrePerf, Double precio) {
        Perfume perfume = new Perfume(db);
        perfume.setIdPerf(idPerf);
        perfume.setNombrePerf(nombrePerf);
        perfume.setPrecio(precio);
    }
    
    public static void insertarFlor(MtDatabase db, int idFlor, String nombreFlor, String color){
        Flores flor = new Flores(db);
        flor.setIdFlor(idFlor);
        flor.setNombreFlor(nombreFlor);
        flor.setColor(color);
    }
    
    public static void insertarTienda(MtDatabase db, int idTienda, String nombreTienda, String ubicacion){
        Tienda tienda = new Tienda(db);
        tienda.setIdTienda(idTienda);
        tienda.setNombreTienda(nombreTienda);
        tienda.setUbicacion(ubicacion);
    }
    
    public static void relacionar(int idPerf, int idPerfume, int idTienda){
        
    }

    public static void consulta(MtDatabase db) {
        String resultado="";
        Flores flor = new Flores(db);
        try {
            Class.forName("com.matisse.sql.MtDriver");
            String url = "jdbc:mt://localhost/holamundo";
            System.out.println("Query class names from the JDBC connection: " + url);
            Connection jcon = DriverManager.getConnection(url);
            Statement stmt = (Statement) jcon.createStatement();
            String query = "SELECT * FROM Flores";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Result from: " + query);
            
            while (rs.next()) {
                resultado=rs.getString("idFlor");        
                /*
                flor.setIdFlor(Integer.parseInt(resultado));
                resultado=rs.getString("nombreFlor");
                flor.setNombreFlor(resultado);
                resultado=rs.getString("color");
                flor.setColor(resultado);
                if(flor.getIdFlor()==idFlor){
                return;
                }
                System.out.println(flor.getIdFlor()+"\n"+flor.getNombreFlor()+"\n"+flor.getColor());
                */
                System.out.println(resultado);
                /*
                if(!resultado.equals(String.valueOf(idPerf))){
                    insertarPerfume(db, idPerf, nombrePerf, precio);
                }
*/
            }
            System.out.println("\ndone.");
        } catch (ClassNotFoundException e) {
            System.out.println("Matisse JDBC Driver class not found, check your CLASSPATH");
        } catch (SQLException e) {
            System.out.println("SQLException thrown:  " + e.getMessage());
        }
    }

    public static void insertarObjetos(MtDatabase db) {
        Perfume p1 = new Perfume(db);
        p1.setIdPerf(1);
        p1.setNombrePerf("Pasión Floral");
        p1.setPrecio(0.95d);

        Flores f1 = new Flores(db);
        f1.setIdFlor(1);
        f1.setNombreFlor("Rosa");
        f1.setColor("Rojo");

        Flores f2 = new Flores(db);
        f2.setIdFlor(2);
        f2.setNombreFlor("Lavanda");
        f2.setColor("Lila");

        Tienda t1 = new Tienda(db);
        t1.setIdTienda(1);
        t1.setNombreTienda("Mercadona");
        t1.setUbicacion("Almería");

        p1.appendPosee(f1);
        p1.appendPosee(f2);
        p1.appendSe_vende(t1);

        f1.setPertenece(p1);
        f2.setPertenece(p1);

        t1.appendEs_vendido(p1);

    }

}
