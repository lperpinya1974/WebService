/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lluis_2
 */

public class Conexion {
    
    private Connection conexio;
    
    public Conexion()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
              //  conexio = DriverManager.getConnection("jdbc:oracle:thin:@192.168.180.10:1521:INSLAFERRERI", "PROFEA1","1234");
               conexio = DriverManager.getConnection("jdbc:oracle:thin:@ieslaferreria.xtec.cat:8081:INSLAFERRERI", "PROFEA1","1234");
                
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void finalizarConexion() throws SQLException
        {
            conexio.close();
        }
    
        public List<Cliente> obtenerClientes() throws SQLException
        {
            List<Cliente> lista = new ArrayList();
            ResultSet rset;
             
         String sql = "SELECT IdCliente, Nombre, Telefono FROM Cliente";
         PreparedStatement stmt = conexio.prepareStatement(sql);
         rset = stmt.executeQuery();
      
         while (rset.next())
         {
             lista.add (new Cliente(rset.getInt("idcliente"), rset.getString("nombre"),
             rset.getInt("telefono")));
         }
         this.finalizarConexion();
         return lista;
            
        }
    
    
}
