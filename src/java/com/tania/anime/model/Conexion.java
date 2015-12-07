package com.tania.anime.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Seb
 */
public class Conexion {

    //Harcodeamos los valores de la conexion
    private final static String USR="root";
    private static final String PWD="";
    private static final String DB="portalanime";
    private static final String HOST="localhost";
    private static final String PUERTO = "3306";
    
    //Conexion 
    private static Connection conn;
    
    
    public static Connection getConnection()
    {
        if(conn==null)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                
                conn= DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PUERTO+"/"+DB+"?user="+USR+"&password="+PWD);
                
            } catch (ClassNotFoundException ex) {
                System.out.println("Clase no encontrada");
            } catch (InstantiationException ex) {
                System.out.println("Error de instanciacion");
            } catch (IllegalAccessException ex) {
                System.out.println("Error de Autenticacion");
            } catch (SQLException ex) {
                System.out.println("Error SQL");
                ex.printStackTrace();
            }
        }
        
        return conn;
    }
    
    
    public static boolean testForColumn(ResultSet rs,String columna)
    {
        ResultSetMetaData rsmd;
        
        try {
            rsmd=rs.getMetaData();
            int tam=rsmd.getColumnCount();
            //System.out.println(tam);
            for(int i=1;i<=tam;i++)
            {
                if(columna.equals(rsmd.getColumnName(i)))
                    return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(GeneroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    /*
    public static void main(String[] args) {
        getConnection();
    }*/
}
